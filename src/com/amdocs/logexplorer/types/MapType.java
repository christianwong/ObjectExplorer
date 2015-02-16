package com.amdocs.logexplorer.types;

import java.util.Iterator;
import java.util.Map;

public class MapType extends AbstractType {

	public MapType(Object object) {
		super(object);
	}

	public static boolean isMap(Object object) {
		
		return object instanceof Map;
	}
	
	protected void processObject() {
		
		System.out.print(" + (MAP) [ ");
		
		Map<?,?> map = (Map<?, ?>) object;
		Iterator<?> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.print(key+"->"+map.get(key).toString()+" ");
		}
		
		System.out.println("]");
	}

}
