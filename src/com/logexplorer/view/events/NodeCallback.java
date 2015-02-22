package com.logexplorer.view.events;

public interface NodeCallback {

	public void onExpandNode(String code);
	public void onCollapseNode(String code);
	public void onClickNode(String code);
	
}
