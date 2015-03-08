package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.logexplorer.view.consts.ViewConsts;
import com.logexplorer.view.events.TextViewCallback;

public class ObjectTextViewPanel extends JPanel {

	private static final String VIEW_FULL_OBJECT = "View full object";
	private static final String KNOWN_OBJECT = "Known Object:";
	
	private static final long serialVersionUID = 1L;
	private JLabel knownObjectLabel;
	private JTextPane knownObjectText;
	private JButton viewFullObjectButton;

	private ObjectExplorerPanel parent;
	private TextViewCallback callback;
	
	public ObjectTextViewPanel(ObjectExplorerPanel parent) {
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		// init components
		knownObjectLabel = new JLabel(KNOWN_OBJECT);
		add(knownObjectLabel, BorderLayout.NORTH);
		
		knownObjectText = new JTextPane();
		knownObjectText.setEditable(false);
		add(knownObjectText, BorderLayout.CENTER);
		add(new JScrollPane(knownObjectText));
//		Font font = new Font("Courier", Font.BOLD, 12);
//		knownObjectText2.setFont(font);
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
	
	public synchronized void updateText(List<Object> objects) {
		knownObjectText.setEditable(true);
		knownObjectText.setText("");

		if (objects.size() > 1) {
			appendToPane("Displaying "+objects.size()+" selected objects:\n\n", ViewConsts.TEXT_DEFAULT_COLOR, ViewConsts.BG_DEFAULT_COLOR);
		}
		
		for (Object object : objects) {
			formatObject(object);
		}
		knownObjectText.setEditable(false);
	}

	private void formatObject(Object object) {
		describeType(object, 0);//+"\n\n";
		appendToPane("\n\n", ViewConsts.TEXT_DEFAULT_COLOR, ViewConsts.BG_DEFAULT_COLOR);
	}
	
	private void describeType(Object object, int level) {
		String name = parent.getHandler().getObjectName(object);
		String type = parent.getHandler().getObjectType(object);
		String id = parent.getHandler().getObjectID(object);
		String value = parent.getHandler().getObjectValue(object);
		
		Color nameHightlight = ViewConsts.BG_DEFAULT_COLOR;
		Color valueHightlight = ViewConsts.BG_DEFAULT_COLOR;
		if (parent.getHandler().isHighlighted(object)) {
			nameHightlight = ViewConsts.BG_NAME_COLOR;
			valueHightlight = ViewConsts.BG_VALUE_COLOR;
		}
		
		if (!parent.getHandler().hasChildren(object)) {
			// Process leaves
			indentLine(level, false);
			appendToPane(name, ViewConsts.TEXT_DEFAULT_COLOR, nameHightlight);
			appendToPane("=", ViewConsts.TEXT_DEFAULT_COLOR, ViewConsts.BG_DEFAULT_COLOR);
			appendToPane(value, ViewConsts.TEXT_VALUE_COLOR, valueHightlight);
		} else {
			// Process nodes with children
			indentLine(level, !parent.getHandler().isExpanded(object));
			appendToPane(name, ViewConsts.TEXT_DEFAULT_COLOR, nameHightlight);
			appendToPane("<"+type+"> ", ViewConsts.TEXT_TYPE_COLOR, valueHightlight);
			appendToPane("(id="+id+")", ViewConsts.TEXT_DEFAULT_COLOR, valueHightlight);
			
			if (parent.getHandler().isExpanded(object)) {
				List<?> childs = parent.getHandler().getChildren(object);

				for (Object oChild : childs) {
					appendToPane("\n", ViewConsts.TEXT_DEFAULT_COLOR, ViewConsts.BG_DEFAULT_COLOR);
					describeType(oChild, level + 1);
				}
			}
		}
	}

	private void indentLine(int level, boolean canExpand) {
		String indentation = "";
		for (int idx = 0; idx < level; idx++) {
			if (idx == level - 1) {
				if (canExpand) {
					indentation += " |+ ";
				} else {
					indentation += " |- ";
				}
			} else {
				indentation += " | ";
			}
		}
		appendToPane(indentation, ViewConsts.BG_INDENT_COLOR, ViewConsts.BG_DEFAULT_COLOR);
	}
	
	private void appendToPane(String text, Color textFont, Color textHighlight) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, textFont);
		aset = sc.addAttribute(aset, StyleConstants.Background, textHighlight);
		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Courier");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

		int len = knownObjectText.getDocument().getLength();
		knownObjectText.setCaretPosition(len);
		knownObjectText.setCharacterAttributes(aset, false);
		knownObjectText.replaceSelection(text);
	}

}
