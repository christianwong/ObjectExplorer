package com.amdocs.logexplorer.factory;

import com.amdocs.logexplorer.types.AbstractType;
import com.amdocs.logexplorer.types.ArrayType;
import com.amdocs.logexplorer.types.BasicType;
import com.amdocs.logexplorer.types.IterableType;
import com.amdocs.logexplorer.types.MapType;
import com.amdocs.logexplorer.types.ObjectType;

public class TypeFactory {
	
	public static AbstractType getType(Object object) {
		
		AbstractType type = null;
		
		if (ArrayType.isArray(object)) {
			type = new ArrayType(object);
		} else if (IterableType.isIterable(object)) {
			type = new IterableType(object);
		} else if (MapType.isMap(object)) {
			type = new MapType(object);
		} else if (BasicType.isBasicType(object)) {
			type = new BasicType(object);
		} else {
			type = new ObjectType(object);
		}
		
		return type;
	}

}
