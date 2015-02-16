import com.logexplorer.factory.TypeFactory;
import com.logexplorer.test.Tester;

public class Main {

	private static final String FILENAME = "GuessClass.bin";

	public static void main(String[] args) {
		
		Object object = Tester.run(FILENAME);
		TypeFactory.getType("OBJECT", object);
	}

}
