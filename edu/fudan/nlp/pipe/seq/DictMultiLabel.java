package edu.fudan.nlp.pipe.seq;

import java.io.Serializable;
import java.util.Arrays;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.LabelAlphabet;

public class DictMultiLabel extends DictLabel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3659336481904520840L;

	public DictMultiLabel(Dictionary dict, LabelAlphabet labels) {
		super(dict, labels);
	}

	//函数依赖{1=B,2=M,3=E,0=S}
	//可能把s位都设为1（通路）会比较好?
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
						if(n == indexLen) 
							label(i, indexLen, dicData); //check的特殊情况，为了加速
						else	
							check(i, n, length, data, dicData);
					}
				}
			}
		}
		for (int i = 0; i < length; i++) 
			if (hasWay(dicData[i]))
				for(int j = 0; j < dicData[i].length; j++)
					dicData[i][j]++;
		
//		for(int i = 0; i < dicData.length; i++) {
//			for(int j = 0; j < dicData[i].length; j++)
//				System.out.print(dicData[i][j]);
//			System.out.println();
//		}
		
		instance.setDicData(dicData);
	}
	
	private boolean hasWay(int[] ia) {
		for(int i = 0; i < ia.length; i++) {
			if(ia[i] == -1)
				return true;
		}
		return false;
	}
	
	private void check(int i, int n, int length, String[][] data, int[][] tempData) {
		if (i + n <= length) {
			String s = getNextN(data, i, n);
			if (dict.contains(s)) {
				label(i, n, tempData);			
			}
		}
	}
	
	private void label(int i, int n, int[][] tempData) {
//		if (labels.size() == 4) {
			if (n == 1)
				tempData[i][labels.lookupIndex("S")] = -1;
			else {
				tempData[i][labels.lookupIndex("B")] = -1;
				for (int j = i + 1; j < i + n - 1; j++)
					tempData[j][labels.lookupIndex("M")] = -1;
				tempData[i + n - 1][labels.lookupIndex("E")] = -1;
			}
//		} else if (labels.size() == 2) {
//			tempData[i][labels.lookupIndex("B")] = 1;
//			for (int j = i + 1; j < i + n; j++)
//				tempData[j][labels.lookupIndex("I")] = 1;
//		}
	}

	public String getNextN(String[][] data, int index, int N) {
		StringBuffer sb = new StringBuffer();
		for (int i = index; i < index + N; i++) 
			sb.append(data[0][i]);
//		System.out.println(sb.toString());
		return sb.toString();
	}
}
