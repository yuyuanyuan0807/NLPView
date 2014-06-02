package edu.fudan.nlp.similarity;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.set.hash.TIntHashSet;



public class JaccardSimilarity implements ISimilarity<TIntHashSet>{

	public float calc(TIntHashSet s1, TIntHashSet s2) {
		int com = 0;
		if(s1==null||s2==null)
			return 0;
		TIntIterator it = s1.iterator();
		for ( int i = s1.size(); i-- > 0; ) {
			int v = it.next();
			if(s2.contains(v))
				com++;
		}
		float sim = ((float) com)/(s1.size()+s2.size()-com);
		return sim;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
