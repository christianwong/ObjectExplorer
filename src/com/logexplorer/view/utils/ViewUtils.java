package com.logexplorer.view.utils;

import javax.swing.JLabel;

public final class ViewUtils {
	
	public static void setDefaultValue(JLabel label, String text) {
		if (label.getText().trim().isEmpty()) {
			label.setText(text);
		}
	}

}
