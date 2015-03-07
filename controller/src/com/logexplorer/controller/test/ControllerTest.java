package com.logexplorer.controller.test;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.model.types.AbstractType;
import com.logexplorer.model.util.TypeUtils;
import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.events.TextViewCallback;
import com.logexplorer.view.handlers.NodeHandler;
import com.logexplorer.view.panels.ObjectExplorerPanel;

public class ControllerTest {

	private NodeHandler nodeHandler;
	private NodeCallback nodeCallback;
	private TextViewCallback textViewCallback;
	private SearchCallback searchCallback;
	
	private ObjectExplorerPanel view;
	private AbstractType type;
	private JFrame frame;
	
	public ControllerTest(AbstractType type) {
		this.type = type;
		
		initCallbacks();

		// set up view
		view = new ObjectExplorerPanel(type, nodeHandler);
		
		// set up callbacks
		view.getTreeViewPanel().setCallback(nodeCallback);
		view.getTextViewPanel().setCallback(textViewCallback);
		view.getSearchBarPanel().setCallback(searchCallback);
		
		// display full object as for now.
		view.getTextViewPanel().setKnownObjectText(TypeUtils.describeKnownType(this.type));
		
		// set up frame
		frame = new JFrame();
		frame.add(view, BorderLayout.CENTER);
}

	private void initCallbacks() {
		
		nodeHandler = new NodeHandler() {
			
			@Override
			public boolean hasChildren(Object object) {
				return ((AbstractType) object).hasChilds();
			}
			
			@Override
			public List<Object> getChildren(Object object) {
				return ((AbstractType) object).getChilds();
			}
		};
		
		nodeCallback = new NodeCallback() {
			
			@Override
			public void expandNode(Object object) {
				
//				// get childs from model
//				AbstractType selectedType = DataHelper.getInstance().getStoredType(code);
//				List<AbstractType> typeChilds = selectedType.getChilds();
//				
//				// copy childs from model to view
//				MutableTreeNode node = (MutableTreeNode)path.getLastPathComponent();
//				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//				for (AbstractType type : typeChilds) {
//					String nodeDescription = type.getNameWithID();
//					DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(nodeDescription);
//					model.insertNodeInto(newChild, node, node.getChildCount());
//					if (type.hasChilds()) {
//						newChild.add(new DefaultMutableTreeNode(DUMMY_LEAF));
//					}
//				}
//				
//				// remove dummy leaf
//				MutableTreeNode dummyLeaf = (MutableTreeNode) node.getChildAt(0);
//				model.removeNodeFromParent(dummyLeaf);

				clickNode(object);
			}
			
			@Override
			public void collapseNode(Object object) {
				
//				// remove childs from model
//				AbstractType selectedType = DataHelper.getInstance().getStoredType(code);
//				selectedType.resetChilds();
//				
//				// remvoe childs from view
//				MutableTreeNode node = (MutableTreeNode)path.getLastPathComponent();
//				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//				for (int idx=node.getChildCount()-1; idx >= 0; idx--) {
//					MutableTreeNode dummyLeaf = (MutableTreeNode) node.getChildAt(idx);
//					model.removeNodeFromParent(dummyLeaf);
//				}
//				
//				// add dummy leaf to view
//				DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(DUMMY_LEAF);
//				model.insertNodeInto(newChild, node, node.getChildCount());
				
				clickNode(object);
			}
			
			@Override
			public void clickNode(Object object) {
//			    String description = "";
//			    TreePath[] paths = tree.getSelectionPaths();
//			    
//			    if (1 != paths.length) {
//			    	description += "Displaying " + paths.length + " selected objects.\n\n";
//			    }
//			    
//			    for (TreePath path : paths) {
//			    	int pathCode = ViewUtils.getCodeFromName(path.getLastPathComponent().toString());
//
//					AbstractType selectedType = DataHelper.getInstance().getStoredType(pathCode);
//					description += TypeUtils.describeKnownType(selectedType) + "\n\n";
//				}
//			    view.getTextViewPanel().setKnownObjectText(description);
			}
		};
		
		textViewCallback = new TextViewCallback() {
			
			@Override
			public void onViewFullObject() {
				System.out.println("onViewFullObject called");
			}
		};
		
		searchCallback = new SearchCallback() {
			
			@Override
			public void onSearch(String attribute, String value) {
				System.out.println("setSearchCallback('"+attribute+"', '"+value+"') called");
			}
		};
	}
	
	public void show() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Object Explorer - by Christian Wong");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void run(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ControllerTest controller = new ControllerTest(type);
				controller.show();
			}
		});
	}

}
