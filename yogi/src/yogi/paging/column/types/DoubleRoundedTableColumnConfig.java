package yogi.paging.column.types;

import yogi.paging.column.formatter.DoubleRoundedFormatter;


public class DoubleRoundedTableColumnConfig extends DoubleTableColumnConfig {
	
	private static final long serialVersionUID = 5937836976366319999L;
	
	public DoubleRoundedTableColumnConfig(String viewName, int width, boolean readOnly) {
		super(viewName, width, new DoubleRoundedFormatter(), readOnly);
	}
	
	public DoubleRoundedTableColumnConfig(String viewName, int width) {
		this(viewName, width, true);
	}
}
