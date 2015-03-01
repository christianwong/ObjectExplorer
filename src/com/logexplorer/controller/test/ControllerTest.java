package com.logexplorer.controller.test;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.model.helper.DataHelper;
import com.logexplorer.model.types.AbstractType;
import com.logexplorer.model.util.TypeUtils;
import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.events.TextViewCallback;
import com.logexplorer.view.panels.ObjectExplorerPanel;

public class ControllerTest {

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
		view = new ObjectExplorerPanel(this.type);
		view.setNodeCallback(nodeCallback);
		view.setTextViewCallback(textViewCallback);
		
		// set up callbacks
		view.setNodeCallback(nodeCallback);
		view.setTextViewCallback(textViewCallback);
		view.setSearchCallback(searchCallback);
		
		// display full object as for now.
		view.setKnownObjectText(TypeUtils.describeKnownType(this.type));
		
		// set up frame
		frame = new JFrame();
		frame.add(view, BorderLayout.CENTER);
}

	private void initCallbacks() {
		nodeCallback = new NodeCallback() {
			
			@Override
			public void onExpandNode(int code) {
				System.out.println("onExpandNode called: "+code);
				
				AbstractType selectedType = DataHelper.getInstance().getStoredType(code);
				List<AbstractType> childs = selectedType.getChilds();
				
				// TODO 
				// add childs to expanded node
				
				onClickNode(code);
			}
			
			@Override
			public void onCollapseNode(int code) {
				System.out.println("onCollapseNode called: "+code);
				
				AbstractType selectedType = DataHelper.getInstance().getStoredType(code);
				selectedType.resetChilds();
				
				// TODO 
				// remove childs from collapsed node
				
				onClickNode(code);
			}
			
			@Override
			public void onClickNode(int code) {
				AbstractType selectedType = DataHelper.getInstance().getStoredType(code);
				String description = TypeUtils.describeKnownType(selectedType);
				view.setKnownObjectText(description);
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
		frame.setTitle("Object Explorer");
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
