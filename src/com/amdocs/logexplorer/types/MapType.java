package com.amdocs.logexplorer.types;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import com.amdocs.logexplorer.util.TypeUtils;

public class MapType extends AbstractType {

	public MapType(Object object, Field field) {
		super(object, field);
		
		processMap();
	}
	
	public static boolean isMap(Object object, Field field) {
		
		Object value = TypeUtils.getFieldValue(object, field);
		if (null == value) {
			return false;
		}
		
		return value instanceof Map;
	}
	
	private void processMap() {
		Object value = TypeUtils.getFieldValue(object, field);
		Class<?> type = field.getType();
		if (null == value) {
			return;
		}
		
		System.out.print("   =(MP:" + type + ") [ ");
		
		Map<?,?> map = (Map<?, ?>) value;
		Iterator<?> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.print(key+"->"+map.get(key).toString()+" ");
		}
		
		System.out.println("]");
	}

}
