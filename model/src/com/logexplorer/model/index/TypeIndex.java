package com.logexplorer.model.index;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.logexplorer.model.types.AbstractType;

/**
 * Parent class for field (name) and value indexes.
 * @author christian wong
 */
public class TypeIndex {

	// Singleton instance
	private static TypeIndex instance = null;
	
	/**
	 * Singleton method to retrieve instance.
	 * @return
	 */
	public static TypeIndex getInstance() {
		if (null == instance) {
			instance = new TypeIndex();
		}
		return instance;
	}
	
	protected List<TypeIndexInstance> list;
	
	private TypeIndex() {
		list = new ArrayList<TypeIndexInstance>();
	}

	public void add(String typeName, String typeValue, AbstractType type) {
		TypeIndexInstance item = new TypeIndexInstance(typeName, typeValue, type);
		list.add(item);
	};
	
	@Override
	public String toString() {
		String str = "TypeIndex ("+list.size()+")\n";
		for (TypeIndexInstance item : list) {
			str += item.toString()+"\n";
		}
		return str;
	}
	
	public List<AbstractType> search(String name, String value) {
		List<AbstractType> result = new ArrayList<AbstractType>();
		
		Pattern pName = Pattern.compile(name);
		Pattern pValue = Pattern.compile(value);

		for (TypeIndexInstance item : list) {
			if (pName.matcher(item.getName()).matches() && pValue.matcher(item.getValue()).matches()) {
				result.add(item.getType());
			}
		}
		
		return result;
	}
	
}
