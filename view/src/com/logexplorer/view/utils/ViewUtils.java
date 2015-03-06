package com.logexplorer.view.utils;

import javax.swing.JLabel;

public final class ViewUtils {
	
	public static void setDefaultValue(JLabel label, String text) {
		if (label.getText().trim().isEmpty()) {
			label.setText(text);
		}
	}

	public static int getCodeFromName(String name) {
		String code=name.substring(name.lastIndexOf("=")+1, name.lastIndexOf(")"));
		return Integer.parseInt(code);
	}
	
}
