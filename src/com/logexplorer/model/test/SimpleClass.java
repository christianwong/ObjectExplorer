package com.logexplorer.model.test;
import java.io.Serializable;


public class SimpleClass implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int abc = 123;
	
	public String toString() {
		return "abc=123";
		
	}
}
