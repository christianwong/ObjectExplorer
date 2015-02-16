package com.amdocs.logexplorer.util;

import java.lang.reflect.Field;

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
	
}
