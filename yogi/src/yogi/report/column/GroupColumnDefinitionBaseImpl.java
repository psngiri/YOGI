package yogi.report.column;

import java.util.Comparator;

import yogi.base.io.Formatter;
import yogi.report.function.Function;
import yogi.report.group.GroupEvaluator;

public class GroupColumnDefinitionBaseImpl<T, C> extends ColumnDefinitionBaseImpl<T, C> {

	public GroupColumnDefinitionBaseImpl(String name, int width, GroupEvaluator<T, C> groupEvaluator, Formatter<? super C> formatter, Function<C> funtcion, String[] heading, Comparator<C> comparator) {
		super(name, width, groupEvaluator, formatter, funtcion, heading, comparator);
	}
	
}
