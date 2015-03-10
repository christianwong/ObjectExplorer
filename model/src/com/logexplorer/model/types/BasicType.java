package com.logexplorer.model.types;

import com.logexplorer.model.consts.TypeConstants;
import com.logexplorer.model.types.util.NestedType;
import com.logexplorer.model.types.util.NullType;

public class BasicType extends AbstractType {

	public BasicType(String name, Object object, AbstractType parent) {
		super(name, object, parent);
	}
	
	public static boolean isBasicType(Object object) {
		return object instanceof Number
				|| object instanceof Boolean
				|| object instanceof Character
				|| object instanceof String
				|| object instanceof NullType
				|| object instanceof NestedType;
	}

	@Override
	public void processChilds() {}

	@Override
	public String getValue() {
		return null==getObject() ? TypeConstants.NULL : formatValue();
	}

	private String formatValue() {
		String value = getObject().toString();
		if (getObject() instanceof Character) {
			value = "'"+value+"'";
		} else if (getObject() instanceof String) {
			value = "\""+value+"\"";
		}
		return value;
	}

	@Override
	public boolean hasChilds() {
		return false;
	}
	
	@Override
	public void setExpanded(boolean expanded) {
		this.expanded = false;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
