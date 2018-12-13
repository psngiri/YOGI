package yogi.report.server;

import yogi.base.selectors.BaseAndSelector;
import yogi.report.column.ColumnSelector;

public class ColumnAndSelector<T> extends  BaseAndSelector<T, ColumnSelector<T, Object>>{
	public ColumnAndSelector(ColumnSelector<T, Object> ... selectors) {
		super(selectors);
	}

	public ColumnAndSelector() {
		super();
	}
	
}