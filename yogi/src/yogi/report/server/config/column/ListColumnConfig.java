package yogi.report.server.config.column;

import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.function.list.config.ListUnionFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public abstract class ListColumnConfig<R, S> extends ColumnConfig<R, List<S>>{
	
	private static final long serialVersionUID = 408540737574249968L;

	public ListColumnConfig(String columnName, Evaluator<R, List<S>> evaluator, Formatter<List<S>> formatter, Comparator<List<S>> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addFunction(new ListUnionFunctionConfig<S>());
		//this.addFunction(new ListIntersectionFunctionConfig<S>());
	}

}
