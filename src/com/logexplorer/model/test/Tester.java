package com.logexplorer.model.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {
	
	public static Object run(String filename) {
		return readBinaryObject(filename);
	}

	private static Object readBinaryObject(String filename) {
		Object someObject = null;

		try {
			FileInputStream fin = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fin);
			someObject = ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return someObject;
	}

	private static void writeBinaryObject(String filename, Object object) {
		try {
			FileOutputStream fout = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(object);
			oos.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createObject(String filename) {
		GuessClass gc = new GuessClass();
		gc.setaInteger(123);
		gc.setaDouble(543.756);
		gc.setaChar('a');
		gc.setaFloat(234f);
		gc.setaString("hello, world!");
		int iarray[] = new int[3];
		iarray[0] = 1;
		iarray[1] = 2;
		iarray[2] = 3;
		gc.setaIntArray(iarray);
		
		SimpleClass simpleArray[] = new SimpleClass[2];
		SimpleClass simpleClass = new SimpleClass();
		simpleArray[0] = simpleClass;
		simpleArray[1] = simpleClass;
		gc.setaSimpleClass(simpleArray);
		
		List<String> aArrayList = new ArrayList<String>();
		aArrayList.add("first");
		aArrayList.add("seccond");
		gc.setaArrayList(aArrayList);
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("one", new Integer(1));
		aMap.put("two", new Integer(2));
		aMap.put("three", new Integer(3));
		gc.setaMap(aMap);

		Map<String, SimpleClass> aComplexMap = new HashMap<String, SimpleClass>();
		aComplexMap.put("SimpleClass", new SimpleClass());
		aComplexMap.put("SimplerClass", new SimpleClass());
		gc.setaComplexMap(aComplexMap);

		writeBinaryObject(filename, gc);
	}

}
