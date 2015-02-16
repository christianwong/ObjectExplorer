package com.amdocs.logexplorer.types;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;

import com.amdocs.logexplorer.util.TypeUtils;

public class IterableType extends AbstractType {

	public IterableType(Object object, Field field) {
		super(object, field);
		
		processIterable();
	}

	public static boolean isIterable(Object object, Field field) {
		
		Object value = TypeUtils.getFieldValue(object, field);
		if (null == value) {
			return false;
		}
		
		return value instanceof Iterable;
	}
	
	private void processIterable() {
		Object value = TypeUtils.getFieldValue(object, field);
		Type genericType = field.getGenericType();
		if (null == value) {
			return;
		}
		
		System.out.print("   =(IT:" + genericType + ") [ ");
		
		Iterator<?> iterator = ((Iterable<?>) value).iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		
		System.out.println("]");
	}

}
