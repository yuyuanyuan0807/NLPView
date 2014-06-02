


import java.util.ArrayList;

import edu.fudan.nlp.cn.ner.TimeNormalizer;
import edu.fudan.nlp.cn.ner.TimeUnit;


public class TimeExpressionRecognition {
	 public ArrayList<String> TimeRecognition(String news) throws Exception{ 
		 ArrayList<String> keywords=new ArrayList<String>();  
		String target = news;
		TimeNormalizer normalizer;
		normalizer = new TimeNormalizer("./models/TimeExp.m");
		normalizer.parse(target);
		TimeUnit[] unit = normalizer.getTimeUnit();
		for(int i = 0; i < unit.length; i++){
			System.out.println(unit[i]);
			String str=unit[i].toString();
			keywords.add(str);
		}
		return keywords; 
	}
}
