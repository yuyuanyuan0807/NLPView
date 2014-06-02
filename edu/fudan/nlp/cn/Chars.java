package edu.fudan.nlp.cn;

import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * 中文字符操作类
 * @author xpqiu
 * @version 1.0
 * @since FudanNLP 1.5
 */
public class Chars {
	
	/**
	 * 字符类型
	 * 汉字 字母 数字 标点 :空格;
	 * @author xpqiu
	 *
	 */
	public  enum CharType {  
		  HAN, CHAR, NUM, PUN, BLANK
		}  
	
	/**
	 * 字符串类型
	 * D:纯数字串 E：纯字母 M：数字字母混合 O:其他
	 * @author xpqiu
	 *
	 */
	public enum StringType{
		NUM, CHAR, MIXED, OTHER
	}
	
	/**
	 * 半角或全角数字英文
	 * 
	 * @param str
	 * @return 0,1
	 */
	public static boolean isChar(String str) {
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!isChar(c[i]))
				return false;
		}
		return true;
	}

	/**
	 * 半角或全角数字英文
	 * 
	 * @param c
	 * @return 0,1
	 */
	public static boolean isChar(char c) {
		if ((c > 32 && c < 127) || c == 12288 || (c > 65280 && c < 65375)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEnglishChar(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		else
			return false;
	}

	public static boolean isChineseChar(char c) {
		if (c > 65280 && c < 65375)
			return true;
		else
			return false;
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            全角或半角字符串
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 得到语句的字符信息,没有复杂处理
	 * 
	 * @param str
	 * @return 
	 */
	public static char[] getTag_Simple(String str) {
		char[] tag = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (Character.isLowerCase(c) || Character.isUpperCase(c))
				tag[i] = 'e';
			else if (Character.isDigit(c))
				tag[i] = 'd';
			else if (Character.isWhitespace(c) || Character.isSpaceChar(c))
				tag[i] = 's';
			else if (Pattern.matches("\\pP|\\pS", c + "")) {
				tag[i] = 'p';
			} else
				tag[i] = 'h';
		}
		return tag;
	}

	public static boolean isLetterOrDigitOrPunc(char ch) {
		return Character.isLowerCase(ch) || Character.isUpperCase(ch)
		// || Character.isDigit(ch)
				|| Pattern.matches("\\pP|\\pS", ch + ""); 
		//TODO:可以修改为Java7中的isLetterOrDigit
	}
	
	public static boolean isLetterOrDigitOrPunc(String str) {
		return Pattern.matches("(\\w|\\pP|\\pS|\\s)+", str);  
		//TODO:可以修改为Java7中的isLetterOrDigit
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPunc(String str) {

			return Pattern.matches("(\\pP|\\pS)+", str);  
			//TODO:可以修改为Java7中的isLetterOrDigit
	}

	/**
	 * 得到语句的字符信息
	 * 
	 * @param str
	 * @return h:汉字 e:字母 d：数字 p：标点 s:空格
	 */
	public static CharType[] getTag(String str) {
		CharType[] tag = new CharType[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
					|| (c >= 'a' + 65248 && c <= 'z' + 65248)
					|| (c >= 'A' + 65248 && c <= 'Z' + 65248)) {
				tag[i] = CharType.CHAR;
			} else if (c == 12288 || c == 32) {
				tag[i] = CharType.BLANK;
			} else if ((c >= '0' && c <= '9') || (c >= '0' + 65248 && c <= '9' + 65248)) {
				tag[i] = CharType.NUM;
			}else if ("一二三四五六七八九十零○".indexOf(c) != -1) {
				tag[i] = CharType.NUM;
			} else if ("/—-()。!,\"'（）！，””<>《》：:#@￥$%^…&*！、.%".indexOf(c) != -1) {
				tag[i] = CharType.PUN;
			} else {
				tag[i] =CharType.HAN;
			}
		}
		return tag;
	}

	/**
	 * *
	 * 
	 * @param str
	 * @return 字符类型序列
	 */
	public static String getType(String str) {
		CharType[] tag = getTag(str);
		String s = type2String(tag);		
		return s;
	}
	
	public static String getTypeSet(String str) {
		CharType[] tag = getTag(str);
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<tag.length;i++){
			set.add(tag[i].toString());			
		}	
		return set.toString();
	}
	
	public static String type2String(CharType[] tag) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<tag.length;i++){
			sb.append(tag[i]);
			if(i<tag.length-1)
				sb.append(' ');
		}
		return sb.toString();
	}

}
