package com.logexplorer.view.util;


public class TypeUtils {
	
	public static int getCodeFromName(String name) {
		String code=name.substring(name.lastIndexOf("=")+1, name.lastIndexOf(")"));
		return Integer.parseInt(code);
	}
	
}
