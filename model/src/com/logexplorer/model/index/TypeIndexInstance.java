package com.logexplorer.model.index;

import com.logexplorer.model.types.AbstractType;

public class TypeIndexInstance {
	
	protected String name;
	protected String value;
	protected AbstractType type;
	
	public TypeIndexInstance(String name, String value, AbstractType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public AbstractType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "name='"+name+"' value='"+value+"' type='"+type.toString()+"'";
	}

}
