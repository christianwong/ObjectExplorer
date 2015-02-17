package com.logexplorer.model.types;

import java.util.ArrayList;
import java.util.List;

import com.logexplorer.model.factory.TypeFactory;
import com.logexplorer.model.util.TypeUtils;


public abstract class AbstractType {

	protected String name;
	protected Object object;
	protected List<AbstractType> childs;
	
	@SuppressWarnings("unused")
	private AbstractType() {}
	
	public AbstractType(String name, Object object) {
		this.name = name;
		this.object = object;
		childs = new ArrayList<AbstractType>();
		//processObject();
	}
	
	public List<AbstractType> getChilds() {
		//System.out.println("CHILDS:BEGIN");
		
		//remove all childs to process again.
		childs.clear();

		processChilds();

		//System.out.println("CHILDS:END");
		return childs;
	}
	
	protected void addChild(String name, Object child) {
		AbstractType type = TypeFactory.getType(name, child);
		childs.add(type);
	}
	
	protected void addFirstChild(String name, Object child) {
		AbstractType type = TypeFactory.getType(name, child);
		childs.add(0, type);
	}
	
	public String getDisplayValue() {
		return TypeUtils.formatClassName(object.getClass().toString());
	}
	
	public String getDisplayName() {
		return name;
	}
	
	protected abstract void processObject();
	protected abstract void processChilds();

}
