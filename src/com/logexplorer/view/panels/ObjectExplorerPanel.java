package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.logexplorer.model.types.AbstractType;

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
	
	public static void demo(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.add(new ObjectExplorerPanel(type), BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
