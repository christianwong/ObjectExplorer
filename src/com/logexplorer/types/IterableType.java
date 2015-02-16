package com.logexplorer.types;

import java.util.Iterator;

public class IterableType extends AbstractType {

	public IterableType(String name, Object object) {
		super(name, object);
	}

	public static boolean isIterable(Object object) {
		
		return object instanceof Iterable;
	}
	
	protected void processObject() {
		System.out.println(" ##ITERABLE:"+name);
	}

	@Override
	public void processChilds() {
		
		Iterator<?> iterator = ((Iterable<?>) object).iterator();
		while(iterator.hasNext()) {
			String name = Integer.toString(iterator.hashCode());
			Object child = iterator.next();
			
			addChild(name, child);
		}
		
	}

}
