package com.logexplorer.view.panels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ObjectTextViewPanel extends JFrame {

	private static final String VIEW_FULL_OBJECT = "View full object";
	private static final String KNOWN_OBJECT = "Known Object:";
	
	private static final long serialVersionUID = 1L;
	private JLabel knownObjectLabel;
	private JTextArea knownObjectText;
	private JButton viewFullObjectButton;
	
	public ObjectTextViewPanel() {
		
		// init components
		knownObjectLabel = new JLabel(KNOWN_OBJECT);
		add(knownObjectLabel, BorderLayout.NORTH);
		
		knownObjectText = new JTextArea();
		add(knownObjectText, BorderLayout.CENTER);
		
		viewFullObjectButton = new JButton(VIEW_FULL_OBJECT);
		add(viewFullObjectButton, BorderLayout.SOUTH);
		
		// set up the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ObjectTreeViewPanel");
		this.pack();
		this.setVisible(true);
	}

	public static void demo() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ObjectTextViewPanel();
			}
		});
	}

}
