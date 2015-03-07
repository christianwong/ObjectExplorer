package com.logexplorer.view.events;

import java.util.EventObject;

public interface NodeCallback {

	public void onExpandNode(EventObject event);
	public void onCollapseNode(EventObject event);
	public void onClickNode(EventObject event);
	
}
