package edu.fudan.ml.classifier.hier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import edu.fudan.ml.classifier.AbstractClassifier;
import edu.fudan.ml.classifier.Predict;
import edu.fudan.ml.classifier.TResult;
import edu.fudan.ml.classifier.linear.inf.Inferencer;
import edu.fudan.ml.feature.BaseGenerator;
import edu.fudan.ml.types.AlphabetFactory;
import edu.fudan.ml.types.HashSparseVector;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.LabelAlphabet;
import edu.fudan.nlp.pipe.Pipe;
/**
 * 大规模层次化多类分类，类别数很大，因此权重l向量用稀疏数组表示
 * @author xpqiu
 *
 */
public class Linear extends AbstractClassifier implements Serializable {

	private static final long serialVersionUID = -1599891626397888670L;
	/**
	 * 特征转换器
	 */
	public Pipe pipe;
	/**
	 * 特征权重向量数组，每一类对应一个向量
	 */
	public HashSparseVector[] weights;
	/**
	 * 推理器
	 */
	public Inferencer inf;
	/**
	 * 特征向量生成器
	 */
	public BaseGenerator gen;
	/**
	 * 字典管理器
	 */
	public AlphabetFactory factory;

	public Linear(HashSparseVector[] weights, Inferencer msolver) {	
		this.weights = weights;
		this.inf = msolver;
		this.inf.isUseTarget(false);
	}
	
	public Linear(HashSparseVector[] weights, Inferencer msolver, 
			BaseGenerator gen, Pipe pipes, AlphabetFactory af) {
		this.weights = weights;
		this.pipe = pipes;
		this.gen = gen;
		this.inf = msolver;
		this.inf.isUseTarget(false);
		this.factory = af;
		this.factory.setStopIncrement(true);
	}

	
	public String getLabel(Instance instance){
		Results pred = (Results) inf.getBest(instance, 1);
		LabelAlphabet labelAlphabet = factory.DefaultLabelAlphabet();
		return labelAlphabet.lookupString(pred.getPredAt(0));
	}
	/**
	 * 得到前n个结果
	 * @param instance
	 * @param nBest
	 * @return 标签和相应的概率
	 */
	public Predict getPreditProb(Instance instance,int nBest){
		Results ress = (Results) inf.getBest(instance, -1);
		ress.normalize();
		LabelAlphabet labelAlphabet = factory.DefaultLabelAlphabet();
		Predict pred = new Predict(nBest);
		for(int i=0;i<nBest;i++){
			String label = (String) labelAlphabet.lookupString((Integer)ress.getPredAt(i));
			pred.set(i, label, ress.getScoreAt(i));
		}
		return pred;
	}

	
	/**
	 * 得到前n个结果
	 * @param instance
	 * @param nBest
	 * @return 标签和相应的得分
	 */
	public Predict getPredit(Instance instance,int nBest){
		Results ress = (Results) inf.getBest(instance, nBest);
		LabelAlphabet labelAlphabet = factory.DefaultLabelAlphabet();
		Predict pred = new Predict(nBest);
		for(int i=0;i<nBest;i++){
			String label = (String) labelAlphabet.lookupString((Integer)ress.getPredAt(i));
			pred.set(i, label, ress.getScoreAt(i));
		}
		return pred;
	}
	
	/**
	 * 得到类标签
	 * @param idx 数字型的分类结果，类标签对应的索引
	 * @return
	 */
	public String getLabel(int idx) {
		LabelAlphabet labelAlphabet = factory.DefaultLabelAlphabet();
		return labelAlphabet.lookupString(idx);
	}
	
	@Override
	public TResult predict(Instance instance,int nbest) {		
		return inf.getBest(instance, nbest);
	}
	
	@Override
	public TResult predict(Instance instance) {
		return predict(instance,1);
	}
	
	/**
	 * 将模型存储到文件
	 * @param file
	 * @throws IOException
	 */
	public void saveTo(String file) throws IOException {
		File f = new File(file);
		File path = f.getParentFile();
		if(path!=null&&!path.exists()){
			path.mkdirs();
		}
		ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(
				new BufferedOutputStream(new FileOutputStream(file))));
		out.writeObject(this);
		out.close();
	}

	public static Linear loadFrom(String file) throws IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(
				new BufferedInputStream(new FileInputStream(file))));
		Linear cl = (Linear) in.readObject();
		in.close();
		return cl;
	}
	
	
	public void setPipe(Pipe pipe) {
		this.pipe = pipe;		
	}
	public Pipe getPipe() {
		return pipe;		
	}

	@Override
	public int classify(Instance instance) {
		Results pred = (Results) inf.getBest(instance, 1);
		return pred.getPredAt(0);
	}
}
