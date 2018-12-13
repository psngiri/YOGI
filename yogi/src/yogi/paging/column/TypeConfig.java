package yogi.paging.column;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5976093836408821129L;
	
	private Map<String,String> items = new LinkedHashMap<String, String>();
	
	public TypeConfig() {
		super();
	}
	
	public void addItem(String name, String value) {
		items.put(name, value);
	}	
}