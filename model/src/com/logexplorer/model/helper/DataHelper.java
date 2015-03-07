package com.logexplorer.model.helper;

import java.util.ArrayList;
import java.util.List;

import com.logexplorer.model.types.AbstractType;

/**
 * Singleton class for storing object references and duplication detection.
 * @author christian wong
 */
public class DataHelper {
	
	// Singleton instance
	private static DataHelper instance = null;
	
	/**
	 * Singleton method to retrieve instance.
	 * @return
	 */
	private static DataHelper getInstance() {
		if (null == instance) {
			instance = new DataHelper();
		}
		return instance;
	}
	
	protected List<DataHelperInstance> list;
	
	private DataHelper() {
		list = new ArrayList<DataHelperInstance>();
	};
	
	// warning: ugly code!
	private synchronized int processObject(Object object, AbstractType type) {
		
//		DataHelperInstance instance = ;
		int objectID = list.size();
		
		// somehow indexOf is not calling DataHelperInstance.equals()
//		int indexOf = ((ArrayList<DataHelperInstance>)list).indexOf(instance);
		int indexOf = getObjectID(object);
		
		if (indexOf >= 0) {
			objectID = indexOf;//+"D";
		} else {
			list.add(new DataHelperInstance(object, type));
		}

//		System.out.println("object Hash: '"+DataHelperInstance.getHashCode(object)+"' was assigned to objectID: '"+objectID+"'.. Object is: '"+object.toString()+"'.");
		return objectID;
	}

	public int getObjectID(Object object) {
		int indexOf = -1;
		for (int idx=0; idx<list.size(); idx++) {
			DataHelperInstance inst = list.get(idx);
			if (inst.getObjectHash() == DataHelperInstance.getHashCode(object)) {
				indexOf = idx;
				break;
			}
		}
		return indexOf;
	}
	
	public static int getObjectID(Object object, AbstractType type) {
		return DataHelper.getInstance().processObject(object, type);
	}
	
	public AbstractType getStoredType(int index) {
		return list.get(index).getObjectType();
	}

	public Object getStoredObject(int index) {
		return list.get(index).getObject();
	}

}
