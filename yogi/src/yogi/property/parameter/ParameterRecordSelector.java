package yogi.property.parameter;

import yogi.base.Selector;

public class ParameterRecordSelector implements Selector<String> {

	public boolean select(String record) {
		if(record.trim().length() == 0) return false;
		return true;
	}

}
