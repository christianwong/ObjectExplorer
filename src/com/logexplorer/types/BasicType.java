package com.logexplorer.types;



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
	protected void processObject() {
		System.out.println(" ##BASIC:"+name+"="+object.toString());
	}

	@Override
	public void processChilds() {}

}
