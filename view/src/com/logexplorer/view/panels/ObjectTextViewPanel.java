package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

	private ObjectExplorerPanel parent;
	private TextViewCallback callback;
	
	public ObjectTextViewPanel(ObjectExplorerPanel parent) {
		this.parent = parent;
		
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
	
	public void setCallback(final TextViewCallback callback) {
		this.callback = callback;
	}
	
	public void setKnownObjectText(String description) {
		knownObjectText.setText(description);
	}

	public void updateText(List<Object> objects) {
		String description = "";
		
		if (objects.size() > 1) {
			description += "Displaying "+objects.size()+" selected objects.\n\n";
		}
		
		for (Object object : objects) {
			description += formatObject(object)+"\n\n";
		}
		
		setKnownObjectText(description);
	}

	private String formatObject(Object object) {
		return describeType(object, 0);
	}
	
	private String describeType(Object object, int level) {
		String name = parent.getHandler().getObjectName(object);
		String type = parent.getHandler().getObjectType(object);
		String id = parent.getHandler().getObjectID(object);
		String value = parent.getHandler().getObjectValue(object);
		
		String indentation = getIndentation(level);
		String typeString = "";

		if (!parent.getHandler().hasChildren(object)) {
			typeString = indentation+name+"="+value;
		} else {
			typeString = indentation+name+"<"+type+"> (id="+id+")";
			
			if (parent.getHandler().isExpanded(object)) {
				List<?> childs = parent.getHandler().getChildren(object);

				for (Object oChild : childs) {
					typeString += "\n" + describeType(oChild, level + 1);
				}
			}
		}
		
		return typeString;
	}

	private static String getIndentation(int level) {
		String indentation = "";
		for (int idx = 0; idx < level; idx++) {
			if (idx == level - 1) {
				indentation += " |- ";
			} else {
				indentation += " | ";
			}
		}
		return indentation;
	}
	
}
