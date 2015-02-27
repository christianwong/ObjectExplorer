package com.logexplorer.model.helper;

import java.util.ArrayList;
import java.util.List;

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
	protected List<Object> objects;
	
	private DataHelper() {
		list = new ArrayList<Integer>();
		objects = new ArrayList<Object>();
	};
	
	private int getObjectHash(Object object) {
		return System.identityHashCode(object);
	}
	
	private synchronized String processObject(Object object) {
		int objectHash = getObjectHash(object);
		String objectID = Integer.toString(list.size());
		
		int indexOf = list.indexOf(objectHash);
		if (indexOf >= 0) {
			objectID = Integer.toString(indexOf)+"D";
		} else {
			list.add(objectHash);
			objects.add(object);
		}

		System.out.println("object Hash: '"+objectHash+"' was assigned to objectID: '"+objectID+"'.. Object is: '"+object.toString()+"'.");
		return objectID;
	}
	
	public static String getObjectID(Object object) {
		return DataHelper.getInstance().processObject(object);
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
