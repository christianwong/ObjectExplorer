package com.logexplorer.model.test;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class GuessClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int aInteger;
	private double aDouble;
	private char aChar;
	private float aFloat;
	
	private String aString;
	private int aIntArray[];
	private SimpleClass aSimpleClass[];
	private List<?> aList[] = new List<?>[0];
	private List<?> aArrayList;
	private Map<?, ?> aMap;
	private Map<?, ?> aComplexMap;
	
	@SuppressWarnings("unused")
	private SimpleClass aSimplClass = new SimpleClass();
	
	public int getaInteger() {
		return aInteger;
	}

	public void setaInteger(int aInteger) {
		this.aInteger = aInteger;
	}

	public double getaDouble() {
		return aDouble;
	}

	public void setaDouble(double aDouble) {
		this.aDouble = aDouble;
	}

	public char getaChar() {
		return aChar;
	}

	public void setaChar(char aChar) {
		this.aChar = aChar;
	}

	public float getaFloat() {
		return aFloat;
	}

	public void setaFloat(float aFloat) {
		this.aFloat = aFloat;
	}

	public String getaString() {
		return aString;
	}

	public void setaString(String aString) {
		this.aString = aString;
	}

	public int[] getaIntArray() {
		return aIntArray;
	}

	public void setaIntArray(int[] aIntArray) {
		this.aIntArray = aIntArray;
	}

	public List<?> getaArrayList() {
		return aArrayList;
	}

	public void setaArrayList(List<?> aArrayList) {
		this.aArrayList = aArrayList;
	}

	public Map<?, ?> getaMap() {
		return aMap;
	}

	public void setaMap(Map<?, ?> aMap) {
		this.aMap = aMap;
	}

	public Map<?, ?> getaComplexMap() {
		return aComplexMap;
	}

	public void setaComplexMap(Map<?, ?> aComplexMap) {
		this.aComplexMap = aComplexMap;
	}

	public SimpleClass[] getaSimpleClass() {
		return aSimpleClass;
	}

	public void setaSimpleClass(SimpleClass aSimpleClass[]) {
		this.aSimpleClass = aSimpleClass;
	}

	public List<?>[] getaList() {
		return aList;
	}

	public void setaList(List<?> aList[]) {
		this.aList = aList;
	}

}
