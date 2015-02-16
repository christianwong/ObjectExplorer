package com.logexplorer.types;

import java.lang.reflect.Array;

public class ArrayType extends AbstractType {
	
	private static final String ATTR_LENGTH = "length";

	public ArrayType(String name, Object object) {
		super(name, object);
	}

	public static boolean isArray(Object object) {
		return null != object && object.getClass().isArray();
	}
	
	protected void processObject() {
		System.out.println(" ##ARRAY:"+name+"="+getDisplayValue());
	}

	@Override
	public void processChilds() {
		int length = Array.getLength(object);
		addChild(ATTR_LENGTH, length);
		for (int idx = 0; idx < length; idx++) {
			String name = Integer.toString(idx);
			Object child = Array.get(object, idx);
			addChild(name, child);
		}
	}

}
