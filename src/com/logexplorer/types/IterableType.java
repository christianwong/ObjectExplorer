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
		System.out.println(" ##ITERABLE:"+name+"="+getDisplayValue());
	}

	@Override
	public void processChilds() {
		
		Iterator<?> iterator = ((Iterable<?>) object).iterator();
		int index = 0;
		while(iterator.hasNext()) {
			String name = Integer.toString(index++);
			Object child = iterator.next();
			
			addChild(name, child);
		}
		
	}

}
