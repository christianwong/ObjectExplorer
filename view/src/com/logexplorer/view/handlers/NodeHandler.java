package com.logexplorer.view.handlers;

import java.util.List;

public interface NodeHandler {
	
	public List<?> getChildren(Object object);
	public boolean hasChildren(Object object);
	public void setExpanded(Object object, boolean expanded);
	public boolean isExpanded(Object object);
	public boolean isHighlighted(Object object);
	
	public String getObjectName(Object object);
	public String getObjectType(Object object);
	public String getObjectID(Object object);
	public String getObjectValue(Object object);

}
