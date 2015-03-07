package com.logexplorer.model.factory;

import com.logexplorer.model.index.TypeIndex;
import com.logexplorer.model.types.AbstractType;
import com.logexplorer.model.types.ArrayType;
import com.logexplorer.model.types.BasicType;
import com.logexplorer.model.types.IterableType;
import com.logexplorer.model.types.MapType;
import com.logexplorer.model.types.ObjectType;

public class TypeFactory {
	
	public static AbstractType getType(String name, Object object) {

		// needed to avoid indexing of null objects
		if (null == object) {
			object = "null";
		}

		// if the object exists in DataHelper, don't create a new one.
//		int index = DataHelper.getInstance().getObjectID(object);
//		if (index>=0) {
//			return DataHelper.getInstance().getStoredType(index);
//		}
		
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
		
		String typeName = type.getName();
		String typeValue = type.getValue();

		// add new type to index
		TypeIndex.getInstance().add(typeName, typeValue, type);
		
		return type;
	}

}
