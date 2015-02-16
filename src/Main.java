import java.lang.reflect.Field;

import com.logexplorer.factory.TypeFactory;
import com.logexplorer.test.Tester;
import com.logexplorer.types.AbstractType;
import com.logexplorer.util.TypeUtils;

public class Main {

	public static void main(String[] args) {
		String filename = "GuessClass.bin";
		Object object = Tester.run(filename);
		analyzeObject(object);
	}

	private static void analyzeObject(Object object) {
		
		System.out.println("###### PROCESSING OBJECT");
		AbstractType type = TypeFactory.getType(object);
		
		System.out.println("###### PROCESSING FIELDS");
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

}
