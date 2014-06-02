package edu.fudan.nlp.parser.dep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DependencyTree implements Serializable {

    private static final long serialVersionUID = -4766669720074872942L;

    public String word;
    public String pos;
    
    public int id;
    private int size=1;
    
    

    private String relation;
    public List<DependencyTree> leftChilds;
    public List<DependencyTree> rightChilds;
    /**
     * 父节点
     */
    private DependencyTree parent = null;
    
    public DependencyTree(int id)   {
        this(id, null, null,null);
    }
    
    public DependencyTree(int id, String word)  {
        this(id, word, null,null);
    }
    //change
    public DependencyTree(int id,String word,String pos )   {
        this(id, word, pos,null);
    }
    //add
    public DependencyTree(int id, String word, String pos,String depClass)  {
        this.word = word;
        this.pos = pos;
        this.id = id;
        this.relation = depClass;
        leftChilds = new ArrayList<DependencyTree>();
        rightChilds = new ArrayList<DependencyTree>();
    }
    public String getDepClass(){
        return this.relation;
    }
    public void setDepClass(String depClass){
        this.relation = depClass;
    }
    public void addLeftChild(DependencyTree ch) {
        leftChilds.add(0, ch);
        ch.setParent(this);
        updatesize(ch.size);
    }
    

    public void addRightChild(DependencyTree ch)    {
        rightChilds.add(ch);
        ch.setParent(this);
        updatesize(ch.size);
    }
    /**
     * 更新树大小
     * @param size
     */
    private void updatesize(int size) {
        this.size+=size;
        if(parent!=null){
            parent.updatesize(size);
        }
        
        
    }

    /**
     * 设置父节点
     * @param tree
     */
    private void setParent(DependencyTree tree) {
        parent = tree;
    }
    public String toString()    {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(id);
//      if (word != null)   {
//          sb.append("[");
//          sb.append(word);
//          sb.append("]");
//      }
        sb.append(" ");
        for (int i = 0; i < leftChilds.size(); i++) {
            sb.append(leftChilds.get(i));
        }
        sb.append("-");
        for (int i = 0; i < rightChilds.size(); i++) {
            sb.append(rightChilds.get(i));
        }
        sb.append("]");
        return sb.toString();
    }
    
    public int[] toHeadsArray() {
        int[] heads = new int[size];
        toArrays(this,heads);
        return heads;
    }

    public static void toArrays(DependencyTree dt, int[] heads) {
        for(int i = 0; i < dt.leftChilds.size(); i++)   {
            DependencyTree ch = dt.leftChilds.get(i);
            heads[ch.id] = dt.id;
            toArrays(ch, heads);
        }
        for(int i = 0; i < dt.rightChilds.size(); i++)  {
            DependencyTree ch = dt.rightChilds.get(i);
            heads[ch.id] = dt.id;
            toArrays(ch, heads);
        }
    }
    
    public int size() {
        return size;
    }
    
}
