package com.logexplorer.view.utils;


public final class ViewUtils {
	
	private static final String POSTFIX = ")";
	private static final String PREFIX = " (id=";

	public static int decodeNodeName(String name) {
		String code=name.substring(name.lastIndexOf(PREFIX)+PREFIX.length(), name.lastIndexOf(POSTFIX));
		return Integer.parseInt(code);
	}
	
	public static String encodeNodeName(String name, int code) {
		return name+PREFIX+code+POSTFIX;
	}
	
}
