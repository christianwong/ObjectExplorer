package com.logexplorer.model.types;

import java.util.Iterator;

import com.logexplorer.model.consts.TypeConstants;

public class IterableType extends AbstractType {

	public IterableType(String name, Object object) {
		super(name, object);
	}

	public static boolean isIterable(Object object) {
		
		return object instanceof Iterable;
	}
	
	@Override
	public void processChilds() {
		
		Iterator<?> iterator = ((Iterable<?>) getObject()).iterator();
		int index = 0;
		while(iterator.hasNext()) {
			String name = Integer.toString(index++);
			Object child = iterator.next();
			
			addChild(name, child);
		}
		
		addFirstChild(TypeConstants.ATTR_LENGTH, index);
	}

	@Override
	public boolean hasChilds() {
		return true;
	}

}
