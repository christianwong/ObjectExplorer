package com.logexplorer.model.io;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectReader {

	private static final String BIN_EXT = ".bin";
	private static final String XML_EXT = ".xml";

	public static Object loadObjectFromFile(String filename) {
		Object object = null;
		
		try {
			object = readObject(filename, object);
		} catch (Throwable exception) {
			object = processException(object, exception);
		}
		
		return object;
	}

	private static Object processException(Object object, Throwable exception) {
		if (null == object) {
			object = exception;
		} else {
			Object objectMessage[] = new Object[2];
			objectMessage[0] = exception;
			objectMessage[1] = object;
			
			object = objectMessage;
		}
		return object;
	}

	private static Object readObject(String filename, Object object)
			throws Throwable {
		if (isBinary(filename)) {
			object = readBinaryObject(filename);
		} else if (isXml(filename)) {
			object = readXmlObject(filename);
		} else {
			object = "File "+filename+" not recognized!";
		}
		return object;
	}

	private static boolean isXml(String filename) {
		return filename.endsWith(XML_EXT);
	}

	private static boolean isBinary(String filename) {
		return filename.endsWith(BIN_EXT);
	}

	private static Object readXmlObject(String filename) throws Throwable {
		Object object = null;

		FileInputStream fin = new FileInputStream(filename);
		XMLDecoder decoder = new XMLDecoder(fin);
		object = decoder.readObject();
		decoder.close();

		return object;
	}

	private static Object readBinaryObject(String filename) throws Throwable {
		Object object = null;

		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fin);
		object = ois.readObject();
		ois.close();

		return object;
	}

}
