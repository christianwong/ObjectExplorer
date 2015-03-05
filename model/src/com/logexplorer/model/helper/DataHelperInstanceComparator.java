package com.logexplorer.model.helper;

import java.util.Comparator;

public class DataHelperInstanceComparator implements Comparator<DataHelperInstance> {

	@Override
	public int compare(DataHelperInstance o1, DataHelperInstance o2) {
		return o1.getObjectHash() - o2.getObjectHash();
	}

}
