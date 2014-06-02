package edu.fudan.nlp.pipe.seq.templet;

import java.util.Arrays;

import edu.fudan.ml.types.FeatureAlphabet;
import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.cn.Chars;

/**
 * 字符串中字符组合模板
 * 
 * @author xpqiu
 * 
 */
public class StringTypeTemplet implements Templet {

	private static final long serialVersionUID = -4911289807273417691L;
	private int id;
	private int minLen = 2;

	public StringTypeTemplet(int id) {
		this.id = id;
	}



	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int generateAt(Instance instance, FeatureAlphabet features, int pos,
			int... numLabels) {
		String[][] data = ( String[][]) instance.getData();
		
		int len = data[0][pos].length();

		if(len<minLen)//对于长度过小的句子，不考虑开始、结束特征
			return -1;
		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(':');
		
		String str = data[0][pos]; //这里数据行列和模板中行列相反
		String type;
		if(Chars.isLetterOrDigitOrPunc(str)){
			if(str.length()>4 && str.startsWith("http:"))
				type = "URL";
			else if(str.length()>4&&str.contains("@"))
				type  = "Email";
			else
				type = "EN";
		}else
			type = "CN";
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
