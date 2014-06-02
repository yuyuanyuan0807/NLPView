

import java.util.ArrayList;

import edu.fudan.nlp.cn.anaphora.Anaphora;

/**
 * 指代消解实例
 * @author jszhao
 * @version 1.0
 * @since FudanNLP 1.5
 */
public class AnaphoraResolution {
	 public ArrayList<String> Resolution(String news) throws Exception{ 
		 ArrayList<String> keywords=new ArrayList<String>();  
		String str = news;
		Anaphora aa = new Anaphora("./models/seg.m","./models/pos.m","./models/ar.m");
	
		System.out.printf(aa.resultToString(str));
		keywords.add(aa.resultToString(str));
		/*String str2 = "复旦大学创建于1905年,它位于上海市，这个大学培育了好多优秀的学生。";
		String str3[] = {"复旦","大学","创建","于","1905年","，","它","位于","上海市","，","这个","大学","培育","了","好多","优秀","的","学生","。"};
		String str4[] = {"实体名","名词","动词","介词","时间短语","标点","代词","动词","实体名","标点","限定词","名词","动词","动态助词","数词","形容词","结构助词","名词","标点"};
		String str5[][][] = new String[1][2][str3.length];
		str5[0][0] = str3;
		str5[0][1] = str4;		
		System.out.printf(aa.resultToString(str5, str2));
		*/
		return keywords; 
	}

}
