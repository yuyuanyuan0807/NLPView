package edu.fudan.nlp.parser.dep;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.fudan.ml.classifier.Results;
import edu.fudan.ml.types.HashSparseVector;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.LabelAlphabet;
import edu.fudan.nlp.parser.Sentence;
import edu.fudan.nlp.parser.Target;
import edu.fudan.util.exception.UnsupportedDataTypeException;

/**
 * 依赖句法分析器类，同时标注依赖关系类型
 * 
 * 输入单个分完词的句子(包含词性)，使用Yamada分析算法完成依存结构分析。
 * 
 * @author 
 */
public class JointParser extends YamadaParser {

	private static final long serialVersionUID = 7114734594734593632L;
	
	

	/**
	 * 构造函数
	 * 
	 * @param modelfile
	 *            模型目录
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public JointParser(String modelfile) throws ClassNotFoundException, IOException {
		super(modelfile);
	}

	private void doNext(String action,JointParsingState state){
		char act = action.charAt(0);
		String relation = action.substring(1);
		switch(act){
		case 'L':
			state.next(JointParsingState.Action.LEFT, relation);break;		
		case 'R':
			state.next(JointParsingState.Action.RIGHT, relation);break;
		default:
			System.out.println("状态动作错误");
		}

	}

	private void doNext(String action,float est1,JointParsingState state){
		char act = action.charAt(0);
		String relation = action.substring(1);
		switch(act){
		case 'L':
			state.next(JointParsingState.Action.LEFT, est1,relation);break;
		case 'R':
			state.next(JointParsingState.Action.RIGHT, est1,relation);break;
		default:
			System.out.println("状态动作错误");
		}

	}

	private Results<DependencyTree> _getBestParse(Sentence sent) throws UnsupportedDataTypeException	{
		float score = 0.0f;

		// 分析中的状态
		JointParsingState state = new JointParsingState(sent,factory);

		

		while (!state.isFinalState()) {

			Results<String> estimates;
				estimates = estimateActions(state);
	
			String action = estimates.getPredAt(0);
			if (!action.equals("S")){
				doNext(action ,state);
				score +=estimates.getScoreAt(0);
			}
			else{
				action = estimates.getPredAt(1);
				float s = estimates.getScoreAt(1);
				doNext(action,s,state);
				score +=estimates.getScoreAt(1);
			}
		}
		Results<DependencyTree> res = new Results<DependencyTree>();
		res.addPred(score, state.trees.get(0));
		return res;
	}

	/**
	 * 动作预测
	 * 
	 * 根据当前状态得到的特征，和训练好的模型，预测当前状态应采取的策略，用在测试中
	 * 
	 * @param featureAlphabet
	 *            特征名到特征ID的对应表，特征抽取时使用特征名，模型中使用特征ID，
	 * @param model
	 *            分类模型
	 * @param features
	 *            当前状态的特征
	 * @return 动作及其概率 ［［动作1，概率1］，［动作2，概率2］，［动作3，概率3］］ 动作： 1->LEFT; 2->RIGHT;
	 *         0->SHIFT
	 * @throws UnsupportedDataTypeException 
	 */
	private Results<String> estimateActions(JointParsingState state) throws UnsupportedDataTypeException {
		// 当前状态的特征
		HashSparseVector features = state.getFeatures();
		Instance inst = new Instance(features.indices());

		String pos = state.getLeftPos();
		int lpos = postagAlphabet.lookupIndex(pos);
		if(lpos==-1){
			if(defaultPOS!=null)
				lpos = postagAlphabet.lookupIndex(defaultPOS);
			else
				throw new UnsupportedDataTypeException("不支持词性："+pos);
		}
		
		LabelAlphabet actionList = factory.buildLabelAlphabet(pos);

		Results<Integer> ret = models[lpos].predict(inst,actionList.size());
		ret.normalize();
		Results<String> result =new Results<String>(2);
		float total = 0;
		for (int i = 0; i < 2; i++) {
			Integer guess = ret.getPredAt(i);
			if(guess==null) //bug：可能为空，待修改。 xpqiu
				break;
			String action = actionList.lookupString(guess);
			result.addPred(ret.getScoreAt(i), action);	
		}


		return result;
	}

	public Target jointParse(Instance inst) throws UnsupportedDataTypeException {
		Sentence sent = (Sentence) inst;

		Results<DependencyTree> res = _getBestParse(sent);
		DependencyTree dt = res.getPredAt(0);

		Target target = new Target(sent.length());

		to2HeadsArray(dt, target);

		return target;
	}
	
	private void to2HeadsArray(DependencyTree dt, Target targets) {
		for(int i = 0; i < dt.leftChilds.size(); i++)	{
			DependencyTree ch = dt.leftChilds.get(i);
			targets.setHeads(ch.id, dt.id);
			targets.setDepClass(ch.id, ch.getDepClass());
			to2HeadsArray(ch, targets);
		}
		for(int i = 0; i < dt.rightChilds.size(); i++)	{
			DependencyTree ch = dt.rightChilds.get(i);
			targets.setHeads(ch.id,dt.id);
			targets.setDepClass(ch.id,ch.getDepClass());
			to2HeadsArray(ch, targets);
		}
	}

	public DependencyTree parse2T(Sentence sent) throws UnsupportedDataTypeException{
		Results<DependencyTree> res = _getBestParse(sent);
		DependencyTree dt = res.getPredAt(0);
		return dt;
	}
	/**
	 * 得到依存句法树
	 * @param words 词数组
	 * @param pos 词性数组
	 * @return
	 * @throws UnsupportedDataTypeException 
	 */
	public DependencyTree parse2T(String[] words, String[] pos) throws UnsupportedDataTypeException{
		return parse2T(new Sentence(words, pos));
	}

	public Target parse2R(Instance inst) throws UnsupportedDataTypeException	{
		return  jointParse(inst);
	}

	public Target parse2R(String[] words, String[] pos) throws UnsupportedDataTypeException	{
		return parse2R(new Sentence(words, pos));
	}

	public String parse2String(String[] words, String[] pos,boolean b) throws UnsupportedDataTypeException {
		Target target = parse2R(words,pos);
		int[] heads = target.getHeads();
		String[] rel = target.getRelations();
		StringBuffer sb = new StringBuffer();
		if(b){
			for(int j = 0; j < words.length; j++){
				sb.append(words[j]);
				if(j<words.length-1)
					sb.append(" ");
			}
			sb.append("\n");
			for(int j = 0; j < pos.length; j++){
				sb.append(pos[j]);
				if(j<pos.length-1)
					sb.append(" ");
			}
			sb.append("\n");
			
		}
		for(int j = 0; j < heads.length; j++){
			sb.append(heads[j]);
			if(j<heads.length-1)
				sb.append(" ");
		}
		sb.append("\n");
		for(int j = 0; j < rel.length; j++){
			if(rel[j]==null)
				sb.append("核心词");
			else
				sb.append(rel[j]);
			if(j<heads.length-1)
				sb.append(" ");
		}
		return sb.toString();
	}


	/**
	 * 得到支持的依存关系类型集合
	 * @return 词性标签集合
	 */
	public Set<String> getSupportedTypes(){
		Set<String> typeset = new HashSet<String>();
		Set<String> tagset = postagAlphabet.toSet();
		Iterator<String> it = tagset.iterator();
		while(it.hasNext()){
			String tag = it.next();
			Set<String> set = factory.buildLabelAlphabet(tag).toSet();
			Iterator<String> itt = set.iterator();
			while(itt.hasNext()){
				String type = itt.next();
				if(type.length() ==1 )
					continue;
				typeset.add(type.substring(1));
			}
		}
		return typeset;
	}
	

}
