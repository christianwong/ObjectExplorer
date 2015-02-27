package com.logexplorer.model.types;

import com.logexplorer.model.consts.TypeConstants;

public class BasicType extends AbstractType {

	public BasicType(String name, Object object) {
		super(name, object);
	}
	
	public static boolean isBasicType(Object object) {
		return object instanceof Number
				|| object instanceof Boolean
				|| object instanceof Character
				|| object instanceof String;
	}

	@Override
	public void processChilds() {}

	@Override
	public String getDisplayValue() {
		return null==object ? TypeConstants.NULL : object.toString();
	}

	@Override
	public boolean hasChilds() {
		return false;
	}

}
