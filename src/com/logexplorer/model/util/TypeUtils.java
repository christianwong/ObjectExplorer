package com.logexplorer.model.util;

import java.lang.reflect.Field;
import java.util.List;

import com.logexplorer.model.consts.TypeConstants;
import com.logexplorer.model.types.AbstractType;

public class TypeUtils {
	
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
		String basicType = TypeConstants.typesMap.get(originalName);
		if (null!=basicType) {
			return basicType;
		}
		
		// Remove ";" from name
		if (name.endsWith(TypeConstants.COLON)) {
			name = name.substring(0, name.length()-1);
		}
		
		// Remove "class" from name
		if (name.startsWith(TypeConstants.CLASS)) {
			name = name.substring(TypeConstants.CLASS.length());
		}
		
		// Process Array
		if (name.startsWith(TypeConstants.ARRAY_NOTATION)) {
			name = formatClassName(name.substring(TypeConstants.ARRAY_NOTATION.length()))+"[]"; 
		}

		// Remove package information
		if (name.contains(TypeConstants.DOT)) {
			name = name.substring(name.lastIndexOf(TypeConstants.DOT)+1);
		}
		
		return name;
	}

	public static void print(AbstractType type) {
		print(type, 0);
	}
	
	private static void print(AbstractType type, int level) {
		String name = type.getDisplayName();
		String value = type.getDisplayValue();
		List<AbstractType> childs = type.getChilds();
		
		String indentation = "";
		for(int idx=0; idx<level; idx++) {
			if (idx==level-1) {
				indentation += " |- ";
			} else {
				indentation += " | ";
			}
		}
		
		System.out.println(indentation+name+":"+value);
		for(AbstractType child : childs) {
			print(child, level+1);
		}
	}
	
}
