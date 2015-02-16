import com.logexplorer.factory.TypeFactory;
import com.logexplorer.test.Tester;
import com.logexplorer.types.AbstractType;
import com.logexplorer.util.TypeUtils;

public class Main {

	private static final String FILENAME = "GuessClass.bin";

	public static void main(String[] args) {
		
		Object object = Tester.run(FILENAME);
		AbstractType type = TypeFactory.getType("bin_object", object);
		TypeUtils.print(type);
	}

}
