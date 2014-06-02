package edu.fudan.nlp.pipe.seq.templet;

import edu.fudan.ml.types.FeatureAlphabet;
import edu.fudan.ml.types.Instance;

/**
 * 字符串中子序列的模版
 * 
 * @author xpqiu
 * @since FudanNLP 1.5
 * @version 1.0
 */
public class CharInStringTemplet implements Templet {

	private static final long serialVersionUID = -2535980449084713920L;
	private int id;
	/**
	 * 字符串中子序列的起始位置
	 */
	private int position;
	private int minLen = 2;
	/**
	 * 字符串中子序列的长度
	 */
	private int plen;

	/**
	 * 构造函数
	 * @param id 模版
	 * @param pos 子序列起始位置
	 * @param len 子序列起始长度
	 */
	public CharInStringTemplet(int id, int pos,int len) {
		this.id = id;
		this.position = pos;
		this.plen = len;
	}
	/**
	  * 构造函数
	 * @param id 模版
	 * @param pos 子序列起始位置
	 * @param len 子序列起始长度
	 * @param minlen 本模版起作用的最小长度
	 */
	public CharInStringTemplet(int id, int pos,int len,int minlen) {
		this.id = id;
		this.position = pos;
		this.plen = len;
		this.minLen = minlen;
	}


	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int generateAt(Instance instance, FeatureAlphabet features, int pos,
			int... numLabels) {
		String[][] data = ( String[][]) instance.getData();
		
		int len = data[0][pos].length();
		
		

		if(len<minLen)//对于长度过小的句子
			return -1;
		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(':');
		int indx = position;
		if(indx<0)
			indx = len+indx;
		int endIdx = indx+plen;
		if(endIdx>len)
			endIdx = len;
		String str = data[0][pos].substring(indx,endIdx); //这里数据行列和模板中行列相反				
		sb.append(str);	
		
		int index = features.lookupIndex(sb.toString(), numLabels[0]);
		return index;
	}

	@Override
	public int getOrder() {
		return 0;
	}

	public int[] getVars() {
		return new int[] { 0 };
	}

	public int offset(int... curs) {
		return 0;
	}

}
