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

public class ObjectSearchBarPanel extends JPanel {

	private static final String DEFAULT_ATTRIBUTE_SEARCH = ".*(id|ID|Id).*";
	private static final String DEFAULT_VALUE_SEARCH = "('|\")?[0-9]+.?('|\")?";
	private static final String GO = "Go!";
	private static final String RESET = "Reset";
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
	private JTextField attributeText;
	private JTextField valueText;
	private JButton searchButton;
	private JButton resetButton;
	private SearchCallback callback;
	
	public ObjectSearchBarPanel() {
		
		setLayout(new FlowLayout());
		
		// init components
		add(new JLabel(SEARCH));
		
		add(new JLabel(ATTRIBUTE));
		
		attributeText = new JTextField(DEFAULT_ATTRIBUTE_SEARCH, 15);
		attributeText.addFocusListener(attributeTextFocusListener);
		add(attributeText);
		
		add(new JLabel(VALUE));
		
		valueText = new JTextField(DEFAULT_VALUE_SEARCH, 15);
		valueText.addFocusListener(valueTextFocusListener);
		add(valueText);
		
		searchButton = new JButton(GO);
		add(searchButton);
		
		resetButton = new JButton(RESET);
		add(resetButton);
		
		initCallbacks();
				
		// set up the panel
		this.setVisible(true);
	}

	private void initCallbacks() {
		callback = null;
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearch();
			}
		});
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				attributeText.setText(DEFAULT_ATTRIBUTE_SEARCH);
				valueText.setText(DEFAULT_VALUE_SEARCH);
				
				doSearch();
			}
		});
	}
	
	public void setCallback(final SearchCallback callback) {
		this.callback = callback;
		doSearch();
	}

	private void doSearch() {
		if (callback != null) {
			callback.onSearch(attributeText.getText(), valueText.getText());
		}
	}
	
}
