package com.logexplorer.model.types;

import java.util.Iterator;
import java.util.Map;

import com.logexplorer.model.consts.TypeConstants;

public class MapType extends AbstractType {

	public MapType(String name, Object object) {
		super(name, object);
	}

	public static boolean isMap(Object object) {
		
		return object instanceof Map;
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
		
		addFirstChild(TypeConstants.ATTR_LENGTH, map.keySet().size());
	}

	@Override
	public boolean hasChilds() {
		return true;
	}

}
