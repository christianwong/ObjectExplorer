package com.logexplorer.view.handlers;

import java.util.List;

public interface NodeHandler {
	
	public List<Object> getChildren(Object object);
	public boolean hasChildren(Object object);

}
