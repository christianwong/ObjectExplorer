package com.logexplorer.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

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

import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.utils.ViewUtils;

public class ObjectTreeViewPanel extends JPanel {

//	private static final String DUMMY_LEAF = "dummy";
	private static final long serialVersionUID = 1L;

	private JTree tree;
	private DefaultMutableTreeNode root;

	private NodeCallback callback;

	public ObjectTreeViewPanel() {

		setLayout(new BorderLayout());

		// init components
		initTree();

		// set up the frame
		this.setMinimumSize(new Dimension(500, 600));
		this.setVisible(true);
	}

	private void initTree() {
		root = new DefaultMutableTreeNode("?????");

		// add childs to tree
//		for (AbstractType child : type.getChilds()) {
//			DefaultMutableTreeNode dummyChild = new DefaultMutableTreeNode(child.getNameWithID());
//			if (child.hasChilds()) {
//				dummyChild.add(new DefaultMutableTreeNode(DUMMY_LEAF));
//			}
//			root.add(dummyChild);
//		}
		
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
		
		initCallbacks();
		
		add(tree, BorderLayout.CENTER);
		add(new JScrollPane(tree));
	}

	private void initCallbacks() {
		callback = null;
		tree.addTreeExpansionListener(new TreeExpansionListener() {

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				TreePath path = event.getPath();
				int code = ViewUtils.getCodeFromName(path.getLastPathComponent().toString());
				if (null != callback) {
					callback.onExpandNode(tree, path, code);
				}
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				TreePath path = event.getPath();
				int code = ViewUtils.getCodeFromName(path.getLastPathComponent().toString());
				if (null != callback) {
					callback.onCollapseNode(tree, path, code);
				}
			}
		});

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				TreePath path = event.getPath();
				int code = ViewUtils.getCodeFromName(path.getLastPathComponent().toString());
				if (null != callback) {
					callback.onClickNode(tree, code);
				}
				
				// TODO mark current node as selected in tree
			}

		});
	}

	public void setCallback(final NodeCallback callback) {
		this.callback = callback;
	}

}
