package com.logexplorer.model.types.util;

import com.logexplorer.model.types.AbstractType;

public class NestedType {
	
	AbstractType parent;

	public NestedType(AbstractType parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "::Reference to parent::"+parent.toString()+"::";
	}

}
