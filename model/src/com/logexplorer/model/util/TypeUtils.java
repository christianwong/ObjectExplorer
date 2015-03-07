package com.logexplorer.model.util;

import java.lang.reflect.Field;
import java.util.List;

import com.logexplorer.model.consts.TypeConstants;
import com.logexplorer.model.types.AbstractType;

public class TypeUtils {
	
	public static Object getFieldValue(Object object, Field field) {
		Object value = null;
		try {
			value = field.get(object);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void resetAccessible(Field field, boolean accessible) {
		field.setAccessible(accessible);
	}

	public static boolean enableAccessible(Field field) {
		boolean accessible = field.isAccessible();
		if(!accessible) {
			field.setAccessible(true);
		}
		return accessible;
	}

	public static String formatClassName(String originalName) {
		String name = originalName;
		
		// Check if is a basic data type (from array)
		String basicType = TypeConstants.typesMap.get(originalName);
		if (null!=basicType) {
			return basicType;
		}
		
		name = removeColon(name);
		name = removeClass(name);
		name = processArray(name);
		name = removePackageInformation(name);
		
		return name;
	}

	private static String removePackageInformation(String name) {
		if (name.contains(TypeConstants.DOT)) {
			name = name.substring(name.lastIndexOf(TypeConstants.DOT)+1);
		}
		return name;
	}

	private static String processArray(String name) {
		if (name.startsWith(TypeConstants.ARRAY_NOTATION)) {
			name = formatClassName(name.substring(TypeConstants.ARRAY_NOTATION.length()))+"[]"; 
		}
		return name;
	}

	private static String removeClass(String name) {
		if (name.startsWith(TypeConstants.CLASS)) {
			name = name.substring(TypeConstants.CLASS.length());
		}
		return name;
	}

	private static String removeColon(String name) {
		if (name.endsWith(TypeConstants.COLON)) {
			name = name.substring(0, name.length()-1);
		}
		return name;
	}

	public static String describeFullType(AbstractType type) {
		return describeType(type, true, 0);
	}
	
	public static String describeKnownType(AbstractType type) {
		return describeType(type, false, 0);
	}

	private static String describeType(AbstractType type, boolean forceExpand, int level) {
		String name = type.getName();
		String value = type.getValue();
		
		String indentation = getIndentation(level);
		String typeString = indentation+name+":"+value;

		if (forceExpand || type.isExpanded()) {
			List<?> childs = type.getChilds();

			for(Object oChild : childs) {
				AbstractType child = (AbstractType) oChild;
				typeString += "\n"+describeType(child, forceExpand, level+1);
			}
		}
		
		return typeString;
	}

	private static String getIndentation(int level) {
		String indentation = "";
		for (int idx = 0; idx < level; idx++) {
			if (idx == level - 1) {
				indentation += " |- ";
			} else {
				indentation += " | ";
			}
		}
		return indentation;
	}
	
}
