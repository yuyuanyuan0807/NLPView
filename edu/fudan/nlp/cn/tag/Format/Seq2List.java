package edu.fudan.nlp.cn.tag.Format;

import java.util.ArrayList;
import java.util.List;

import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;

public class Seq2List {

	public static String format(InstanceSet testSet, String[][] labelsSet) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < testSet.size(); i++) {
			Instance inst = testSet.getInstance(i);
			String[] labels = labelsSet[i];
			sb.append(format(inst, labels));
		}
		return sb.toString();
	}

	public static ArrayList<String> format(Instance inst, String[] labels) {
		String[][] data = (String[][]) inst.getSource();
		int len = data[0].length;
		ArrayList<String> res = new ArrayList<String>(len);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < len; j++) {
			String label = labels[j];
			String w = data[0][j];
			if(w.equals(" ")){//空格特殊处理
				if(sb.length()>0){
					res.add(sb.toString());
					sb = new StringBuilder();
				}
				continue;
			}
			sb.append(w);
			if (label.equals("E") || label.equals("S")) {
				res.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		if(sb.length()>0){
			res.add(sb.toString());
		}
		return res;
	}
	
	
}
