import com.logexplorer.controller.test.ControllerTest;
import com.logexplorer.model.factory.TypeFactory;
import com.logexplorer.model.test.Tester;
import com.logexplorer.model.types.AbstractType;
import com.logexplorer.model.util.TypeUtils;

public class Main {

	private static final String FILENAME = "GuessClass.bin";

	public static void main(String[] args) {
		
		Object object = Tester.run(FILENAME);
		AbstractType type = TypeFactory.getType("bin_object", object);
		
		// expand root type
		type.getChilds();
		
		String knownType = TypeUtils.describeKnownType(type);
		System.out.println("###### KNOWN TYPE\n"+knownType);
		
		String fullType = TypeUtils.describeFullType(type);
		System.out.println("###### FULL TYPE\n"+fullType);

//		TreeDemo.run();
//		ViewTest.runTreeDemo(type);
//		ViewTest.runTextDemo();
//		ViewTest.runSearchDemo();
//		ViewTest.run(type);
		
		ControllerTest.run(type);
	}

}
