package edu.fudan.nlp.cn.tag.Format;

import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;

public class SimpleFormatter {
	public static String format(InstanceSet testSet, String[][] labelsSet) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < testSet.size(); i++) {
			Instance inst = testSet.getInstance(i);
			String[] labels = labelsSet[i];
			sb.append(format(inst, labels));
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String format(Instance inst, String[] labels) {

		StringBuilder sb = new StringBuilder();
		String[][] data = (String[][]) inst.getSource();

		for (int i = 0; i < data[0].length; i++) {
			sb.append(data[0][i]);
			sb.append("\t");
			sb.append(labels[i]);
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String format(InstanceSet testSet, String[][] labelsSet, String[][] target) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < testSet.size(); i++) {
			Instance inst = testSet.getInstance(i);
			String[] labels = labelsSet[i];
			sb.append(format(inst, labels, target[i]));
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String format(Instance inst, String[] labels, String[] target) {

		StringBuilder sb = new StringBuilder();
		String[][] data = (String[][]) inst.getSource();

		for (int j = 0; j < data.length; j++) {
			for (int i = 0; i < data[j].length; i++) {
				sb.append(data[j][i]);
				sb.append(" ");
			}
			sb.append(target[j]);
			sb.append(" ");
			sb.append(labels[j]);
			//			sb.append(target[j].substring(0, 1));
			//			sb.append(" ");
			//			sb.append(labels[j].substring(0, 1));
			sb.append("\n");
		}
		return sb.toString();
	}
}
