package com.logexplorer.types;

import java.util.Iterator;
import java.util.Map;

public class MapType extends AbstractType {

	public MapType(String name, Object object) {
		super(name, object);
	}

	public static boolean isMap(Object object) {
		
		return object instanceof Map;
	}
	
	protected void processObject() {
		System.out.println(" ##MAP:"+name+"="+getDisplayValue());
	}

	@Override
	public void processChilds() {
		Map<?,?> map = (Map<?, ?>) object;
		Iterator<?> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String name = (String) iterator.next();
			Object child = map.get(name);
			
			addChild(name, child);
		}
	}

}
