package com.logexplorer.model.helper;

import com.logexplorer.model.types.AbstractType;

public class DataHelperInstance {
	
	private Object data;
	private int objectHash;
	private AbstractType objectType;
	
	public DataHelperInstance(Object object, AbstractType objectType) {
		this.setObject(object);
		this.setObjectHash(System.identityHashCode(object));
		this.setObjectType(objectType);
	}

	public Object getObject() {
		return data;
	}

	protected void setObject(Object data) {
		this.data = data;
	}

	public int getObjectHash() {
		return objectHash;
	}

	protected void setObjectHash(int objectHash) {
		this.objectHash = objectHash;
	}
	
	public AbstractType getObjectType() {
		return objectType;
	}

	protected void setObjectType(AbstractType objectType) {
		this.objectType = objectType;
	}
	
}
