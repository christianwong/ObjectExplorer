package com.amdocs.logexplorer.factory;

import com.amdocs.logexplorer.types.AbstractType;
import com.amdocs.logexplorer.types.ArrayType;
import com.amdocs.logexplorer.types.IterableType;
import com.amdocs.logexplorer.types.MapType;

public class TypeFactory {
	
	public static AbstractType getType(Object object) {
		
		AbstractType type = null;
		
		if (ArrayType.isArray(object)) {
			type = new ArrayType(object);
		} else if (IterableType.isIterable(object)) {
			type = new IterableType(object);
		} else if (MapType.isMap(object)) {
			type = new MapType(object);
		} else if (object instanceof Object) {
			processObjectToString("OBJ", object);
		} else {
			processObjectToString("ANY", object);
		}
		
		return type;
	}

	private static void processObjectToString(String str, Object object) {
		Class<?> type = null;//field.getType();
		System.out.println(" + ("+str+":" + type + ") " + object.toString());
	}

}
