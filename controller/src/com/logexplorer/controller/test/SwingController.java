package com.logexplorer.controller.test;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.model.index.TypeIndex;
import com.logexplorer.model.types.AbstractType;
import com.logexplorer.view.handlers.DataHandler;
import com.logexplorer.view.panels.ObjectExplorerPanel;

public class SwingController {

	private DataHandler nodeHandler;
	
	private ObjectExplorerPanel view;
	private JFrame frame;
	
	public SwingController(AbstractType type) {
		initCallbacks();

		// set up view
		view = new ObjectExplorerPanel(type, nodeHandler);
		view.doPostInit();
		
		// set up frame
		frame = new JFrame();
		frame.add(view, BorderLayout.CENTER);
	}

	private void initCallbacks() {
		
		nodeHandler = new DataHandler() {
			
			@Override
			public boolean hasChildren(Object object) {
				return ((AbstractType) object).hasChilds();
			}
			
			@Override
			public List<?> getChildren(Object object) {
				return ((AbstractType) object).getChilds();
			}

			@Override
			public String getObjectName(Object object) {
				return ((AbstractType) object).getName();
			}

			@Override
			public String getObjectType(Object object) {
				return ((AbstractType) object).getType();
			}

			@Override
			public String getObjectID(Object object) {
				return ((AbstractType) object).getID();
			}

			@Override
			public boolean isExpanded(Object object) {
				return ((AbstractType) object).isExpanded();
			}

			@Override
			public String getObjectValue(Object object) {
				return ((AbstractType) object).getValue();
			}

			@Override
			public void setExpanded(Object object, boolean expanded) {
				((AbstractType) object).setExpanded(expanded);
			}

			@Override
			public boolean isHighlighted(Object object) {
				AbstractType type = (AbstractType) object;
				return TypeIndex.getInstance().matches(type.getName(), type.getValue());
			}

			@Override
			public void doSearch(String attribute, String value) {
				// Searching but not using the results
				TypeIndex.getInstance().search(attribute, value);
				
				// re-process tree and text views
				view.getTreeViewPanel().processSelectedObjects();
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
				SwingController controller = new SwingController(type);
				controller.show();
			}
		});
	}

}
