package com.logexplorer.view.events;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

public interface NodeCallback {

	public void onExpandNode(JTree tree, TreePath path, int code);
	public void onCollapseNode(JTree tree, TreePath path, int code);
	public void onClickNode(JTree tree, int code);
	
}
