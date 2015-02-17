package com.logexplorer.model.types;

import java.lang.reflect.Array;

import com.logexplorer.model.consts.TypeConstants;

public class ArrayType extends AbstractType {
	
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
		addChild(TypeConstants.ATTR_LENGTH, length);
		
		for (int idx = 0; idx < length; idx++) {
			String name = Integer.toString(idx);
			Object child = Array.get(object, idx);
			addChild(name, child);
		}
	}

}
