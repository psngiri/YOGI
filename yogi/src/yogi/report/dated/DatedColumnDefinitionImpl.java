package yogi.report.dated;

import java.util.Comparator;

import yogi.base.io.Formatter;
import yogi.period.interval.Interval;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.group.Group;
import yogi.report.group.GroupEvaluatorAdapter;

public abstract class DatedColumnDefinitionImpl<T, C extends Comparable<? super C>> extends ColumnDefinitionImpl<T, C> implements DatedColumnDefinition<T>{
	private IntervalSplitter intervalSplitter;
	public DatedColumnDefinitionImpl(String name, int width, Formatter<? super C> formatter, String[] heading, IntervalSplitter intervalSplitter, Comparator<C> comparator) {
		super(name, width, new DatedColumnGroupEvaluator<T, C>(name), formatter, heading, comparator);
		this.intervalSplitter = intervalSplitter;
	}
	
	public DatedColumnDefinitionImpl(String name, int width, Formatter<? super C> formatter, String[] heading, IntervalSplitter intervalSplitter) {
		this(name, width, formatter, heading, intervalSplitter, null);
	}
	
	public IntervalSplitter getIntervalSplitter() {
		return intervalSplitter;
	}

	abstract public C getValue(Interval interval);
	
}

class DatedColumnGroupEvaluator<I, O> extends GroupEvaluatorAdapter<I, O>
{
	String keyName;
	
	public DatedColumnGroupEvaluator(String keyName) {
		super();
		this.keyName = keyName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O evaluate(Group<I> group, int indexInGroup) {
		return (O) group.getKeyValue(keyName);
	}

	
}

