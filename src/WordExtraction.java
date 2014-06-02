import edu.fudan.nlp.app.keyword.Extractor;
import edu.fudan.nlp.app.keyword.WordExtract;
import java.util.ArrayList;  
import java.util.Map;  

import javax.swing.JOptionPane;

  
import edu.fudan.nlp.cn.tag.CWSTagger;  
import edu.fudan.nlp.corpus.StopWords; 



public class WordExtraction {
	 
    public ArrayList<String> GetKeyword(String News,int keywordsNumber) throws Exception{  
        ArrayList<String> keywords=new ArrayList<String>();  
        StopWords sw= new StopWords("./models/stopwords");  
        CWSTagger seg = new CWSTagger("./models/seg.m");  
        Extractor key = new WordExtract(seg,sw);  
          
        //you need to set the keywords number, here you will get 10 keywords  
        Map<String,Integer> ans = key.extract(News, keywordsNumber);  
          
        for (Map.Entry<String, Integer> entry : ans.entrySet()) {  
           String keymap = entry.getKey().toString();  
           String value = entry.getValue().toString();  
           keywords.add("key=" + keymap + " value=" + value);  
           System.out.println("key=" + keymap + " value=" + value); 
        }  
        return keywords;  
    
}  
	    
}
