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
	protected int objectID;
	protected List<AbstractType> childs;
	protected boolean expanded = false;
	protected String fingerprint = ".";
	
	@SuppressWarnings("unused")
	private AbstractType() {}
	
	public AbstractType(String name, Object object, AbstractType parent) {
		this.name = name;
		this.object = object;
		this.childs = new ArrayList<AbstractType>();
		
		this.objectID = DataHelper.getObjectID(object, this);
		
		if (null != parent) {
			this.fingerprint = parent.getFingerprint();
		}
		this.fingerprint += objectID+".";
		
		processChilds();
	}
	
	private String getFingerprint() {
		return this.fingerprint;
	}
	
	public boolean isChildAllowed(Object object) {
		String objectID = "."+DataHelper.getObjectID(object, this)+".";
		return !this.fingerprint.contains(objectID);
	}

	public List<AbstractType> getChilds() {
		return childs;
	}
	
	public boolean isExpanded() {
		return expanded;
	}
	
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
	public abstract boolean hasChilds();
	
	public String getValue() {
		Object object = getObject();
		return null==object ? TypeConstants.NULL : TypeUtils.formatClassName(object.getClass().toString())+" (id="+objectID+")";
	}
	
	protected Object getObject() {
		return object;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		String rawType = getObject().getClass().toString();
		return TypeUtils.formatClassName(rawType);
	}
	
	public String getID() {
		return Integer.toString(this.objectID);
	}
	
	@Override
	public String toString() {
		return name+" (id="+objectID+")";
	}
	
	protected abstract void processChilds();

	protected void addChild(String name, Object child) {
		AbstractType type = TypeFactory.getType(name, child, this);
		childs.add(type);
	}
	
	protected void addFirstChild(String name, Object child) {
		AbstractType type = TypeFactory.getType(name, child, this);
		childs.add(0, type);
	}
	
}
