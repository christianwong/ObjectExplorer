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
	
}
