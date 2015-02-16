package com.amdocs.logexplorer.types;

import java.util.Iterator;

public class IterableType extends AbstractType {

	public IterableType(Object object) {
		super(object);
	}

	public static boolean isIterable(Object object) {
		
		return object instanceof Iterable;
	}
	
	protected void processObject() {
		
		System.out.print(" + (ITERABLE) [ ");
		
		Iterator<?> iterator = ((Iterable<?>) object).iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		
		System.out.println("]");
	}

}
