package edu.fudan.nlp.cn.tag.Format;

import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;
/**
 * 将BMES标签转为成词的序列
 * @author xpqiu
 *
 */
public class Seq2String {

	/**
	 * 将BMES标签转为#delim#隔开的字符串
	 * @param instSet 样本集
	 * @param labelsSet  标签集
	 * @param delim 字之间的间隔符
	 * @return
	 */
	public static String format(InstanceSet instSet, String[][] labelsSet, String delim) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < instSet.size(); i++) {
			Instance inst = instSet.getInstance(i);
			String[] labels = labelsSet[i];
			sb.append(format(inst, labels,delim));
		}
		return sb.toString();
	}

	/**
	 * 将BMES标签转为#delim#隔开的字符串
	 * @param inst 样本
	 * @param labels  标签
	 * @param delim 字之间的间隔符
	 * @return
	 */
	public static String format(Instance inst, String[] labels,String delim) {
		String[][] data = (String[][]) inst.getSource();
		int len = data[0].length;
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < len; j++) {
			String label = labels[j];
			String w = data[0][j];			
			sb.append(w);
			if(w.equals(" "))
				continue;
			if (label.equals("E") || label.equals("S")) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

}
