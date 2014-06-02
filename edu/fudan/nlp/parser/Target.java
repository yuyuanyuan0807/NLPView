package edu.fudan.nlp.parser;

import java.util.Arrays;

/**
 * 句法分析的标签，包括依赖词的id和依赖关系
 * @author 
 *
 */
public class Target {
	private String[] relations;
	private int[] heads;
	
	public Target(int[] heads ,String[] relations){
		this.heads = heads;
		this.relations = relations;
	}
	public Target(int len) {
		relations = new String[len];
		heads = new int[len];
		Arrays.fill(heads, -1);
	}
	public String getDepClass(int idx){
		return this.relations[idx];
	}
	public int getHead(int idx){
		return this.heads[idx];
	}

	public void setDepClass(int i, String relations){
		this.relations[i] = relations;
	}
	public void setHeads(int i, int heads){
		this.heads[i] = heads;
	}
	public int size() {
		
		return heads.length;
	}
	public String[] getRelations() {
		return relations;
	}
	public int[] getHeads() {
		return heads;
	}
}
