package com.logexplorer.model.helper;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private synchronized String processObject(Object object, AbstractType type) {
		
		DataHelperInstance instance = new DataHelperInstance(object, type);
		String objectID = Integer.toString(list.size());
		
		int indexOf = Collections.binarySearch(list, instance, new DataHelperInstanceComparator());
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

}
