package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.logexplorer.view.consts.ViewConsts;

public class ObjectTextViewPanel extends JPanel {

	private static final String FULL_OBJECT = "Full object";
	private static final String KNOWN_OBJECT = "Known Object";
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JTextPane knownObjectText;
	private JTextPane fullObjectText;

	private JComponent knownObjectComponent;
	private JComponent fullObjectComponent;

	private JTextPane selectedText;
	private boolean fullTraverse;

	private ObjectExplorerPanel parent;
	
	public ObjectTextViewPanel(ObjectExplorerPanel parent) {
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		// init tabbed pane
		tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);
		
		// init known object tab
		knownObjectText = new JTextPane();
		knownObjectText.setEditable(false);
		knownObjectComponent = buildTextPanel(knownObjectText);
		tabbedPane.addTab(KNOWN_OBJECT, knownObjectComponent);
		
		// init full object tab
		fullObjectText = new JTextPane();
		fullObjectText.setEditable(false);
		fullObjectComponent = buildTextPanel(fullObjectText);
		tabbedPane.addTab(FULL_OBJECT, fullObjectComponent);
		fullObjectText.setText("full object text");
		
		initCallbacks();
		
		// set up the panel
		this.setMinimumSize(new Dimension(600,600));
		this.setVisible(true);
		
		updateSelectedPane();
	}
	
	protected void updateSelectedPane() {
		Component selectedComponent = tabbedPane.getSelectedComponent();
		if (selectedComponent == knownObjectComponent) {
			this.selectedText = knownObjectText;
			this.fullTraverse = false;
		} else {
			this.selectedText = fullObjectText;
			this.fullTraverse = true;
		}
	}
	
	protected JComponent buildTextPanel(JTextPane pane) {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(pane);
		panel.add(new JScrollPane(pane));
		return panel;
	}


	private void initCallbacks() {
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				updateSelectedPane();
				parent.getTreeViewPanel().processSelectedObjects();
			}
		});
	}
	
	public synchronized void updateText(List<Object> objects) {
		selectedText.setEditable(true);
		selectedText.setText("");

		if (objects.size() > 1) {
			appendToPane("Displaying "+objects.size()+" selected objects:\n\n", ViewConsts.TEXT_DEFAULT_COLOR, ViewConsts.BG_DEFAULT_COLOR);
		}
		
		for (Object object : objects) {
			formatObject(object);
		}
		selectedText.setEditable(false);
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
			indentLine(level, !parent.getHandler().isExpanded(object) && !fullTraverse);
			appendToPane(name, ViewConsts.TEXT_DEFAULT_COLOR, nameHightlight);
			appendToPane("<"+type+"> ", ViewConsts.TEXT_TYPE_COLOR, valueHightlight);
			appendToPane("(id="+id+")", ViewConsts.TEXT_DEFAULT_COLOR, valueHightlight);
			
			if (parent.getHandler().isExpanded(object) || fullTraverse) {
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

		int len = selectedText.getDocument().getLength();
		selectedText.setCaretPosition(len);
		selectedText.setCharacterAttributes(aset, false);
		selectedText.replaceSelection(text);
	}

}
