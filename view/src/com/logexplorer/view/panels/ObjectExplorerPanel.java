package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.logexplorer.view.handlers.NodeHandler;

public class ObjectExplorerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ObjectSearchBarPanel searchPanel;
	private ObjectTreeViewPanel treePanel;
	private ObjectTextViewPanel textPanel;
	private NodeHandler handler;
	
	public ObjectExplorerPanel(Object rootSource, NodeHandler handler) {	
		
		setLayout(new BorderLayout());
		this.handler = handler;

		// init components
		searchPanel = new ObjectSearchBarPanel(this);
		add(searchPanel, BorderLayout.NORTH);
		
		treePanel = new ObjectTreeViewPanel(this, rootSource);
		treePanel.setMinimumSize(new Dimension(150, 500));
		
		textPanel = new ObjectTextViewPanel(this);
		textPanel.setMinimumSize(new Dimension(150, 500));
		
		// set up split pane
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, textPanel);
		splitPanel.setOneTouchExpandable(false);
		splitPanel.setDividerLocation(300);
		add(splitPanel, BorderLayout.CENTER);

		// set up the frame
		setMinimumSize(new Dimension(300,300));
		this.setVisible(true);
	}
	
	public ObjectTreeViewPanel getTreeViewPanel() {
		return treePanel;
	}
	
	public ObjectTextViewPanel getTextViewPanel() {
		return textPanel;
	}
	
	public ObjectSearchBarPanel getSearchBarPanel() {
		return searchPanel;
	}
	
	public NodeHandler getHandler() {
		return handler;
	}

	public void doPostInit() {
		getSearchBarPanel().doSearch();
	}
	
}
