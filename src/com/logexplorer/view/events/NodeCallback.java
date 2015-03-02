package com.logexplorer.view.events;

import javax.swing.JTree;

public interface NodeCallback {

	public void onExpandNode(JTree tree, int code);
	public void onCollapseNode(JTree tree, int code);
	public void onClickNode(JTree tree, int code);
	
}
