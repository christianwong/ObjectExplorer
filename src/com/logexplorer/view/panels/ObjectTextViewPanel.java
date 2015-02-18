package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.logexplorer.view.events.TextViewCallback;

public class ObjectTextViewPanel extends JPanel {

	private static final String VIEW_FULL_OBJECT = "View full object";
	private static final String KNOWN_OBJECT = "Known Object:";
	
	private static final long serialVersionUID = 1L;
	private JLabel knownObjectLabel;
	private JTextArea knownObjectText;
	private JButton viewFullObjectButton;
	
	public ObjectTextViewPanel() {
		
		setLayout(new BorderLayout());
		
		// init components
		knownObjectLabel = new JLabel(KNOWN_OBJECT);
		add(knownObjectLabel, BorderLayout.NORTH);
		
		knownObjectText = new JTextArea();
//		knownObjectText.setEnabled(false);
		add(knownObjectText, BorderLayout.CENTER);
		
		viewFullObjectButton = new JButton(VIEW_FULL_OBJECT);
//		viewFullObjectButton.setEnabled(false);
		add(viewFullObjectButton, BorderLayout.SOUTH);
		
		// TODO - remove from here
		setTextViewCallback(new TextViewCallback() {
			
			@Override
			public void onViewFullObject() {
				System.out.println("TextViewCallback called!");
			}
		});
		
		// set up the panel
		this.setMinimumSize(new Dimension(600,600));
		this.setVisible(true);
	}
	
	public void setTextViewCallback(final TextViewCallback callback) {
		
		// TODO - remove previous listeners
		
		viewFullObjectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				callback.onViewFullObject();
			}
		});
	}
	
	public static void demo() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				ObjectTextViewPanel panel = new ObjectTextViewPanel();
//				panel.setTextViewCallback(new TextViewCallback() {
//					
//					@Override
//					public void onViewFullObject() {
//						System.out.println("TextViewCallback called!");
//					}
//				});
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
