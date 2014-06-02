package edu.fudan.ml.classifier.linear;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import edu.fudan.ml.types.AlphabetFactory;
import edu.fudan.ml.types.FeatureAlphabet;
import edu.fudan.nlp.pipe.seq.Sequence2FeatureSequence;
import edu.fudan.nlp.pipe.seq.templet.TempletGroup;
import gnu.trove.iterator.TObjectIntIterator;
import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.list.array.TFloatArrayList;
import gnu.trove.map.hash.TIntObjectHashMap;

/**
 * 优化模型文件，去掉无用的特征
 * 权重向量为float[]
 * @since FudanNLP 1.0
 * @author xpqiu
 * 
 */
public class ModelAnalysis {

	private Linear cl;
	private FeatureAlphabet feature;
	private float thresh = 0;
	private float[] weights;
	private Sequence2FeatureSequence p;

	public ModelAnalysis(Linear cl) {
		this.cl = cl;
		this.feature = cl.getAlphabetFactory().DefaultFeatureAlphabet();
		this.weights = cl.getWeights();
	}

	/**
	 * 统计信息，计算删除非0特征后，权重的长度
	 * 
	 * @throws IOException
	 */
	public void removeZero() {
		boolean freeze = false;
		if (feature.isStopIncrement()) {
			feature.setStopIncrement(false);
			freeze = true;
		}

		TIntObjectHashMap<String> index = new TIntObjectHashMap<String>();
		TObjectIntIterator<String> it = feature.iterator();
		while (it.hasNext()) {
			it.advance();
			String value = it.key();
			int key = it.value();
			index.put(key, value);
		}
		System.out.println("\n优化前");	
		System.out.println("字典大小"+index.size());
		System.out.println("特征个数"+feature.size());
		System.out.println("权重长度"+weights.length);
		int[] idx = index.keys();
		Arrays.sort(idx);
		int length = this.weights.length;
		FeatureAlphabet newfeat = cl.getAlphabetFactory().rebuildFeatureAlphabet(AlphabetFactory.DefalutFeatureName);
		TFloatArrayList ww = new TFloatArrayList();
		for (int i = 0; i < idx.length; i++) {
			int base = idx[i]; //一个特征段起始位置
			int end; //一个特征段结束位置
			if (i < idx.length - 1)
				end = idx[i + 1]; //对应下一个特征段起始位置
			else
				end  = length; //或者整个结束位置
			boolean del = true;
			for (int j = base; j < end; j++) {
				if (Math.abs(this.weights[j])>1e-5f) {
					del = false;
					break;
				}
			}
			int interv = end - base;   //一个特征段长度
			if (!del) {
				String str = index.get(base);
				int id = newfeat.lookupIndex(str, interv);
				for (int j = 0; j < interv; j++) {
					ww.insert(id + j, weights[base + j]);
				}
			}else{
//				System.out.print(".");	
			}
			
		}
		newfeat.setStopIncrement(freeze);
		cl.setWeights(ww.toArray());
		System.out.println("\n优化后");	
		System.out.println("字典大小"+newfeat.keysize());
		System.out.println("字典大小"+newfeat.size());
		System.out.println("权重长度"+ww.size());
		
		
		index.clear();
		ww.clear();
	}
	
	public void removeEqual(double Variance) {
		boolean freeze = false;
		if (feature.isStopIncrement()) {
			feature.setStopIncrement(false);
			freeze = true;
		}

		TIntObjectHashMap<String> index = new TIntObjectHashMap<String>();
		TObjectIntIterator<String> it = feature.iterator();
		while (it.hasNext()) {
			it.advance();
			String value = it.key();
			int key = it.value();
			index.put(key, value);
		}
		int[] idx = index.keys();
		Arrays.sort(idx);
		int length = this.weights.length;
		FeatureAlphabet newfeat = cl.getAlphabetFactory().rebuildFeatureAlphabet(AlphabetFactory.DefalutFeatureName);
		TFloatArrayList ww = new TFloatArrayList();
		for (int i = 0; i < idx.length; i++) {
			int base = idx[i];
			int end = length;
			if (i < idx.length - 1)
				end = idx[i + 1];
		
			boolean del = false;
			
			double E = 0.0, D = 0.0;
			for (int j = base; j < end; j++) {
				E += this.weights[j];
			}
			E = E / (end - base);
			for (int j = base; j < end; j++) {
				D += Math.pow(this.weights[j] - E, 2);
			}
			D = D / (end - base);
			
			if(D <= Variance)
				del = true;
			
			int interv = end - base;
			if (!del) {
				String str = index.get(base);
				int id = newfeat.lookupIndex(str, interv);
				for (int j = 0; j < interv; j++) {
					ww.insert(id + j, weights[base + j]);
				}
			}
		}
		
		newfeat.setStopIncrement(freeze);
		cl.setWeights(ww.toArray());
		
		index.clear();
		ww.clear();
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String file;
		if (args.length > 0)
			file = args[0];
		else
			file="./models/cws.m";
		Linear cl = loadFrom(file);

		// double[][] hist = MyArrays.histogram(weis, 100);
		ModelAnalysis ma = new ModelAnalysis(cl);

		ma.removeZero();
		cl.saveTo(file+1);
		System.out.print("Done");
	}

	protected static Linear loadFrom(String modelfile) throws IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new GZIPInputStream(new FileInputStream(modelfile))));
		TempletGroup templets = (TempletGroup) in.readObject();
		Linear cl = (Linear) in.readObject();
		in.close();
		return cl;
	}

}
