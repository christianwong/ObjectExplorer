package com.amdocs.logexplorer.types;

import java.lang.reflect.Type;
import java.util.Iterator;

public class IterableType extends AbstractType {

	public IterableType(Object object) {
		super(object);
	}

	public static boolean isIterable(Object object) {
		
		return object instanceof Iterable;
	}
	
	protected void processObject() {
		Type genericType = null;
		
		System.out.print(" + (IT:" + genericType + ") [ ");
		
		Iterator<?> iterator = ((Iterable<?>) object).iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		
		System.out.println("]");
	}

}
