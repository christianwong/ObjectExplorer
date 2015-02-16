package com.logexplorer.types;


public class ObjectType extends AbstractType {

	public ObjectType(Object object) {
		super(object);
	}
	
	@Override
	protected void processObject() {
		System.out.println(" + (OBJECT) " + object.toString());
	}

}
