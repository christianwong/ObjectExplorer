package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import com.logexplorer.model.types.AbstractType;

public class ObjectTreeViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTree tree;

	public ObjectTreeViewPanel(AbstractType type) {
		
		setLayout(new BorderLayout());
		
		// init components
		initTree(type);
		
		// set up the frame
		this.setMinimumSize(new Dimension(500,600));
		this.setVisible(true);
	}

	private void initTree(AbstractType type) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(type.getDisplayName());

		// add childs to tree
		for (AbstractType child : type.getChilds()) {
			DefaultMutableTreeNode dummyChild = new DefaultMutableTreeNode(child.getDisplayName());
			root.add(dummyChild);
		}
		
		// set up the tree
		tree = new JTree(root);
		add(tree, BorderLayout.CENTER);
		add(new JScrollPane(tree));
	}
	
	public static void demo(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.add(new ObjectTreeViewPanel(type), BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
	
}
