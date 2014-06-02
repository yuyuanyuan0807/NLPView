package edu.fudan.ml.classifier.hier;

import edu.fudan.ml.classifier.TResult;
import gnu.trove.list.linked.TFloatLinkedList;
import gnu.trove.list.linked.TIntLinkedList;

/**
 * 用来保存中间计算结果
 * pred为预测 oracle为真实
 * 
 * @author xpqiu
 * 
 */
public class Results  implements TResult<Integer> {
	/**
	 * 记录前n个结果，默认为-1，不限制个数
	 */
	int n=-1;
	/**
	 * 预测值得分
	 */
	public TFloatLinkedList predScores;
	/**
	 * 预测值
	 */
	public TIntLinkedList predList;
	/**
	 * 真实值得分
	 */
	public TFloatLinkedList oracleScores;
	/**
	 * 真实值
	 */
	public TIntLinkedList oracleList;

	public Object other;
	private boolean normalized = false;

	public Results() {
		this(1);
	}

	public Results(int n) {
		this.n = n;
		predScores = new TFloatLinkedList();
		predList = new TIntLinkedList();
	}

	/**
	 * 记录正确标注对应的得分
	 */
	public void buildOracle() {
		oracleScores = new TFloatLinkedList();
		oracleList = new TIntLinkedList();
	}

	/**
	 * 返回插入的位置
	 * 
	 * @param score 得分
	 * @param pred 预测值
	 * @return 插入位置
	 */
	public int addPred(float score, int pred) {
		return adjust(predScores, predList, score, pred);
	}

	/**
	 * 返回插入的位置
	 * 
	 * @param score 得分
	 * @param pred 预测值
	 * @return 插入位置
	 */
	public int addOracle(float score, int pred) {
		return adjust(oracleScores, oracleList, score, pred);
	}

	private int adjust(TFloatLinkedList scores, TIntLinkedList preds, float score, int pred) {
		int i = 0;
		int max;
		if(n==-1)
			max = scores.size();
		else
			max = n>scores.size()?scores.size():n;
		
		for (i = 0; i < max; i++) {
			if (score > scores.get(i))
				break;
		}
		//TODO: 没有删除多余的信息
		if(n!=-1&&i>=n)
			return -1;
		if(i<scores.size()){
			scores.insert(i,score);
			preds.insert(i,pred);
		}else{
			scores.add(score);
			preds.add(pred);
		}
		return i;
	}

	/**
	 * 获得预测结果
	 * 
	 * @param i
	 *            位置
	 * @return 第i个预测结果；如果不存在，为-1
	 */
	public Integer getPredAt(int i) {
		if (i < 0 || i >= predList.size())
			return -1;
		return predList.get(i);
	}

	/**
	 * 获得预测结果的得分
	 * 
	 * @param i
	 *            位置
	 * @return 第i个预测结果的得分；不存在为Double.NEGATIVE_INFINITY
	 */
	public float getScoreAt(int i) {
		if (i < 0 || i >= predScores.size())
			return Float.NEGATIVE_INFINITY;
		return predScores.get(i);
	}
	
	public float getOraleScoreAt(int i) {
		return oracleScores.get(i);
	}

	/**
	 * 预测结果数量
	 * 
	 * @return 预测结果的数量
	 */
	public int size() {
		return n;
	}

	public void normalize(){

		float base = predScores.get(0)/2;
		float sum = 0;

		for(int i=0;i<predScores.size();i++){
			float s  = (float) Math.exp(predScores.get(i)/base);
			predScores.set(i, s);
			sum +=s;
		}
		for(int i=0;i<predScores.size();i++){
			float s = predScores.get(i)/sum;
//			if(s <0.001f)
//				s=0;
			predScores.set(i, s);
			
		}
	}
	
	
	
}
