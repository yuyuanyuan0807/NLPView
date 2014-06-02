package edu.fudan.nlp.corpus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class CTB2CONLL {
	public static void main(String[] args)
	{
		try
		{
			String ls_1;
			Process process =null;
			File handle = new File("./tmpdata/ctb/data3");
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("./tmpdata/malt.train"), "UTF-8"));
			for (File sub : Arrays.asList(handle.listFiles())){
				String str = sub.getAbsolutePath();
				process = Runtime.getRuntime().exec("cmd /c java -jar ./tmpdata/ctb/Penn2Malt.jar "+str+" ./tmpdata/ctb/headrules.txt 3 2 chtb");			
				BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
				while ( (ls_1=bufferedReader.readLine()) != null)
				{
					System.out.println(ls_1);
				}
			}			
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}
}
