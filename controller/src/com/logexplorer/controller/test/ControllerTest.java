package com.logexplorer.controller.test;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.model.index.TypeIndex;
import com.logexplorer.model.types.AbstractType;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.handlers.NodeHandler;
import com.logexplorer.view.panels.ObjectExplorerPanel;

public class ControllerTest {

	private NodeHandler nodeHandler;
	private SearchCallback searchCallback;
	
	private ObjectExplorerPanel view;
	private JFrame frame;
	
	public ControllerTest(AbstractType type) {
		initCallbacks();

		// set up view
		view = new ObjectExplorerPanel(type, nodeHandler);
		
		// set up callbacks
		view.getSearchBarPanel().setCallback(searchCallback);
		
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
		};
		
		searchCallback = new SearchCallback() {
			
			@Override
			public void onSearch(String attribute, String value) {
				
				List<AbstractType> search = TypeIndex.getInstance().search(attribute, value);
				System.out.println("Found "+search.size()+" items:");
				for (AbstractType result : search) {
					System.out.println(result.getID()+" -> name='"+result.getName()+"' value='"+result.getValue()+"'");
				}
				
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
				ControllerTest controller = new ControllerTest(type);
				controller.show();
			}
		});
	}

}
