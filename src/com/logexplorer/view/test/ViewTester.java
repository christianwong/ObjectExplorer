package com.logexplorer.view.test;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class ViewTester extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree tree;

	public ViewTester() {
		// create the root node
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("bin_object : GuessClass");
		
		// create the child nodes
		DefaultMutableTreeNode child01 = new DefaultMutableTreeNode("aInteger : 123");

		DefaultMutableTreeNode child02 = new DefaultMutableTreeNode("aDouble : 543.756");
		
		DefaultMutableTreeNode child03 = new DefaultMutableTreeNode("aSimpleClass  : SimpleClass");
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

		// create the tree by passing in the root node
		tree = new JTree(root);
		add(tree);
		add(new JScrollPane(tree));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ViewTester");
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewTester();
			}
		});
	}

}
