package com.logexplorer.model.helper;

import com.logexplorer.model.types.AbstractType;

public class DataHelperInstance {
	
	private Object object;
	private int objectHash;
	private AbstractType objectType;
	
	public DataHelperInstance(Object object, AbstractType objectType) {
		this.setObject(object);
		this.setObjectHash(getHashCode(object));
		this.setObjectType(objectType);
	}

	public Object getObject() {
		return object;
	}

	protected void setObject(Object data) {
		this.object = data;
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
	
	public static int getHashCode(Object object) {
		return System.identityHashCode(object);
	}
	
//	@Override
//	public boolean equals(Object object) {
//		int objectHash2 = getObjectHash();
//		int hashCode = getHashCode(object);
//		return objectHash2 == hashCode;
//		
//		if (object instanceof DataHelperInstance) {
//			DataHelperInstance instance = (DataHelperInstance) object;
//			return this.hashCode() == instance.hashCode();
//		}
//		
//		return false;
//	}
	
}
