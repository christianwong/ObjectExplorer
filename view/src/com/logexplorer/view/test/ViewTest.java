package com.logexplorer.view.test;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logexplorer.view.events.SearchCallback;
import com.logexplorer.view.handlers.NodeHandler;
import com.logexplorer.view.panels.ObjectExplorerPanel;
import com.logexplorer.view.panels.ObjectTreeViewPanel;
import com.logexplorer.view.utils.ViewUtils;

public class ViewTest {
	
	private static SearchCallback searchCallback;
	
	static {
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
				ObjectExplorerPanel panel = new ObjectExplorerPanel(ViewUtils.encodeNodeName("demo", 0), 
						new NodeHandler() {
							
							@Override
							public boolean hasChildren(Object object) {
								return false;
							}
							
							@Override
							public List<Object> getChildren(Object object) {
								return new ArrayList<Object>();
							}

							@Override
							public String getObjectName(Object object) {
								return "";
							}

							@Override
							public String getObjectType(Object object) {
								return "";
							}

							@Override
							public String getObjectID(Object object) {
								return "";
							}

							@Override
							public boolean isExpanded(Object object) {
								return false;
							}

							@Override
							public String getObjectValue(Object object) {
								return "";
							}

							@Override
							public void setExpanded(Object object,
									boolean expanded) {
								;
							}

							@Override
							public boolean isHighlighted(Object object) {
								return false;
							}
						});
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Panel Demo");
				frame.pack();
				frame.setVisible(true);
				
				panel.getSearchBarPanel().setCallback(searchCallback);
				
				ObjectTreeViewPanel treeView = panel.getTreeViewPanel();
				Object root = treeView.getRoot();
				for (int idx=1; idx<7; idx++) {
					treeView.addNode(root, ViewUtils.encodeNodeName("child", idx), idx%4==1);
				}
			}
		});
	}

}
