package com.logexplorer.view.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.utils.ViewUtils;

public class ObjectSearchBarPanel extends JPanel {

	private static final String STAR = ".*";
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
	private SearchCallback callback;
	
	public ObjectSearchBarPanel() {
		
		setLayout(new FlowLayout());
		
		// init components
		searchLabel = new JLabel(SEARCH);
		add(searchLabel);
		
		attributeLabel = new JLabel(ATTRIBUTE);
//		attributeLabel.setEnabled(false);
		add(attributeLabel);
		
		attributeText = new JTextField(STAR, 15);
//		attributeText.setEnabled(false);
//		attributeText.setBackground(Color.ORANGE);
		add(attributeText);
		
		valueLabel = new JLabel(VALUE);
//		valueLabel.setEnabled(false);
		add(valueLabel);
		
		valueText = new JTextField(STAR, 15);
//		valueText.setEnabled(false);
//		valueText.setBackground(Color.CYAN);
		add(valueText);
		
		searchButton = new JButton(GO);
//		searchButton.setEnabled(false);
		add(searchButton);
		
		initCallbacks();
				
		// set up the panel
		this.setVisible(true);
	}

	private void initCallbacks() {
		callback = null;
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ViewUtils.setDefaultValue(attributeLabel, STAR);
				ViewUtils.setDefaultValue(valueLabel, STAR);

				if (callback != null) {
					callback.onSearch(attributeText.getText(), valueText.getText());
				}
			}
		});
	}
	
	public void setCallback(final SearchCallback callback) {
		this.callback = callback;
	}
	
}
