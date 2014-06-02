package edu.fudan.nlp.pipe.seq.templet;

import java.io.Serializable;
import java.util.Arrays;

import edu.fudan.ml.types.FeatureAlphabet;
import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.cn.Chars;

/**
 * 当前位置固定窗口下，字符串的组合类型
 * 
 * @see edu.fudan.nlp.cn.Chars#getType(String)
 * @author xpqiu
 * 
 */
public class CharRangeTemplet implements Serializable, Templet {

	private static final long serialVersionUID = 3572735523891704313L;
	private int id;
	private int[] position;
	private int minLen = 5;

	public CharRangeTemplet(int id, int[] pos) {
		this.id = id;
		Arrays.sort(pos);
		this.position = pos;
	}

	
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int generateAt(Instance instance, FeatureAlphabet features, int pos,
			int... numLabels) {
		String[][] data = ( String[][]) instance.getData();
		int len = data[0].length;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(id);
		sb.append(':');
		for (int i = 0; i < position.length; i++) {

			if (pos + position[i] < 0) {
				if(len<minLen)//对于长度过小的句子，不考虑开始、结束特征
					return -1;
				sb.append("/B");
				sb.append(position[i]);
			} else if (pos + position[i] >= len) {
				if(len<minLen)//对于长度过小的句子，不考虑开始、结束特征
					return -1;
				sb.append("/E");
				sb.append(position[i]);
			} else {
				String str = data[0][pos + position[i]]; //这里数据行列和模板中行列相反
				// 得到字符串类型
				String type = Chars.getTypeSet(str);
				sb.append("/");
				sb.append(position[i]);
				sb.append(type);
				
			}
		}

		int index = features.lookupIndex(sb.toString(),numLabels[0]);
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
