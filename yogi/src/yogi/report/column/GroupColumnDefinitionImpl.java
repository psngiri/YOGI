package yogi.report.column;

import java.util.Comparator;

import yogi.base.io.Formatter;
import yogi.report.function.Function;
import yogi.report.group.GroupEvaluator;

public class GroupColumnDefinitionImpl<T, C extends Comparable<? super C>> extends ColumnDefinitionBaseImpl<T, C> {
	public GroupColumnDefinitionImpl(String name, int width, GroupEvaluator<T, C> groupEvaluator, Formatter<? super C> formatter, Function<C> funtcion, String[] heading, Comparator<C> comparator) {
		super(name, width, formatter, heading);
		setColumnWorker(new ColumnWorkerImpl<T, C>(this, groupEvaluator, funtcion, comparator));
	}
	
	public GroupColumnDefinitionImpl(String name, int width, GroupEvaluator<T, C> groupEvaluator, Formatter<? super C> formatter, Function<C> funtcion, String[] heading) {
		super(name, width, formatter, heading);
		setColumnWorker(new ColumnWorkerImpl<T, C>(this, groupEvaluator, funtcion, null));
	}
	
}
