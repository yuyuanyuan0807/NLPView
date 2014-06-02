package edu.fudan.nlp.corpus;
/**
 * 树的label节点
 * @author jszhao
 * @version 1.0
 * @since FudanNLP 1.5
 */
public class Node {
	public boolean isCoordinative;
	/**
	 * ctb文件中标记的类型
	 */
	public String ctbClass;
	
	private Node core;
	private String tag;
	private String data;
	private int id;
	private String depClass;
	public Node(){
		isCoordinative = false;
	}
	public Node(String tag,String data,int id){
		this.tag = tag;
		this.data = data;
		this.id = id;
	}
	public Node(Node core,String tag,String data,int id){
		this.core = core;
		this.tag = tag;
		this.data = data;
		this.id = id;
	}
	public Node(Node core,String tag,String data,int id,String depClass){
		this.core = core;
		this.tag = tag;
		this.data = data;
		this.id = id;
		this.depClass = depClass;
	}

	public String getDepClass(){
		return this.depClass;
	}
	
	public void setDepClass(String depClass){
		this.depClass = depClass;
	}
	
	public Node getCore(){
		return this.core;
	}

	public String getTag(){
		return this.tag;
	}
	public String getData(){
		return this.data;
	}
	public int getId(){
		return this.id;
	}
	
	public void setCore(Node core){
		this.core = core;
	}
	public void setTag(String tag){
		this.tag = tag;
	}
	public void setData(String data){
		this.data = data;
	}
	public void setId(int id){
		this.id = id;
	}
}
