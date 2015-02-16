package com.amdocs.logexplorer.types;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import com.amdocs.logexplorer.util.TypeUtils;

public class ArrayType extends AbstractType {
	
	public ArrayType(Object object, Field field) {
		super(object, field);
		
		processArray();
	}

	public static boolean isArray(Object object, Field field) {
		
		Object value = TypeUtils.getFieldValue(object, field);
		if (null == value) {
			return false;
		}
		
		return value.getClass().isArray();
	}
	
	private void processArray() {
		Object value = TypeUtils.getFieldValue(object, field);
		if (null == value) {
			return;
		}
		System.out.print("   =(AR:array) [ ");
		
		for (int i = 0; i < Array.getLength(value); i++) {
			Object arrayElement = Array.get(value, i);
			System.out.print(arrayElement+" ");
		}
		
		System.out.println("]");
	}

}
