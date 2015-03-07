package com.logexplorer.view.events;

import java.util.EventObject;

public interface NodeCallback {

	public void expandNode(EventObject event);
	public void collapseNode(EventObject event);
	public void clickNode(EventObject event);
	
}
