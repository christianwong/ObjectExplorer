package com.logexplorer.model.types;

import java.lang.reflect.Field;

import com.logexplorer.model.util.TypeUtils;


public class ObjectType extends AbstractType {
	
	public ObjectType(String name, Object object) {
		super(name, object);
	}
	
	@Override
	public void processChilds() {
		Field[] fields = getFields();
		
		for (Field field : fields) {
			
			boolean accessible = TypeUtils.enableAccessible(field);
			
			String name = field.getName();
			Object child = TypeUtils.getFieldValue(getObject(), field);
			addChild(name, child);
			
			TypeUtils.resetAccessible(field, accessible);
		}
	}

	private Field[] getFields() {
		if (null == getObject()) {
			return new Field[0];
		}
		Class<? extends Object> objClass = getObject().getClass();
		return objClass.getDeclaredFields();
	}

	@Override
	public boolean hasChilds() {
		return getFields().length > 0;
	}

}
