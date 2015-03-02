package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.logexplorer.view.events.TextViewCallback;

public class ObjectTextViewPanel extends JPanel {

	private static final String VIEW_FULL_OBJECT = "View full object";
	private static final String KNOWN_OBJECT = "Known Object:";
	
	private static final long serialVersionUID = 1L;
	private JLabel knownObjectLabel;
	private JTextArea knownObjectText;
	private JButton viewFullObjectButton;
	private TextViewCallback callback;
	
	public ObjectTextViewPanel() {
		
		setLayout(new BorderLayout());
		
		// init components
		knownObjectLabel = new JLabel(KNOWN_OBJECT);
		add(knownObjectLabel, BorderLayout.NORTH);
		
		knownObjectText = new JTextArea();
		knownObjectText.setEditable(false);
		add(knownObjectText, BorderLayout.CENTER);
		add(new JScrollPane(knownObjectText));
		Font font = new Font("Courier", Font.BOLD, 12);
		knownObjectText.setFont(font);
//		knownObjectText.setForeground(Color.BLUE);

		viewFullObjectButton = new JButton(VIEW_FULL_OBJECT);
		viewFullObjectButton.setEnabled(false);
		add(viewFullObjectButton, BorderLayout.SOUTH);
		
		initCallbacks();
		
		// set up the panel
		this.setMinimumSize(new Dimension(600,600));
		this.setVisible(true);
	}

	private void initCallbacks() {
		callback = null;
		viewFullObjectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (null != callback) {
					callback.onViewFullObject();
				}
			}
		});
	}
	
	public void setTextViewCallback(final TextViewCallback callback) {
		this.callback = callback;
	}
	
	public void setKnownObjectText(String description) {
		knownObjectText.setText(description);
	}
	
}
