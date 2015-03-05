package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class ObjectExplorerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ObjectSearchBarPanel searchPanel;
	private ObjectTreeViewPanel treePanel;
	private ObjectTextViewPanel textPanel;
	
	public ObjectExplorerPanel() {	
		
		setLayout(new BorderLayout());

		// init components
		searchPanel = new ObjectSearchBarPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		treePanel = new ObjectTreeViewPanel();
		treePanel.setMinimumSize(new Dimension(150, 500));
		
		textPanel = new ObjectTextViewPanel();
		textPanel.setMinimumSize(new Dimension(150, 500));
		
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, textPanel);
		splitPanel.setOneTouchExpandable(false);
		splitPanel.setDividerLocation(200);
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
	
}
