package com.amdocs.logexplorer.factory;

import java.lang.reflect.Field;

import com.amdocs.logexplorer.types.AbstractType;
import com.amdocs.logexplorer.types.ArrayType;
import com.amdocs.logexplorer.types.IterableType;
import com.amdocs.logexplorer.types.MapType;
import com.amdocs.logexplorer.util.TypeUtils;

public class TypeFactory {
	
	public static AbstractType getType(Field field, Object object) {
		
		boolean accessible = enableAccessible(field);
		AbstractType type = processSingleField(object, field);
		resetAccessible(field, accessible);
		
		return type;
	}

	private static void resetAccessible(Field field, boolean accessible) {
		field.setAccessible(accessible);
	}

	private static boolean enableAccessible(Field field) {
		boolean accessible = field.isAccessible();
		if(!accessible) {
			field.setAccessible(true);
		}
		return accessible;
	}
	
	private static AbstractType processSingleField(Object object, Field field) {
		System.out.println(" + field: "+field.toString());
		AbstractType type = null;
		
		//TODO to be removed in future implementation.
		Object value = TypeUtils.getFieldValue(object, field);

		if (ArrayType.isArray(object, field)) {
			type = new ArrayType(object, field);
		} else if (IterableType.isIterable(object, field)) {
			type = new IterableType(object, field);
		} else if (MapType.isMap(object, field)) {
			type = new MapType(object, field);
		} else if (value instanceof Object) {
			processObjectToString("OBJ", object, field);
		} else {
			processObjectToString("ANY", object, field);
		}
		
		return type;
	}
	
	private static void processObjectToString(String str, Object object, Field field) {
		Object value = TypeUtils.getFieldValue(object, field);
		Class<?> type = field.getType();
		if (null == value) {
			return;
		}
		
		System.out.println("   =("+str+":" + type + ") " + value.toString());
	}

}
