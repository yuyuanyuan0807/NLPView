package edu.fudan.nlp.cn;

import java.util.regex.Pattern;

/**
 * 中文词性操作类
 * @author xpqiu
 * @version 1.0
 * @since FudanNLP 1.5
 */
public class Tags {

	
	static Pattern entitiesPattern  = Pattern.compile("人名|地名|机构名|实体名");
	/**
	 * 判断词性是否为一个实体，包括：人名|地名|机构名|实体名。
	 * @param pos 词性
	 * @return true,false
	 */
	public static boolean isEntiry(String pos) {
		return (entitiesPattern.matcher(pos).find());
	}

}
