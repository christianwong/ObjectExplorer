package com.logexplorer.model.types;

import java.util.ArrayList;
import java.util.List;

import com.logexplorer.model.consts.TypeConstants;
import com.logexplorer.model.factory.TypeFactory;
import com.logexplorer.model.helper.DataHelper;
import com.logexplorer.model.util.TypeUtils;


public abstract class AbstractType {

	protected String name;
	protected Object object;
	protected String objectID;
	protected List<AbstractType> childs;
	
	@SuppressWarnings("unused")
	private AbstractType() {}
	
	public AbstractType(String name, Object object) {
		this.name = name;
		this.object = object;
		this.childs = new ArrayList<AbstractType>();
		
		this.objectID = DataHelper.getObjectID(this.object);
	}
	
	public List<AbstractType> getChilds() {
		resetChilds();
		processChilds();
		return childs;
	}
	
	public boolean hasKnownChilds() {
		return !childs.isEmpty();
	}
	
	public abstract boolean hasChilds();
	
	public String getDisplayValue() {
		return null==object ? TypeConstants.NULL : TypeUtils.formatClassName(object.getClass().toString())+" (id="+objectID+")";
	}
	
	public String getDisplayName() {
		return name;
	}
	
	public void resetChilds() {
		childs.clear();
	}
	
	protected abstract void processChilds();

	protected void addChild(String name, Object child) {
		AbstractType type = TypeFactory.getType(name, child);
		childs.add(type);
	}
	
	protected void addFirstChild(String name, Object child) {
		AbstractType type = TypeFactory.getType(name, child);
		childs.add(0, type);
	}
	
}
