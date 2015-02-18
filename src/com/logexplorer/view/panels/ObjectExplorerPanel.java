package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

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
		
		GridLayout bodyPanelLayout = new GridLayout(1,2);
		bodyPanelLayout.setHgap(5);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(bodyPanelLayout);
		add(bodyPanel, BorderLayout.CENTER);
		
		treePanel = new ObjectTreeViewPanel(type);
		bodyPanel.add(treePanel);
		
		textPanel = new ObjectTextViewPanel();
		bodyPanel.add(textPanel);
				
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
