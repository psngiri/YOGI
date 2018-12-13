package yogi.report.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.report.function.Function;
import yogi.report.group.GroupEvaluator;
import yogi.report.group.GroupEvaluatorWraper;

public class ColumnDefinitionImpl<T, C extends Comparable<? super C>> extends ColumnDefinitionBaseImpl<T, C> {
	public ColumnDefinitionImpl(String name, int width, Evaluator<T, C> evaluator, Formatter<? super C> formatter, Function<C> function, String[] heading, Comparator<C> comparator) {
		super(name, width, formatter, heading);
		setColumnWorker(new ColumnWorkerImpl<T, C>(this, GroupEvaluatorWraper.getGroupEvaluator(evaluator), function, comparator));
	}
	
	public ColumnDefinitionImpl(String name, int width, Evaluator<T, C> evaluator, Formatter<? super C> formatter, Function<C> function, String[] heading) {
		super(name, width, formatter, heading);
		setColumnWorker(new ColumnWorkerImpl<T, C>(this, GroupEvaluatorWraper.getGroupEvaluator(evaluator), function, null));
	}
	
	protected ColumnDefinitionImpl(String name, int width, GroupEvaluator<T, C> groupEvaluator, Formatter<? super C> formatter, String[] heading, Comparator<C> comparator) {
		super(name, width, formatter, heading);
		setColumnWorker(new ColumnWorkerImpl<T, C>(this, groupEvaluator, null, comparator));
	}

	public ColumnDefinitionImpl(String name, int width, Formatter<? super C> formatter, Function<C> function, String[] heading, Comparator<C> comparator, GroupEvaluator<T, C> groupEvaluator) {
		super(name, width, formatter, heading);
		setColumnWorker(new ColumnWorkerImpl<T, C>(this, groupEvaluator, function, comparator));
	}
}

