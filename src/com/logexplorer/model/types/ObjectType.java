package com.logexplorer.model.types;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.logexplorer.model.util.TypeUtils;


public class ObjectType extends AbstractType {
	
	public ObjectType(String name, Object object) {
		super(name, object);
	}
	
	@Override
	public void processChilds() {
		List<Field> fields = getFields();
		
		for (Field field : fields) {
			
			boolean accessible = TypeUtils.enableAccessible(field);
			
			if (!Modifier.isStatic(field.getModifiers())) {
				String childName = field.getName();
				Object child = TypeUtils.getFieldValue(getObject(), field);
				addChild(childName, child);
			}
			
			TypeUtils.resetAccessible(field, accessible);
		}
	}

	private List<Field> getFields() {
		List<Field> fieldList = new ArrayList<Field>();

		if (null == getObject()) {
			return fieldList;
		}

		Class<? extends Object> objClass = getObject().getClass();
		while (objClass != null) {
			fieldList.addAll(Arrays.asList(objClass.getDeclaredFields()));
			objClass = objClass.getSuperclass();
		}

		return fieldList;
	}

	@Override
	public boolean hasChilds() {
		return getFields().size() > 0;
	}

}
