package edu.fudan.nlp.pipe;

import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.POSTagger;
import edu.fudan.nlp.parser.dep.DependencyTree;
import edu.fudan.nlp.parser.dep.JointParser;
import edu.fudan.util.exception.UnsupportedDataTypeException;

public class String2Dep extends Pipe{

	private static final long serialVersionUID = -3646974372853044208L;
	
	private static String path = "./models/";
	
	private String segFile = path + "seg.m";
	private String posFile = path + "pos.m";
	private String depFile = path + "dep.m";

	private CWSTagger cwsTagger;
	private POSTagger posTagger;
	private JointParser depParser;

	public String2Dep(){
		try{
			cwsTagger = new CWSTagger(segFile);
			posTagger = new POSTagger(posFile);
			depParser = new JointParser(depFile);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("模型初始化错误");
			System.exit(0);
		}
	}
	
	public String2Dep(CWSTagger cws, POSTagger pos, JointParser dep){
		cwsTagger = cws;
		posTagger = pos;
		depParser = dep;
	}
	
	/**
	 * 分词
	 * @param sentence
	 * @return
	 */
	public String[] seg(String sentence){
		return cwsTagger.tag2Array(sentence);
	}
	
	/**
	 * 词性标注
	 * @param words
	 * @return
	 */
	public String[] pos(String[] words){
		return posTagger.tagSeged(words);
	}
	
	/**
	 * 依存树生成
	 * @param words
	 * @param pos
	 * @return
	 * @throws UnsupportedDataTypeException 
	 */
	public DependencyTree dep(String[] words, String[] pos) throws UnsupportedDataTypeException{
		return depParser.parse2T(words, pos);
	}
	
	@Override
	public void addThruPipe(Instance inst) throws Exception {
		String data = (String)inst.getSource();
		
		String[] words = seg(data);
		String[] pos = pos(words);
		DependencyTree t = dep(words, pos);
		
		inst.setData(t);
	}

	


}
