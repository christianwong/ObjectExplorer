package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
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

public class ObjectTreeViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTree tree;
	private DefaultMutableTreeNode root;

	private ObjectExplorerPanel parent;
	private NodeCallback callback;

	public ObjectTreeViewPanel(ObjectExplorerPanel parent, Object rootSource) {

		setLayout(new BorderLayout());

		// init components
		this.parent = parent;
		initTree(rootSource);
		initCallbacks();

		// set up the frame
		this.setMinimumSize(new Dimension(500, 600));
		this.setVisible(true);
	}

	private void initTree(Object rootSource) {
		// set root with childs
		root = new DefaultMutableTreeNode(rootSource);
		List<?> children = parent.getHandler().getChildren(root.getUserObject());
		for (Object child : children) {
			addNode(root, child, parent.getHandler().hasChildren(child));
		}
		parent.getHandler().setExpanded(root.getUserObject(), true);

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
					
					processSelectedObjects();
				}
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				if (null != callback) {
					TreePath path = event.getPath();
					Object object = path.getLastPathComponent();
					callback.collapseNode(object);
					
					processSelectedObjects();
				}
			}
		});

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				
			    processSelectedObjects();
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
		DefaultMutableTreeNode nodeChild = new DefaultMutableTreeNode(childSource);
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
	
	public void processSelectedObjects() {
		TreePath[] paths = tree.getSelectionPaths();
	    List<Object> objects = new ArrayList<Object>();
	    for (TreePath path : paths) {
	    	DefaultMutableTreeNode object = (DefaultMutableTreeNode) path.getLastPathComponent();
	    	objects.add(object.getUserObject());
		}
	    
	    parent.getTextViewPanel().updateText(objects);
	}

}
