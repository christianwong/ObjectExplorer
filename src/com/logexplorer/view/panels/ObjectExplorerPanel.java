package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.model.types.AbstractType;

public class ObjectExplorerPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private ObjectSearchBarPanel searchPanel;
	private ObjectTreeViewPanel treePanel;
	private ObjectTextViewPanel textPanel;
	
	public ObjectExplorerPanel(AbstractType type) {	
		
		setMinimumSize(new Dimension(300,300));

		// init components
		searchPanel = new ObjectSearchBarPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		treePanel = new ObjectTreeViewPanel(type);
		add(treePanel, BorderLayout.CENTER);
		
		textPanel = new ObjectTextViewPanel();
		add(textPanel, BorderLayout.EAST);
				
		// set up the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ObjectExplorerPanel");
		this.pack();
		this.setVisible(true);
	}
	
	public static void demo(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ObjectExplorerPanel(type);
			}
		});
	}

}
