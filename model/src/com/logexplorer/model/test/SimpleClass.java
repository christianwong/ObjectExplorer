package com.logexplorer.model.test;
import java.io.Serializable;


public class SimpleClass extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int abc = 123;
	protected String str = "str str str";
	
	public String toString() {
		return "SimpleClass";
	}
}
