package com.logexplorer.view.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree tree;
	private JLabel selectedLabel;

	public TreeDemo() {
		// create the root node
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("bin_object : GuessClass");
		
		// create the child nodes
		DefaultMutableTreeNode child01 = new DefaultMutableTreeNode("aInteger : 123");

		DefaultMutableTreeNode child02 = new DefaultMutableTreeNode("aDouble : 543.756");
		
		DefaultMutableTreeNode child03 = new DefaultMutableTreeNode("aSimpleClass : SimpleClass");
		child03.add(new DefaultMutableTreeNode("serialVersionUID : 1"));
		child03.add(new DefaultMutableTreeNode("abc : 123"));
		
		DefaultMutableTreeNode child04 = new DefaultMutableTreeNode("aIntArray");
		child04.add(new DefaultMutableTreeNode("length : 3"));
		child04.add(new DefaultMutableTreeNode("0 : 1"));
		child04.add(new DefaultMutableTreeNode("1 : 2"));
		child04.add(new DefaultMutableTreeNode("2 : 3"));

		// add the child nodes to the root node
		root.add(child01);
		root.add(child02);
		root.add(child03);
		root.add(child04);
		
		// add label
		selectedLabel = new JLabel();
		add(selectedLabel, BorderLayout.SOUTH);

		// create the tree by passing in the root node
		tree = new JTree(root);
		add(tree);
		add(new JScrollPane(tree));
		
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				updateLabel("E");
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				updateLabel("C");
			}
		});
		
		// selection listener
		tree.addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						updateLabel("S");
					}

				});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ViewTester");
		this.pack();
		this.setVisible(true);
	}

	private void updateLabel(String prefix) {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        selectedLabel.setText(prefix+":"+selectedNode.getUserObject().toString());
	}

	public static void run() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TreeDemo();
			}
		});
	}

}
