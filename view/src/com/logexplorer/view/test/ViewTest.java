package com.logexplorer.view.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import com.logexplorer.view.events.NodeCallback;
import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.events.TextViewCallback;
import com.logexplorer.view.panels.ObjectExplorerPanel;

public class ViewTest {
	
	private static NodeCallback nodeCallback;
	private static TextViewCallback textViewCallback;
	private static SearchCallback searchCallback;
	
	static {
		nodeCallback = new NodeCallback() {
			
			@Override
			public void onExpandNode(JTree tree, TreePath path, int code) {
				System.out.println("onExpandNode called: "+code);
			}
			
			@Override
			public void onCollapseNode(JTree tree, TreePath path, int code) {
				System.out.println("onCollapseNode called: "+code);
			}
			
			@Override
			public void onClickNode(JTree tree, int code) {
				System.out.println("onClickNode called: "+code);
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
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				ObjectExplorerPanel panel = new ObjectExplorerPanel();
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
				
				panel.getTreeViewPanel().setNodeCallback(nodeCallback);
				panel.getTextViewPanel().setTextViewCallback(textViewCallback);
				panel.getSearchBarPanel().setSearchCallback(searchCallback);
			}
		});
	}

}
