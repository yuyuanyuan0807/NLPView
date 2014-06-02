package edu.fudan.nlp.pipe.seq;

import java.io.Serializable;
import java.util.Arrays;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.LabelAlphabet;
import edu.fudan.nlp.pipe.Pipe;

/**
 * 将字符序列转换成特征序列 因为都是01特征，这里保存的是索引号
 * 
 * @author xpqiu
 * 
 */
public class DictLabel extends Pipe implements Serializable {

	private static final long serialVersionUID = -8634966199670429510L;

	protected Dictionary dict;
	protected LabelAlphabet labels;
	
	public DictLabel(Dictionary dict, LabelAlphabet labels) {
		this.dict = dict;
		this.labels = labels;
	}
	
	public void setDict(Dictionary dict)	{
		this.dict = dict;
	}

	public void addThruPipe(Instance instance) throws Exception {
		String[][] data = (String[][]) instance.getData();
		
		int length = data[0].length;
		int[][] dicData = new int[length][labels.size()];				
		
		int indexLen = dict.getIndexLen();
		for (int i = 0; i < length; i++) {
			if (i + indexLen <= length) {
				String s = getNextN(data, i, indexLen);
				int[] index = dict.getIndex(s);
				if(index != null) {
					for(int k = 0; k < index.length; k++) {
						int n = index[k];
						if(n == indexLen) { //下面那个check函数的特殊情况，只为了加速
							label(i, indexLen, dicData);
							i = i + n - 1;
							break;
						}
						if(check(i, n, length, data, dicData)) {
							i = i + n - 1;
							break;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < length; i++) 
			if (hasWay(dicData[i]))
				for(int j = 0; j < dicData[i].length; j++)
					dicData[i][j]++;

		instance.setDicData(dicData);
	}
	
	private boolean hasWay(int[] ia) {
		for(int i = 0; i < ia.length; i++) {
			if(ia[i] == -1)
				return true;
		}
		return false;
	}
	
	private boolean check(int i, int n, int length, String[][] data, int[][] tempData) {
		if (i + n <= length) {
			String s = getNextN(data, i, n);
			if (dict.contains(s)) {
				label(i, n, tempData);	
				return true;
			}
		}
		return false;
	}
	
	private void label(int i, int n, int[][] tempData) {
		// 下面这部分依赖{1=B,2=M,3=E,0=S}	
		if (n == 1)
			tempData[i][labels.lookupIndex("S")] = -1;
		else {
			tempData[i][labels.lookupIndex("B")] = -1;
			for (int j = i + 1; j < i + n - 1; j++)
				tempData[j][labels.lookupIndex("M")] = -1;
			tempData[i + n - 1][labels.lookupIndex("E")] = -1;
		}
	}

	public String getNextN(String[][] data, int index, int N) {
		StringBuffer sb = new StringBuffer();
		for (int i = index; i < index + N; i++) 
			sb.append(data[0][i]);
//		System.out.println(sb.toString());
		return sb.toString();
	}

}
