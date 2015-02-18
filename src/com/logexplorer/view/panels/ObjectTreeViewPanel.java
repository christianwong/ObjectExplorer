package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.logexplorer.model.types.AbstractType;
import com.logexplorer.view.events.NodeCallback;

public class ObjectTreeViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTree tree;
	private NodeCallback callback;

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
//			dummyChild.add(new DefaultMutableTreeNode("dummy"));
			root.add(dummyChild);
		}
		
		// set up the tree
		tree = new JTree(root);
		tree.setShowsRootHandles(true);
		
		// Hide icons from tree
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);
		renderer.setDisabledIcon(null);
		tree.setCellRenderer(renderer);
		
		initCallbacks();
		
		add(tree, BorderLayout.CENTER);
		add(new JScrollPane(tree));
	}

	private void initCallbacks() {
		callback = null;
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				if (null!= callback) {
					callback.onExpandNode();
				}
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				if (null != callback) {
					callback.onCollapseNode();
				}
			}
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (null!= callback) {
					callback.onClickNode();
				}
			}

		});
	}
	
	public void setNodeCallback(final NodeCallback callback) {
		this.callback  = callback;
	}
	
	public static void demo(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				ObjectTreeViewPanel panel = new ObjectTreeViewPanel(type);
//				panel.setNodeCallback(new NodeCallback() {
//					
//					@Override
//					public void onExpandNode() {
//						System.out.println("ObjectTreeViewPanel:onExpandNode called!");
//					}
//					
//					@Override
//					public void onCollapseNode() {
//						System.out.println("ObjectTreeViewPanel:onCollapseNode called!");
//					}
//					
//					@Override
//					public void onClickNode() {
//						System.out.println("ObjectTreeViewPanel:onClickNode called!");
//					}
//				});
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
	
}
