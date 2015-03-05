package com.logexplorer.app;

import com.logexplorer.controller.test.ControllerTest;
import com.logexplorer.model.factory.TypeFactory;
import com.logexplorer.model.helper.DataHelper;
import com.logexplorer.model.test.Tester;
import com.logexplorer.model.types.AbstractType;

public class ObjectExplorerLauncher {

	private static final String FILENAME = "GuessClass.bin";

	public static void main(String[] args) {
		
		String filename = FILENAME;
		if (0 == args.length) {
			Tester.createObject(filename);
		} else {
			filename = args[0];
		}
		
		Object object = Tester.run(filename);
		AbstractType type = TypeFactory.getType("bin_object", object);
		
		// for debug only
		@SuppressWarnings("unused")
		DataHelper dp = DataHelper.getInstance();
		
//		String knownType = TypeUtils.describeKnownType(type);
//		System.out.println("###### KNOWN TYPE\n"+knownType);

//		String fullType = TypeUtils.describeFullType(type);
//		System.out.println("###### FULL TYPE\n"+fullType);

		ControllerTest.run(type);
	}

}