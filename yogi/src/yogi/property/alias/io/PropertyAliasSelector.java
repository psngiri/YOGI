package yogi.property.alias.io;

import yogi.base.Selector;

public class PropertyAliasSelector implements Selector<String> {

	public boolean select(String item) {		
		return !(item.trim().length()==0);
	}


}
