package edu.fudan.nlp.corpus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import edu.fudan.nlp.pipe.seq.String2Sequence;


public class CWS2FNLP {

	private static boolean labeled=false;;

	/**
	 * 转换
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		String input;
		String output;
		if(args.length==2){
			 input =args[0];
			output = args[1];			
		}else if(args.length==1){
			input =args[0];
			output = input+".word";
		}else{
			 input ="./example-data/sequence/data.txt";
			 output = "./example-data/sequence/train.txt";
		}
		
		String2Sequence.delimer = "[\\s"+String.valueOf((char)12288)+"]+"; //全角空格
		
		File f = new File(input);
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				processLabeledData(files[i].toString(),output+files[i].getName());
			}
		}else{
			processLabeledData(input,output);
		}
		
		System.out.println("Done");
	}

	public static void processUnLabeledData(String input,String output) throws Exception{
		FileInputStream is = new FileInputStream(input);
//		is.skip(3); //skip BOM
		BufferedReader r = new BufferedReader(
				new InputStreamReader(is, "utf8"));
		OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(output), "utf8");
		while(true) {
			String sent = r.readLine();
			if(sent==null) break;
			String s = String2Sequence.genSequence(sent);
			w.write(s);
		}
		w.close();
		r.close();
	}

	public static void processLabeledData(String input,String output) throws Exception{
		FileInputStream is = new FileInputStream(input);
//		is.skip(3); //skip BOM
		BufferedReader r = new BufferedReader(
				new InputStreamReader(is, "utf8"));
		OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(output), "utf8");
		while(true) {
			String sent = r.readLine();
			if(null == sent) break;
			String s = String2Sequence.genSequenceWithLabel(sent);
			w.write(s);
		}
		r.close();
		w.close();
	}

}
