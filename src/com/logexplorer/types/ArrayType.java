package com.logexplorer.types;

import java.lang.reflect.Array;

public class ArrayType extends AbstractType {
	
	public ArrayType(Object object) {
		super(object);
	}

	public static boolean isArray(Object object) {
		return object.getClass().isArray();
	}
	
	protected void processObject() {
		System.out.print(" + (ARRAY) [ ");
		
		for (int i = 0; i < Array.getLength(object); i++) {
			Object arrayElement = Array.get(object, i);
			System.out.print(arrayElement+" ");
		}
		
		System.out.println("]");
	}

}
