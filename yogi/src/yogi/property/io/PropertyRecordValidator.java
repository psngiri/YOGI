package yogi.property.io;

import yogi.base.Selector;

public class PropertyRecordValidator implements Selector<String>{

	public boolean select(String propertyRecord) {
		if(propertyRecord.trim().length() == 0 || propertyRecord.charAt(0)=='#') return false;
		return true;
	}

}
