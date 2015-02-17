package com.logexplorer.model.types;

import java.lang.reflect.Field;

import com.logexplorer.model.util.TypeUtils;


public class ObjectType extends AbstractType {
	
	public ObjectType(String name, Object object) {
		super(name, object);
	}
	
	@Override
	protected void processObject() {
		System.out.println(" ##OBJECT:"+name+"="+getDisplayValue());
	}

	@Override
	public void processChilds() {
		Class<? extends Object> objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		
		for (Field field : fields) {
			
			boolean accessible = TypeUtils.enableAccessible(field);
			
			String name = field.getName();
			Object child = TypeUtils.getFieldValue(object, field);
			addChild(name, child);
			
			TypeUtils.resetAccessible(field, accessible);
		}
	}

}
