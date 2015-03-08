package com.logexplorer.view.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.logexplorer.view.consts.ViewConsts;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.utils.ViewUtils;

public class ObjectSearchBarPanel extends JPanel {

	private static final String SEARCH_ALL = ".*";
	private static final String GO = "Go!";
	private static final String VALUE = "value";
	private static final String ATTRIBUTE = "attribute";
	private static final String SEARCH = "Search:";

	private static FocusListener attributeTextFocusListener;
	private static FocusListener valueTextFocusListener;
	
	static {
		attributeTextFocusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				e.getComponent().setBackground(ViewConsts.BG_DEFAULT_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				e.getComponent().setBackground(ViewConsts.BG_NAME_COLOR);
			}
		};

		valueTextFocusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				e.getComponent().setBackground(ViewConsts.BG_DEFAULT_COLOR);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				e.getComponent().setBackground(ViewConsts.BG_VALUE_COLOR);
			}
		};
}

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
		add(attributeLabel);
		
		attributeText = new JTextField(SEARCH_ALL, 15);
		attributeText.addFocusListener(attributeTextFocusListener);
		add(attributeText);
		
		valueLabel = new JLabel(VALUE);
		add(valueLabel);
		
		valueText = new JTextField(SEARCH_ALL, 15);
		valueText.addFocusListener(valueTextFocusListener);
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
				
				ViewUtils.setDefaultValue(attributeLabel, SEARCH_ALL);
				ViewUtils.setDefaultValue(valueLabel, SEARCH_ALL);

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
