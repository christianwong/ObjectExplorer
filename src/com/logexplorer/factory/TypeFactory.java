package com.logexplorer.factory;

import com.logexplorer.types.AbstractType;
import com.logexplorer.types.ArrayType;
import com.logexplorer.types.BasicType;
import com.logexplorer.types.IterableType;
import com.logexplorer.types.MapType;
import com.logexplorer.types.ObjectType;

public class TypeFactory {
	
	public static AbstractType getType(String name, Object object) {
		
		AbstractType type = null;
		
		if (ArrayType.isArray(object)) {
			type = new ArrayType(name, object);
		} else if (IterableType.isIterable(object)) {
			type = new IterableType(name, object);
		} else if (MapType.isMap(object)) {
			type = new MapType(name, object);
		} else if (BasicType.isBasicType(object)) {
			type = new BasicType(name, object);
		} else {
			type = new ObjectType(name, object);
		}
		
		type.getChilds();
		return type;
	}

}
