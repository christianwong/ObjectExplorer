package com.amdocs.logexplorer.types;

import java.lang.reflect.Field;

public abstract class AbstractType {

	protected Object object;
	protected Field field;
	
	@SuppressWarnings("unused")
	private AbstractType() {}
	protected AbstractType(Object object, Field field) {
		this.object = object;
		this.field = field;
	}

}
