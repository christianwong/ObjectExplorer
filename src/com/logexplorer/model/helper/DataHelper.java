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
	public static DataHelper getInstance() {
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
	private synchronized String processObject(Object object, AbstractType type) {
		
		DataHelperInstance instance = new DataHelperInstance(object, type);
		String objectID = Integer.toString(list.size());
		
		// somehow indexOf is not calling DataHelperInstance.equals()
//		int indexOf = ((ArrayList<DataHelperInstance>)list).indexOf(instance);
		int indexOf = -1;
		for (int idx=0; idx<list.size(); idx++) {
			DataHelperInstance inst = list.get(idx);
			if (inst.getObjectHash() == instance.getObjectHash()) {
				indexOf = idx;
				break;
			}
		}
		
		if (indexOf >= 0) {
			objectID = Integer.toString(indexOf)+"D";
		} else {
			list.add(instance);
		}

		System.out.println("object Hash: '"+instance.getObjectHash()+"' was assigned to objectID: '"+objectID+"'.. Object is: '"+object.toString()+"'.");
		return objectID;
	}
	
	public static String getObjectID(Object object, AbstractType type) {
		return DataHelper.getInstance().processObject(object, type);
	}
	
	public AbstractType getStoredType(int index) {
		return list.get(index).getObjectType();
	}

}
