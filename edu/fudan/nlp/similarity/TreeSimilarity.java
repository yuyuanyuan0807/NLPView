package edu.fudan.nlp.similarity;

import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.parser.dep.DependencyTree;

public class TreeSimilarity implements ISimilarity <Instance>{

    public double factor = 0.5;

    @Override
    public float calc(Instance item1, Instance item2) throws Exception {
        double score = getDepScore((DependencyTree)item1.getData(), (DependencyTree)item2.getData(), 1);
        double base = getBase((DependencyTree)item1.getData()) * getBase((DependencyTree)item2.getData());
        base = Math.sqrt(base);
        return (float)(score / base);
    }

    /**
     * 计算Tree Kernel
     * @param t1
     * @param t2
     * @param depth
     * @return
     */
    private double getDepScore(DependencyTree t1, DependencyTree t2, int depth){
        double score = 0.0;
        double modify = getDepthModify(depth);
        if(modify == 0)
            return score;
        
        double sScore = getSScore(t1, t2);
        if (sScore > 0)
            score += modify * sScore;
        else
            score += factor * modify * getCScore(t1, t2);
        
        for(int i = 0; i < t1.leftChilds.size(); i++)
            for(int j = 0; j < t2.leftChilds.size(); j++)
                score += getDepScore(t1.leftChilds.get(i), t2.leftChilds.get(j), depth+1);
        
//        for(int i = 0; i < t1.leftChilds.size(); i++)
//            for(int j = 0; j < t2.rightChilds.size(); j++)
//                score += getDepScore(t1.leftChilds.get(i), t2.rightChilds.get(j), depth+1);
//        
//        for(int i = 0; i < t1.rightChilds.size(); i++)
//            for(int j = 0; j < t2.leftChilds.size(); j++)
//                score += getDepScore(t1.rightChilds.get(i), t2.leftChilds.get(j), depth+1);
        
        for(int i = 0; i < t1.rightChilds.size(); i++)
            for(int j = 0; j < t2.rightChilds.size(); j++)
                score += getDepScore(t1.rightChilds.get(i), t2.rightChilds.get(j), depth+1);
        
        return score;
    }
    
    /**
     * c函数
     * @param t1
     * @param t2
     * @return
     */
    private double getCScore(DependencyTree t1, DependencyTree t2){
        if(t1.pos.equals(t2.pos))
            return 1;
        else return 0;
    }
    
    /**
     * s函数
     * @param t1
     * @param t2
     * @return
     */
    private double getSScore(DependencyTree t1, DependencyTree t2){
        if(t1.word.equals(t2.word))
            return 1;
        else return 0;
    }
    
    /**
     * 深度修正参数
     * @param depth
     * @return
     */
    private double getDepthModify(int depth){
        if(depth == 1)
            return 1;
        else if(depth == 2)
            return 0.9;
        else if(depth == 3)
            return 0.8;
        else if(depth == 4)
            return 0.65;
        else if(depth == 5)
            return 0.5;
        else if(depth == 6)
            return 0.3;
        else if(depth == 7)
            return 0.1;
        else return 0;
    }

    /**
     * 分数归一化
     * @param t
     * @return
     */
    private double getBase(DependencyTree t){
        return getDepScore(t,t,1);
    }
}
