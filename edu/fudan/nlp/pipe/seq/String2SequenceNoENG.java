package edu.fudan.nlp.pipe.seq;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.cn.Chars;
import edu.fudan.nlp.cn.ChineseTrans;
import edu.fudan.nlp.pipe.Pipe;
/**
 * 将字符串转为序列，不对英文、特殊字符进行预处理
 *  String => String[1][]
 * "abc"  => {{"a","b","c"}}
 * @author xpqiu
 *
 */
public class String2SequenceNoENG extends Pipe implements Serializable {
	
	private static final long serialVersionUID = 45050330616309396L;
	
	public String2SequenceNoENG() {
	}
	
	public void addThruPipe(Instance inst) {
		String str = (String) inst.getData();
		String[][] data = new String[1][str.length()];
		for(int i = 0; i < str.length(); i++)
			data[0][i] = str.substring(i,i+1);
		inst.setData(data);
	}
	
	/*
	 * String2Sequence虽然处理了一些复杂情况,但是有Bug
	 * String2SimpleSequence很好的处理了一般的情况
	 */	
	public static String genSequence(String sent){
		char[] tag = Chars.getTag_Simple(sent);
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < sent.length(); j++) {
			char c = sent.charAt(j);	
			
			if(tag[j]=='h' || tag[j]=='p'){//当前是汉字或标点
				sb.append(c);
				sb.append("\n");
			} else if(tag[j]=='s') {
				//空格不要
			} else if(tag[j]=='e'){//当前是英文
				sb.append(c);
				if(j + 1 < sent.length() && tag[j+1] == 'e'){//下一个也是英文
					continue;
				}else{//下一个不是英文
					sb.append("\n");
				}
			} else if(tag[j]=='d'){//当前是数字
				sb.append(c);
				if(j + 1 < sent.length() && tag[j+1] == 'd'){//下一个也是数字
					continue;
				}else{//下一个不是数字
					sb.append("\n");
				}
			}	
		}
		sb.append('\n');
		return sb.toString();
	}
	
	
	/*
	 * String2Sequence虽然处理了一些复杂情况,但是有Bug
	 * String2SimpleSequence很好的处理了一般的情况
	 * String2SimpleSequence2不将英文合并
	 */	
	public static String genSequenceEnglishSegment(String sent){
		char[] tag = Chars.getTag_Simple(sent);
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < sent.length(); j++) {
			char c = sent.charAt(j);	
			
			if(tag[j]=='h' || tag[j]=='p' || tag[j] == 'e'){//当前是汉字或标点或英文
				sb.append(c);
				sb.append("\n");
			} else if(tag[j]=='s') {
				//空格不要
			} else if(tag[j]=='d'){//当前是数字
				sb.append(c);
				if(j + 1 < sent.length() && tag[j+1] == 'd'){//下一个也是数字
					continue;
				}else{//下一个不是数字
					sb.append("\n");
				}
			}	
		}
		sb.append('\n');
		return sb.toString();
	}
	
	public static String genMixedSequence(String sent)	{
		char[] tok = sent.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tok.length; i++)	{
			
		}
		return sb.toString();
	}
}
