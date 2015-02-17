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
	
//	protected Map<String,String> map;
	protected List<Integer> list;
	protected int index;
	private DataHelper() {
//		map = new HashMap<String, String>();
		list = new ArrayList<Integer>();
//		index = 0;
	};
	
	private int getObjectHash(Object object) {
		return System.identityHashCode(object);
	}
	
	public synchronized String addData(Object object) {
		int objectHash = getObjectHash(object);
		String objectID = Integer.toString(list.size());
		
		int indexOf = list.indexOf(objectHash);
		if (indexOf >= 0) {
			objectID = Integer.toString(indexOf)+"D";
		} else {
			list.add(objectHash);
		}
//		String value = Integer.toString(index++);
		
//		// put will return a value different than null in case the object is duplicated.
//		String oldValue = map.put(key, value);
//		if (null != oldValue) {
//			value = value+"D";
//		}
		
//		if (map.containsKey(key)) {
//			value = map.get(key)+"D";
//		} else {
//			map.put(key, value);
//		}
		
		return objectID;
	}

	public String getObjectID(Object object) {
		int objectHash = getObjectHash(object);
		String objectID = null;
		
		int indexOf = list.indexOf(objectHash);
		if (indexOf > 0) {
			objectID = Integer.toString(indexOf);
		} 

		return objectID;
	}

}
