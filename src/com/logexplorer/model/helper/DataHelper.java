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
	
	protected List<Integer> list;
	// TODO store reference to object and AbstractType in the list.
	
	private DataHelper() {
		list = new ArrayList<Integer>();
	};
	
	private int getObjectHash(Object object) {
		return System.identityHashCode(object);
	}
	
	private synchronized String processObject(Object object, AbstractType type) {
		int objectHash = getObjectHash(object);
		String objectID = Integer.toString(list.size());
		
		int indexOf = list.indexOf(objectHash);
		if (indexOf >= 0) {
			objectID = Integer.toString(indexOf)+"D";
		} else {
			list.add(objectHash);
		}

		System.out.println("object Hash: '"+objectHash+"' was assigned to objectID: '"+objectID+"'.. Object is: '"+object.toString()+"'.");
		return objectID;
	}
	
	public static String getObjectID(Object object, AbstractType type) {
		return DataHelper.getInstance().processObject(object, type);
	}

//	public String getObjectID(Object object) {
//		int objectHash = getObjectHash(object);
//		String objectID = null;
//		
//		int indexOf = list.indexOf(objectHash);
//		if (indexOf > 0) {
//			objectID = Integer.toString(indexOf);
//		} 
//
//		return objectID;
//	}

}
