package com.logexplorer.controller.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.model.types.AbstractType;
import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.events.TextViewCallback;
import com.logexplorer.view.panels.ObjectExplorerPanel;

public class ControllerTest {

	private static NodeCallback nodeCallback;
	private static TextViewCallback textViewCallback;
	private static SearchCallback searchCallback;
	
	static {
		nodeCallback = new NodeCallback() {
			
			@Override
			public void onExpandNode() {
				System.out.println("onExpandNode called");
			}
			
			@Override
			public void onCollapseNode() {
				System.out.println("onCollapseNode called");
			}
			
			@Override
			public void onClickNode() {
				System.out.println("onClickNode called");
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
	
	public static void run(final AbstractType type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				ObjectExplorerPanel panel = new ObjectExplorerPanel(type);
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
				
				panel.setNodeCallback(nodeCallback);
				panel.setTextViewCallback(textViewCallback);
				panel.setSearchCallback(searchCallback);
			}
		});
	}

}
