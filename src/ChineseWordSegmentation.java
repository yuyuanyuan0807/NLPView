




import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;



public class ChineseWordSegmentation {
	
	public String WordSegmentation(String News) throws Exception{ 
		 String segmentation="";  
		//CWSTagger tag = new CWSTagger("./models/seg.m");
		
				CWSTagger tag3 = new CWSTagger("./models/seg.m", new Dictionary("./models/dict_ambiguity.txt",true));
				//尽量满足词典，比如词典中有“成立”“成立了”和“了”, 会使用Viterbi决定更合理的输出
				//System.out.println("\n使用不严格的词典的分词：");
				String str3 = News;
				String s3 = tag3.tag(str3);
				System.out.println(s3);
				segmentation=s3;  
				 return segmentation;  
		
	}

}
