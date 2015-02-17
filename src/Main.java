import com.logexplorer.model.factory.TypeFactory;
import com.logexplorer.model.test.Tester;
import com.logexplorer.model.types.AbstractType;
import com.logexplorer.model.util.TypeUtils;
import com.logexplorer.view.panels.ObjectTextViewPanel;

public class Main {

	private static final String FILENAME = "GuessClass.bin";

	public static void main(String[] args) {
		
		Object object = Tester.run(FILENAME);
		AbstractType type = TypeFactory.getType("bin_object", object);
		TypeUtils.print(type);

//		TreeDemo.run();
//		ObjectTreeViewPanel.demo(type);
		ObjectTextViewPanel.demo();
	}

}
