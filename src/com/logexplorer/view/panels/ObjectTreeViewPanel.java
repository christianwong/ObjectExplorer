package com.logexplorer.view.panels;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import com.logexplorer.model.types.AbstractType;

public class ObjectTreeViewPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree tree;

	public ObjectTreeViewPanel(AbstractType type) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(type.getDisplayName());

		// add childs to tree
		for (AbstractType child : type.getChilds()) {
			DefaultMutableTreeNode dummyChild = new DefaultMutableTreeNode(child.getDisplayName());
			root.add(dummyChild);
		}
		
		// set up the tree
		tree = new JTree(root);
		add(tree);
		add(new JScrollPane(tree));
		
		// set up the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ObjectTreeViewPanel");
		this.pack();
		this.setVisible(true);
	}
	
	public static void demo(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ObjectTreeViewPanel(type);
			}
		});
	}}
