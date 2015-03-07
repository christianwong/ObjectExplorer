package com.logexplorer.view.handlers;

import java.util.List;

public interface NodeHandler {
	
	public List<?> getChildren(Object object);
	public boolean hasChildren(Object object);
	public boolean hasKnownChildren(Object object);
	
	public String getObjectName(Object object);
	public String getObjectType(Object object);
	public String getObjectID(Object object);
	public String getObjectValue(Object object);

}
