package edu.fudan.ml.classifier.hier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import edu.fudan.ml.classifier.hier.inf.MultiLinearMax;
import edu.fudan.ml.classifier.linear.inf.Inferencer;
import edu.fudan.ml.eval.Evaluation;
import edu.fudan.ml.feature.BaseGenerator;
import edu.fudan.ml.loss.Loss;
import edu.fudan.ml.types.HashSparseVector;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;
import edu.fudan.ml.types.LabelAlphabet;
import edu.fudan.util.MyHashSparseArrays;
/**
 * 大规模层次化多类分类训练，类别数很大，因此权重向量用稀疏数组表示
 * @author xpqiu
 *
 */
public class PATrainer {

	// 特征权重
	private HashSparseVector[] weights;
	private Linear classifier;
	private MultiLinearMax msolver;
	private BaseGenerator featureGen;
	private Loss loss;

	// 最大迭代次数
	private int maxIter = Integer.MAX_VALUE;
	// 最小错误
	private float eps = 1e-10f;
	private Tree tree;
	private float c;
	/**
	 * 保存中间结果
	 */
	public boolean interim=false;
	public boolean optim=false;
	private boolean incremental =false;

	public PATrainer(Linear pc, Loss loss, int maxIter, float c, Tree tr){
		msolver = (MultiLinearMax) pc.inf;
		msolver.isUseTarget(true);
		featureGen = pc.gen;
		this.loss = loss;
		this.maxIter = maxIter;
		tree = tr;
		this.c = c;		
		incremental = true;
		weights = pc.weights;
	}

	public PATrainer(Inferencer msolver, BaseGenerator featureGen, Loss loss,
			int maxIter, float c, Tree tr) {
		this.msolver = (MultiLinearMax) msolver;
		this.featureGen = featureGen;
		this.loss = loss;
		this.maxIter = maxIter;
		tree = tr;
		this.c = c;
	}

	public Linear getClassifier() {
		return classifier;
	}

	/**
	 * 训练
	 * 
	 * @param eval
	 */
	public Linear train(InstanceSet trainingList, Evaluation eval) {
		System.out.println("Sample Size: " + trainingList.size());
		LabelAlphabet labels = trainingList.getAlphabetFactory().DefaultLabelAlphabet();

		System.out.println("Class Size: " + labels.size());

		if(!incremental){
			// 初始化权重向量到类中心
			weights = Mean.mean(trainingList, tree);
			msolver.setWeight(weights);
		}
		
		int loops = 0;
		float oldErrorRate = Float.MAX_VALUE;
		int numSamples = trainingList.size();
		int frac = numSamples / 10;

		// 开始循环
		System.out.println("Begin Training...");
		long beginTime = System.currentTimeMillis();
		while (loops++ < maxIter) {
			System.out.print("Loop: " + loops);
			float totalerror = 0;
			trainingList.shuffle();
			long beginTimeInner = System.currentTimeMillis();
			for (int ii = 0; ii < numSamples; ii++) {

				Instance inst = trainingList.getInstance(ii);
				int maxC = (Integer) inst.getTarget();
//				HashSet<Integer> t = new HashSet<Integer>();
//				t.add(maxC);
				Results pred = (Results) msolver.getBest(inst, 1);
				int maxE = pred.getPredAt(0);
				int error;
				if (tree == null) {
					error = (pred.getPredAt(0) == maxC) ? 0 : 1;
				} else {
					error = tree.dist(maxE, maxC);
				}
				float loss = error- (pred.getOraleScoreAt(0) - pred.getScoreAt(0));

				if (loss > 0) {// 预测错误，更新权重

					totalerror += 1;
					// 计算含层次信息的内积
					// 计算步长
					float phi = featureGen.getVector(inst).l2Norm2();
					float alpha = (float) Math.min(c, loss / (phi * error));
					if (tree != null) {
						int[] anc = tree.getPath(maxC);
						for (int j = 0; j < anc.length; j++) {
							weights[anc[j]].plus(featureGen.getVector(inst), alpha);
						}
						anc = tree.getPath(maxE);
						for (int j = 0; j < anc.length; j++) {
							weights[anc[j]].plus(featureGen.getVector(inst), -alpha);
						}
					} else {
						weights[maxC].plus(featureGen.getVector(inst), alpha);
						weights[maxE].plus(featureGen.getVector(inst), -alpha);
					}

				}
				if (frac==0||ii % frac == 0) {// 显示进度
					System.out.print('.');
				}
			}
			float acc = 1 - totalerror / numSamples;
			System.out.print("\t Accuracy:" + acc);
			System.out.println("\t Time(s):"
					+ (System.currentTimeMillis() - beginTimeInner) / 1000);
			
			if(optim&&loops<=2){
				int oldnum = 0;
				int newnum = 0;
				for(int i = 0;i<weights.length;i++){
					oldnum += weights[i].size();
					MyHashSparseArrays.trim(weights[i],0.99f);
					newnum += weights[i].size();
				}
				System.out.println("优化：\t原特征数："+oldnum + "\t新特征数："+newnum);					
			}
			

			if (interim) {
				Linear  p = new Linear(weights, msolver, featureGen, trainingList.getPipes(), trainingList.getAlphabetFactory());
				try {
					p.saveTo("./tmp/model.gz");
				} catch (IOException e) {
					System.err.println("write model error!");
				}
				msolver.isUseTarget(true);
			}

			if (eval != null) {
				System.out.print("Test:\t");
				Linear classifier = new Linear(weights, msolver);
				eval.eval(classifier,2);
				msolver.isUseTarget(true);
			}
			if (acc == 1 && Math.abs(oldErrorRate - acc) / oldErrorRate < eps)
				break;
		}
		System.out.println("Training End");
		System.out.println("Training Time(s):"
				+ (System.currentTimeMillis() - beginTime) / 1000);		

		classifier = new Linear(weights, msolver, featureGen, trainingList.getPipes(), trainingList.getAlphabetFactory());
		return classifier;
	}

}
