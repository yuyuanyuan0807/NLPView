package edu.fudan.ml.classifier.knn;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;
import edu.fudan.ml.types.TagScore;
import edu.fudan.nlp.pipe.Pipe;
import edu.fudan.nlp.pipe.String2Dep;
import edu.fudan.nlp.similarity.ISimilarity;

public class KNN implements Serializable  {

	private static final long serialVersionUID = 4459814160943364300L;
	
	private ISimilarity sim;
	
	/**
	 * 特征转换器
	 */
	protected Pipe pipe;
	
	/**
	 * KNN模型
	 */
	protected InstanceSet KNNModel; 
	
	/**
	 * 初始化
	 * @param model
	 */
	public KNN(InstanceSet model,ISimilarity sim){
		try{
			KNNModel = model;
			pipe = new String2Dep();
			pipe.process(KNNModel);
			this.sim = sim;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("KNN model initial error!");
		}
	}
	
	/**
	 * 初始化
	 * @param model
	 * @param p
	 */
	public KNN(InstanceSet model, Pipe p,ISimilarity sim){
		try{
			KNNModel = model;
			pipe = p;
			pipe.process(KNNModel);
			this.sim = sim;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("KNN model initial error!");
		}
	}

	/**
	 * 分类，返回标签，格式可自定义
	 * @param instance
	 * @return
	 * @throws Exception 
	 */
	public Object classify(Instance instance, int k) throws Exception{
		pipe.addThruPipe(instance);
		
		List<TagScore> tagScoreList = new Vector<TagScore>();
		
		for(int i = 0; i < KNNModel.size(); i++){
			Instance curInst = KNNModel.get(i);
			
//			if(((String)curInst.getSource()).equals((String)instance.getSource()))
//				return curInst.getTarget();
					
			
			double score = sim.calc(instance, curInst);
			
			int j = 0;
			
			for(; j < tagScoreList.size(); j++)
				if(tagScoreList.get(j).getScore() < score){
					tagScoreList.add(j, new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));
					break;
				}
			
			if(j == tagScoreList.size() && tagScoreList.size() < k)
				tagScoreList.add(new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));

			if(tagScoreList.size() > k)
				tagScoreList = tagScoreList.subList(0, k);
		}
		
		for(int i = 0; i < tagScoreList.size(); i++)
			for(int j = i + 1; j < tagScoreList.size(); j++){
				String tagi = (String)tagScoreList.get(i).getTag();
				String tagj = (String)tagScoreList.get(j).getTag();
				if(tagi.equals(tagj)){
					tagScoreList.get(i).setScore(tagScoreList.get(i).getScore() + tagScoreList.get(j).getScore());
					tagScoreList.get(i).setTimes(tagScoreList.get(i).getTimes() + 1);
					tagScoreList.get(i).addAllSource(tagScoreList.get(j).getSource());
					tagScoreList.remove(j);
					j--;
				}
			}
			
		Object selection = null;
		double score = 0.0;
		for(int i = 0; i < tagScoreList.size(); i++){
			TagScore cur = tagScoreList.get(i);
			if(cur.getScore() > score){
				score = cur.getScore();
				selection = cur.getTag();
			}
		}
		
		instance.setTarget(selection);
		
		return selection;
	}
	
	/**
	 * 返回更详细的分类信息
	 * 返回结果按分数排列，最多返回k种分类可能
	 * 
	 * @param instance
	 * @return
	 * @throws Exception 
	 */
	public List<TagScore> detailClassify(Instance instance, int k) throws Exception{
		int minSize = 10;
		
		if(minSize < k)
			minSize = k;
		
		pipe.addThruPipe(instance);
		
		List<TagScore> tagScoreList = new Vector<TagScore>();
		
		for(int i = 0; i < KNNModel.size(); i++){
			Instance curInst = KNNModel.get(i);
			
//			if(((String)curInst.getSource()).equals((String)instance.getSource()))
//				return curInst.getTarget();
					
			
			double score = sim.calc(instance, curInst);
			
			int j = 0;
			
			for(; j < tagScoreList.size(); j++)
				if(tagScoreList.get(j).getScore() < score){
					tagScoreList.add(j, new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));
					break;
				}
			
			if(j == tagScoreList.size() && tagScoreList.size() < minSize)
				tagScoreList.add(new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));
			
			if(tagScoreList.size() > minSize)
				tagScoreList = tagScoreList.subList(0, minSize);
		}
		
		for(int i = 0; i < tagScoreList.size(); i++)
			for(int j = i + 1; j < tagScoreList.size(); j++){
				String tagi = (String)tagScoreList.get(i).getTag();
				String tagj = (String)tagScoreList.get(j).getTag();
				if(tagi.equals(tagj)){
					if(tagScoreList.get(i).getTimes() < k){
						tagScoreList.get(i).setScore(tagScoreList.get(i).getScore() + tagScoreList.get(j).getScore());
						tagScoreList.get(i).setTimes(tagScoreList.get(i).getTimes() + 1);
						tagScoreList.get(i).addAllSource(tagScoreList.get(j).getSource());
					}
					tagScoreList.remove(j);
					j--;
				}
			}
			
		Object selection = null;
		double score = 0.0;
		List<TagScore> sortedList = new Vector<TagScore>();
		
		for(int i = 0; i < tagScoreList.size(); i++){
			TagScore cur = tagScoreList.get(i);
			double curScore = cur.getScore();
			if(curScore > score){
				score = cur.getScore();
				selection = cur.getTag();
			}
			int j = 0;
			for(; j < sortedList.size(); j++)
				if(curScore > sortedList.get(j).getScore()){
					sortedList.add(j, cur);
					break;
				}
			if(j == sortedList.size())
				sortedList.add(cur);
		}
		
		instance.setTarget(selection);
		
		return sortedList;
	}
		
	/**
	 * 分类测试，输出测试结果的相关信息
	 * @param instance
	 * @param k
	 * @return
	 * @throws Exception
	 */
	public boolean classifyTest(Instance instance, int k) throws Exception {
		pipe.addThruPipe(instance);
		
		List<TagScore> tagScoreList = new Vector<TagScore>();
		
		for(int i = 0; i < KNNModel.size(); i++){
			Instance curInst = KNNModel.get(i);
			double score = sim.calc(instance, curInst);
			/*
			if(((String)curInst.getSource()).equals(instance.getSource())){
				tagScoreList.clear();
				tagScoreList.add(new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));
				break;
			}
			*/
			/*
			if(((String)curInst.getSource()).equals(instance.getSource())){
				continue;
			}
			*/
			
			int j = 0;
			
			for(; j < tagScoreList.size(); j++)
				if(tagScoreList.get(j).getScore() < score){
					tagScoreList.add(j, new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));
					break;
				}
			
			if(j == tagScoreList.size() && tagScoreList.size() < k)
				tagScoreList.add(new TagScore(curInst.getTarget(),score, (String)curInst.getSource()));
			
			if(tagScoreList.size() > k)
				tagScoreList = tagScoreList.subList(0, k);
		}
		
		
		for(int i = 0; i < tagScoreList.size(); i++)
			for(int j = i + 1; j < tagScoreList.size(); j++){
				String tagi = (String)tagScoreList.get(i).getTag();
				String tagj = (String)tagScoreList.get(j).getTag();
				if(tagi.equals(tagj)){
					tagScoreList.get(i).setScore(tagScoreList.get(i).getScore() + tagScoreList.get(j).getScore());
					tagScoreList.get(i).setTimes(tagScoreList.get(i).getTimes() + 1);
					tagScoreList.get(i).addAllSource(tagScoreList.get(j).getSource());
					tagScoreList.remove(j);
					j--;
				}
			}
			
		String selection = null;
		double score = 0.0;
		List<String> simList = null;
		for(int i = 0; i < tagScoreList.size(); i++){
			TagScore cur = tagScoreList.get(i);
			
			if(cur.getScore() >= score){
				score = cur.getScore();
				selection = (String)cur.getTag();
				simList = cur.getSource();
			}
			
			if(simList.get(0).equals((String)instance.getSource()))
				break;
		}
		
		String tar = (String)instance.getTarget();
		boolean isRight = selection.equals(tar);
		
		if(!isRight){
			System.out.println(instance.getSource() + " " + isRight);
			for(int i = 0; i < simList.size(); i++){
				System.out.println(simList.get(i));
			}
			System.out.println();
		}
		
		//instance.setTarget(selection);
		
		return isRight;
	}

	
}
