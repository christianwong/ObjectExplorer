package com.amdocs.logexplorer.types;


public abstract class AbstractType {

	protected Object object;
	
	@SuppressWarnings("unused")
	private AbstractType() {}
	
	public AbstractType(Object object) {
		this.object = object;
		processObject();
	}
	
	protected abstract void processObject();

}
