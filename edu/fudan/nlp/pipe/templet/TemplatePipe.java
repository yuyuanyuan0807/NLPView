package edu.fudan.nlp.pipe.templet;

import java.util.ArrayList;
import java.util.List;

import edu.fudan.ml.types.AlphabetFactory;
import edu.fudan.ml.types.FeatureAlphabet;
import edu.fudan.ml.types.HashSparseVector;
import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.pipe.Pipe;

public class TemplatePipe extends Pipe {

	private static final long serialVersionUID = -4863048529473614384L;
	private FeatureAlphabet features;
	private ArrayList<RETemplate> group;
	public TemplatePipe(AlphabetFactory af,RETemplateGroup group){
		this.features = af.DefaultFeatureAlphabet();
		this.group = group.group;
	}
	@Override
	public void addThruPipe(Instance inst) throws Exception {
		String str = (String) inst.getSource();
		HashSparseVector sv = (HashSparseVector) inst.getData();
		List<RETemplate> templates = new ArrayList<RETemplate>();
		for(int i=0;i<group.size();i++){
			RETemplate qt = group.get(i);
			float w = qt.matches(str);
			if(w>0){
				int id = features.lookupIndex("template:"+qt.comment);
				sv.add(id, w);
			}
		}
	}

}
