package com.logexplorer.model.types;

import java.util.ArrayList;
import java.util.List;

import com.logexplorer.model.consts.TypeConstants;
import com.logexplorer.model.factory.TypeFactory;
import com.logexplorer.model.helper.DataHelper;
import com.logexplorer.model.util.TypeUtils;


public abstract class AbstractType {

	protected String name;
	protected int objectID;
	protected List<AbstractType> childs;
	
	@SuppressWarnings("unused")
	private AbstractType() {}
	
	public AbstractType(String name, Object object) {
		this.name = name;
		this.childs = new ArrayList<AbstractType>();
		
		this.objectID = DataHelper.getObjectID(object, this);
		
//		System.out.println("Type created for object ("+this.name+") with ID = '"+DataHelperInstance.getHashCode(object)+"'");
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
	
	public String getValue() {
		Object object = getObject();
		return null==object ? TypeConstants.NULL : TypeUtils.formatClassName(object.getClass().toString())+" (id="+objectID+")";
	}
	
	protected Object getObject() {
		return DataHelper.getInstance().getStoredObject(this.objectID);
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		String rawType = DataHelper.getInstance().getStoredObject(this.objectID).getClass().toString();
		return TypeUtils.formatClassName(rawType);
	}
	
	public String getID() {
		return Integer.toString(this.objectID);
	}
	
	@Override
	public String toString() {
		return name+" (id="+objectID+")";
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
