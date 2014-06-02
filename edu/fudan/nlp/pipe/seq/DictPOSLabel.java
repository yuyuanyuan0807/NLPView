package edu.fudan.nlp.pipe.seq;

import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.LabelAlphabet;
import edu.fudan.nlp.pipe.Pipe;
import edu.fudan.util.exception.LoadModelException;

/**
 * 词性字典预处理
 * 
 * @author xpqiu
 * 
 */
public class DictPOSLabel extends DictLabel  implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5457382370544508743L;

	public DictPOSLabel(Dictionary dict, LabelAlphabet labels) throws LoadModelException {
		super(dict, labels);
		checkLabels();
	}

	private void checkLabels() throws LoadModelException {
		TreeMap<String, String[]> pos = dict.getPOSDict();
		for(String[] pp: pos.values()){
			for(String p : pp){
				if(labels.lookupIndex(p)==-1)
					throw new LoadModelException("自定义词性标签只能在下面列表中：\n" +labels.toString());
			}
		}

	}

	public void addThruPipe(Instance instance) throws Exception {
		String[] data = (String[]) instance.getData();

		int length = data.length;
		int[][] dicData = new int[length][labels.size()];				

		for(int i = 0; i < data.length; i++) {
			//			System.out.println(data[i]);
			String[] pos = dict.getPOS(data[i]);
			if(pos != null)
				for(int j = 0; j < pos.length; j++)
					dicData[i][labels.lookupIndex(pos[j])] = -1;
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
}
