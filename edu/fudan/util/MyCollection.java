package edu.fudan.util;

import edu.fudan.nlp.cn.Chars;
import gnu.trove.iterator.TIntFloatIterator;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.map.hash.TIntFloatHashMap;
import gnu.trove.set.hash.THashSet;
import gnu.trove.map.hash.TObjectFloatHashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 常用集合操作
 * @author xpqiu
 *
 */
public class MyCollection {

	/**
	 * 由大到小排序
	 * @param map
	 * @return 数组下标
	 */
	public static int[] sort(TIntFloatHashMap tmap) {
		HashMap<Integer, Float> map = new HashMap<Integer, Float>();

		TIntFloatIterator it = tmap.iterator();
		while (it.hasNext()) {
			it.advance();
			int id = it.key();
			float val = it.value();
			map.put(id, Math.abs(val));
		}
		it = null;

		List<Entry> list = sort(map);
		int[] idx = new int[list.size()];
		Iterator<Entry> it1 = list.iterator();
		int i=0;
		while (it1.hasNext()) {
			Entry entry = it1.next();
			idx[i++] = (Integer) entry.getKey();
		}
		return idx;
	}

	/**
	 * 由大到小排序
	 * @param map
	 * @return
	 */
	public static List<Map.Entry> sort(Map map) {
		LinkedList<Map.Entry> list = new LinkedList<Map.Entry>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry>() {
			@Override
			public int compare(Entry o1,Entry o2) {
				// make sure the values implement Comparable
				return -((Comparable) o1.getValue()).compareTo(o2.getValue());
			}
		});
		return list;
	}

	public static void TSet2List(THashSet<String> newset, ArrayList<String> al) {
		TObjectHashIterator<String> it = newset.iterator();
		while(it.hasNext()){
			String s = it.next();
			al.add(s);
		}

	}
	/**
	 * 输出List<Entry>到文件
	 * @param entryList
	 * @param file
	 * @param b 是否输出值域
	 */
	public static void write(List<Entry> entryList, String file, boolean b) {
		try {
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			Iterator<Entry> it = entryList.iterator();
			while (it.hasNext()) {
				Entry entry = it.next();
				bout.write(entry.getKey().toString());
				if (b) {
					bout.write("\t");
					bout.write(entry.getValue().toString());
				}
				bout.write("\n");
			}
			bout.close();

		} catch (Exception e) {

		}
	}
	/**
	 * 将Map写到文件
	 * @param map
	 * @throws IOException 
	 */
	public static void write(Map<String,Float> map,String file) throws IOException {
		BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF-8"));
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String key = entry.getKey().toString();
			String v = entry.getValue().toString();
			bout.append(key);
			bout.append("\t");
			bout.append(v);
			bout.newLine();
		}
		bout.close();
	}

	/**
	 * 每行为一个元素
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static THashSet<String> loadTSet(String path) throws IOException{

		THashSet<String> dict = new THashSet<String>();
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf8"));
		} catch (FileNotFoundException e) {
			System.out.print("没找到文件："+path);
			return dict;
		}
		String line = null;			
		int count=0;

		while ((line = bfr.readLine()) != null) {
			if(line.length()==0)
				continue;
			//			if(Chars.isLetterOrDigitOrPunc(line))
			//				continue;

			dict.add(line);
		}
		return dict;
	}

	public static TObjectFloatHashMap<String> loadTStringFloatMap(String path) throws IOException {
		TObjectFloatHashMap<String> dict = new TObjectFloatHashMap<String>();
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf8"));
		} catch (FileNotFoundException e) {
			return dict;
		}
		String line = null;
		int count = 0;
		while ((line = bfr.readLine()) != null) {
			if (line.length() == 0)
				continue;
			int idx = line.lastIndexOf("\t");
			dict.put(line.substring(0, idx), Float.parseFloat(line.substring(idx + 1)));
		}
		return dict;
	}

	/**
	 * 将文件读入到HashMap
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String,String> loadStringStringMap(String path) throws IOException{

		HashMap<String,String> dict = new HashMap<String,String>();
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf8"));
		} catch (FileNotFoundException e) {
			return dict;
		}
		String line = null;			
		int count=0;

		while ((line = bfr.readLine()) != null) {
			if(line.length()==0)
				continue;
			int idx = line.lastIndexOf("\t");
			dict.put(line.substring(0,idx), line.substring(idx+1));		
		}
		return dict;
	}


	/**
	 * 将文件读入到HashMap
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String,Float> loadStringFloatMap(String path) throws IOException{

		HashMap<String,Float> dict = new HashMap<String,Float>();
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf8"));
		} catch (FileNotFoundException e) {
			return dict;
		}
		String line = null;			
		int count=0;

		while ((line = bfr.readLine()) != null) {
			if(line.length()==0)
				continue;
			int idx = line.lastIndexOf("\t");
			String key = line.substring(0,idx);
			String v = line.substring(idx+1);
			dict.put(key, Float.parseFloat(v));		
		}
		return dict;
	}

	/**
	 * 从多文件中读入Map
	 * @param sfiles
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static HashMap<String,Float> loadStringFloatMapInMultiFiles(String sfiles) throws NumberFormatException, IOException {
		HashMap<String, Float> map = new HashMap<String, Float>();

		String[] files = sfiles.split(";");
		for(String f:files){
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf8"));
			String line;
			while ((line = br.readLine()) != null) {
				if(line.length()==0)
					continue;
				int idx = line.lastIndexOf("\t");
				if(idx==-1)
					continue;
				String key = line.substring(0,idx);
				float v = Float.parseFloat(line.substring(idx+1));
				if (map.containsKey(key)) {
					float tempV = map.get(key);
					map.put(key, v + tempV);
				}
				else
					map.put(key, v);
			}
		}
		return map;	
	}

	public static void write(Iterable<String> set, String file) {
		try {
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String entry = it.next();
				bout.write(entry);
				bout.write("\n");
			}
			bout.close();

		} catch (Exception e) {

		}
	}
	public static HashMap<String, String[]> loadMultiValueMap(String path) throws IOException {
		return loadMultiValueMap(new FileInputStream(path));
	}

	public static HashMap<String, String[]> loadMultiValueMap(InputStream is) throws IOException {
		HashMap<String, String[]> dict = new HashMap<String, String[]>();
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new InputStreamReader(is,"utf8"));
		} catch (Exception e) {
			return dict;
		}
		String line = null;			
		int count=0;

		while ((line = bfr.readLine()) != null) {
			if(line.length()==0)
				continue;
			String[] toks = line.split("\\s");
			String[] v = Arrays.copyOfRange(toks, 1, toks.length);
			dict.put(toks[0], v);		
		}
		return dict;
	}

	/**
	 * 写多值Map
	 * @param map
	 * @param file
	 */
	public static void write(HashMap<String, HashSet<String>> map,	String file) {

		try {
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			Iterator<Entry<String, HashSet<String>>> it1 = map.entrySet().iterator();
			while(it1.hasNext()){
				Entry<String, HashSet<String>> entry = it1.next();
				bout.write(entry.getKey());
				bout.write("\t");
				Iterator<String> it = entry.getValue().iterator();
				while (it.hasNext()) {
					String en = it.next();
					bout.write(en);
					if(it.hasNext())
						bout.write("\t");
				}
				if(it1.hasNext())
					bout.write("\n");
			}
			bout.close();

		} catch (Exception e) {

		}
	}

	public static int isContain(THashSet<String> set,
			ArrayList<String> subwords) {
		int i = 0;
		for(String s: subwords){
			if(set.contains(s))
				i++;
		}
		return i;
	}

}
