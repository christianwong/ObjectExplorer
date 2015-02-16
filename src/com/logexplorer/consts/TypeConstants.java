package com.logexplorer.consts;

import java.util.HashMap;
import java.util.Map;

public final class TypeConstants {

	public static final String ATTR_LENGTH = "length";
	public static final String COLON = ";";
	public static final String CLASS = "class ";
	public static final String DOT = ".";
	public static final String ARRAY_NOTATION = "[";

	public static Map<String, String> typesMap = new HashMap<String, String>();
	
	static {
		typesMap.put("Z", "boolean");
		typesMap.put("B", "byte");
		typesMap.put("C", "char");
		typesMap.put("D", "double");
		typesMap.put("F", "float");
		typesMap.put("I", "int");
		typesMap.put("J", "long");
		typesMap.put("S", "short");
	}

}
