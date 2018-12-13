package yogi.report.server;

import yogi.base.selectors.BaseOrSelector;
import yogi.report.column.ColumnSelector;

public class ColumnOrSelector<T> extends  BaseOrSelector<T, ColumnSelector<T, Object>>{
	public ColumnOrSelector(ColumnSelector<T, Object> ... selectors) {
		super(selectors);
	}

	public ColumnOrSelector() {
		super();
	}
	
}