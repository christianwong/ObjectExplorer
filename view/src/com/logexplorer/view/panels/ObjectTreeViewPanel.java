package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import com.logexplorer.view.consts.ViewConsts;
import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.handlers.NodeHandler;

public class ObjectTreeViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTree tree;
	private DefaultMutableTreeNode root;

	private NodeCallback callback;
	private NodeHandler handler;

	public ObjectTreeViewPanel(Object rootSource, NodeHandler handler) {

		setLayout(new BorderLayout());

		// init components
		this.handler = handler;
		initTree(rootSource);
		initCallbacks();

		// set up the frame
		this.setMinimumSize(new Dimension(500, 600));
		this.setVisible(true);
	}

	private void initTree(Object rootSource) {
		// set root with childs
		root = new DefaultMutableTreeNode(rootSource);
		List<Object> children = handler.getChildren(root.getUserObject());
		for (Object child : children) {
			addNode(root, child, handler.hasChildren(child));
		}

		// set up the tree
		tree = new JTree(root);
		tree.setShowsRootHandles(true);
		tree.setSelectionPath(new TreePath(root.getPath()));
		
		// Hide icons from tree
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);
		renderer.setDisabledIcon(null);
		tree.setCellRenderer(renderer);
		
		add(tree, BorderLayout.CENTER);
		add(new JScrollPane(tree));
	}

	private void initCallbacks() {
		callback = null;
		tree.addTreeExpansionListener(new TreeExpansionListener() {

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				
				if (null != callback) {
					TreePath path = event.getPath();
					Object object = path.getLastPathComponent();
					
					callback.expandNode(object);
//					MutableTreeNode node = (MutableTreeNode)path.getLastPathComponent();
//					
//					if (node.getChildCount() >= 0) {
//						tree.expandPath(path);
//					}
				}
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				if (null != callback) {
					TreePath path = event.getPath();
					Object object = path.getLastPathComponent();
					callback.collapseNode(object);
				}
			}
		});

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				if (null != callback) {
					TreePath path = event.getPath();
					Object object = path.getLastPathComponent();
					callback.clickNode(object);
				}
			}

		});
	}

	public void setCallback(final NodeCallback callback) {
		this.callback = callback;
	}
	
	public Object getRoot() {
		return this.root;
	}
	
	public Object addNode(Object parent, Object childSource, boolean hasChilds) {
		DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) parent;
		DefaultMutableTreeNode nodeChild = new DefaultMutableTreeNode(childSource.toString());
		if (hasChilds) {
			nodeChild.add(new DefaultMutableTreeNode(ViewConsts.DUMMY_LEAF));
		}
		nodeParent.add(nodeChild);
		return nodeChild;
	}

	public Object addDummyNode(Object parent) {
		DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) parent;
		DefaultMutableTreeNode nodeChild = new DefaultMutableTreeNode(ViewConsts.DUMMY_LEAF);
		nodeParent.add(nodeChild);
		return nodeChild;
	}

}
