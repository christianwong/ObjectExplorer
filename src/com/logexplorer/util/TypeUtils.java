package com.logexplorer.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TypeUtils {
	
	private static Map<String, String> typesMap = new HashMap<String, String>();
	
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

	public static Object getFieldValue(Object object, Field field) {
		Object value = null;
		try {
			value = field.get(object);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void resetAccessible(Field field, boolean accessible) {
		field.setAccessible(accessible);
	}

	public static boolean enableAccessible(Field field) {
		boolean accessible = field.isAccessible();
		if(!accessible) {
			field.setAccessible(true);
		}
		return accessible;
	}

	public static String formatClassName(String originalName) {
		String name = originalName;
		
		// Check if is a basic data type (from array)
		String basicType = typesMap.get(originalName);
		if (null!=basicType) {
			return basicType;
		}
		
		// Remove "class" from name
		String COLON = ";";
		if (name.endsWith(COLON)) {
			name = name.substring(0, name.length()-1);
		}
		
		// Remove "class" from name
		String CLASS = "class ";
		if (name.startsWith(CLASS)) {
			name = name.substring(CLASS.length());
		}
		
		// Process Array
		String ARRAY_NOTATION = "[";
		if (name.startsWith(ARRAY_NOTATION)) {
			name = formatClassName(name.substring(ARRAY_NOTATION.length()))+"[]"; 
		}

		// Remove package information
		String DOT = ".";
		if (name.contains(DOT)) {
			name = name.substring(name.lastIndexOf(DOT)+1);
		}
		
		return name;
	}
	
}
