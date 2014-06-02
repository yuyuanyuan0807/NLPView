


import java.util.ArrayList;
import java.util.HashMap;

import edu.fudan.nlp.cn.tag.NERTagger;


public class NamedEntityRecognition {	


	 public ArrayList<String> GetKeyword(String news) throws Exception{  
		 ArrayList<String> keywords=new ArrayList<String>();  
		NERTagger tag = new NERTagger("./models/seg.m","./models/pos.m");
		String str1 = news;
		HashMap<String, String> map = new HashMap<String, String>();
		tag.tag(str1,map);
		//System.out.println(map);
		//map = tag.tagFile(news);
	String str=map.toString();
		System.out.println(str);
		keywords.add(str);  
		System.out.println("Done!");
		 return keywords;  
	}


}
