package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.logexplorer.model.types.AbstractType;
import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.events.TextViewCallback;

public class ObjectExplorerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ObjectSearchBarPanel searchPanel;
	private ObjectTreeViewPanel treePanel;
	private ObjectTextViewPanel textPanel;
	
	public ObjectExplorerPanel(AbstractType type) {	
		
		setLayout(new BorderLayout());

		// init components
		searchPanel = new ObjectSearchBarPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		treePanel = new ObjectTreeViewPanel(type);
		treePanel.setMinimumSize(new Dimension(150, 500));
		
		textPanel = new ObjectTextViewPanel();
		textPanel.setMinimumSize(new Dimension(150, 500));
		
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, textPanel);
		splitPanel.setOneTouchExpandable(false);
		splitPanel.setDividerLocation(150);
		add(splitPanel, BorderLayout.CENTER);

		// set up the frame
		setMinimumSize(new Dimension(300,300));
		this.setVisible(true);
	}
	
	public void setNodeCallback(final NodeCallback callback) {
		treePanel.setNodeCallback(callback);
	}
	
	public void setTextViewCallback(final TextViewCallback callback) {
		textPanel.setTextViewCallback(callback);
	}
	
	public void setSearchCallback(final SearchCallback callback) {
		searchPanel.setSearchCallback(callback);
	}
	
	public void setKnownObjectText(String description) {
		textPanel.setKnownObjectText(description);
	}
	
}
