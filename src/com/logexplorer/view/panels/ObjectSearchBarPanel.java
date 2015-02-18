package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.logexplorer.view.events.SearchCallback;

public class ObjectSearchBarPanel extends JPanel {

	private static final String GO = "Go!";
	private static final String VALUE = "value";
	private static final String ATTRIBUTE = "attribute";
	private static final String SEARCH = "Search:";
	
	private static final long serialVersionUID = 1L;
	private JLabel searchLabel;
	private JLabel attributeLabel;
	private JTextField attributeText;
	private JLabel valueLabel;
	private JTextField valueText;
	private JButton searchButton;
	
	public ObjectSearchBarPanel() {
		
		setLayout(new FlowLayout());
		
		// init components
		searchLabel = new JLabel(SEARCH);
		add(searchLabel);
		
		attributeLabel = new JLabel(ATTRIBUTE);
//		attributeLabel.setEnabled(false);
		add(attributeLabel);
		
		attributeText = new JTextField(15);
//		attributeText.setEnabled(false);
		add(attributeText);
		
		valueLabel = new JLabel(VALUE);
//		valueLabel.setEnabled(false);
		add(valueLabel);
		
		valueText = new JTextField(15);
//		valueText.setEnabled(false);
		add(valueText);
		
		searchButton = new JButton(GO);
//		searchButton.setEnabled(false);
		add(searchButton);
		
		// TODO - Remove from here
		setSearchCallback(new SearchCallback() {
			
			@Override
			public void onSearch(String attribute, String value) {
				System.out.println("SearchCallback called!! attribute:'"+attribute+"', value'"+value+"'.");
			}
		});
				
		// set up the panel
		this.setVisible(true);
	}
	
	public void setSearchCallback(final SearchCallback callback) {
		
		// TODO - remove previous listeners
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				callback.onSearch(attributeText.getText(), valueText.getText());
			}
		});
	}
	
	public static void demo() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				ObjectSearchBarPanel panel = new ObjectSearchBarPanel();
//				panel.setSearchCallback(new SearchCallback() {
//					
//					@Override
//					public void onSearch(String attribute, String value) {
//						System.out.println("SearchCallback called!! attribute:'"+attribute+"', value'"+value+"'.");
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
