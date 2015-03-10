package com.logexplorer.model.types;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.logexplorer.model.util.TypeUtils;


public class ObjectType extends AbstractType {
	
	public ObjectType(String name, Object object, AbstractType parent) {
		super(name, object, parent);
	}
	
	@Override
	public void processChilds() {
		List<Field> fields = getFields();
		
		for (Field field : fields) {
			
			boolean accessible = TypeUtils.enableAccessible(field);
			
			String childName = field.getName();
			Object child = TypeUtils.getFieldValue(getObject(), field);
			addChild(childName, child);
			
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

		// WA to avoid inclusion of composition object reference "this$"
		for (int idx=fieldList.size()-1; idx>=0; idx--) {
			if (fieldList.get(idx).getName().contains("this$") ||
					Modifier.isStatic(fieldList.get(idx).getModifiers())) {
				fieldList.remove(idx);
			}
		}
		
		// Sort list
		Collections.sort(fieldList, new Comparator<Field>() {
			@Override
			public int compare(Field field1, Field field2) {
				return field1.getName().compareTo(field2.getName());
			}
		});

		return fieldList;
	}

	@Override
	public boolean hasChilds() {
		return getFields().size() > 0;
	}

}
