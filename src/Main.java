import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logexplorer.factory.TypeFactory;
import com.logexplorer.types.AbstractType;
import com.logexplorer.util.TypeUtils;

public class Main {

	public static void main(String[] args) {
		String filename = "GuessClass.bin";
		createObject(filename);
		Object object = readBinaryObject(filename);
		analyzeObject(object);
		System.out.println("finished.");
	}

	private static void analyzeObject(Object object) {
		
		Class<? extends Object> objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		System.out.println("object has "+fields.length+" declared fields.");
		
		processFields(object, fields);
	}

	private static void processFields(Object object, Field[] fields) {
		for (Field field : fields) {
			
			System.out.println("#Field is type: "+field.getGenericType());
			
			boolean accessible = TypeUtils.enableAccessible(field);
			
			Object value = TypeUtils.getFieldValue(object, field);
			AbstractType type = TypeFactory.getType(value);
			
			TypeUtils.resetAccessible(field, accessible);
		}
	}

	private static Object readBinaryObject(String filename) {
		Object someObject = null;

		try {
			FileInputStream fin = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fin);
			someObject = ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return someObject;
	}

	private static void createObject(String filename) {
		GuessClass gc = new GuessClass();
		gc.setaInteger(123);
		gc.setaDouble(543.756);
		gc.setaChar('a');
		gc.setaFloat(234f);
		gc.setaString("hello, world!");
		int iarray[] = new int[3];
		iarray[0] = 1;
		iarray[1] = 2;
		iarray[2] = 3;
		gc.setaIntArray(iarray);
		List<String> aArrayList = new ArrayList<String>();
		aArrayList.add("first");
		aArrayList.add("seccond");
		gc.setaArrayList(aArrayList);
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("one", new Integer(1));
		aMap.put("two", new Integer(2));
		aMap.put("three", new Integer(3));
		gc.setaMap(aMap);

		// serialize the Queue
		System.out.println("serializing theData");
		try {
			FileOutputStream fout = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(gc);
			oos.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
