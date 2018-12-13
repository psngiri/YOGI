package yogi.report.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.report.function.Function;
import yogi.report.group.GroupEvaluator;
import yogi.report.group.GroupEvaluatorWraper;
import yogi.report.split.Splitter;

public class ColumnDefinitionBaseImpl<T, C> implements ColumnDefinition<T> {
	private String[] heading;
	private String name;
	private int width;
	private ColumnWorkerBaseImpl<T, C> columnWorker;
	Class<? extends C> type;
	private Formatter<? super C> formatter;
	private String nullValue = "";
	private Splitter splitter;

	protected ColumnDefinitionBaseImpl(String name, int width, GroupEvaluator<T, C> groupEvaluator, Formatter<? super C> formatter, Function<C> funtcion, String[] heading, Comparator<C> comparator) {
		this(name, width, formatter, heading);
		columnWorker = new ColumnWorkerBaseImpl<T, C>(this, groupEvaluator, funtcion, comparator);
	}

	public ColumnDefinitionBaseImpl(String name, int width, Evaluator<T, C> evaluator, Formatter<? super C> formatter, Function<C> funtcion, String[] heading, Comparator<C> comparator) {
		this(name, width, formatter, heading);
		columnWorker = new ColumnWorkerBaseImpl<T, C>(this, GroupEvaluatorWraper.getGroupEvaluator(evaluator), funtcion, comparator);
	}

	protected ColumnDefinitionBaseImpl(String name, int width, Formatter<? super C> formatter, String[] heading, Comparator<C> comparator) {
		this(name, width, formatter, heading);
		columnWorker = new ColumnWorkerBaseImpl<T, C>(this, null, null, comparator);
	}

	protected ColumnDefinitionBaseImpl(String name, int width, Formatter<? super C> formatter, String[] heading) {
		this.name = name;
		this.width = width;
		this.heading = heading;
		this.formatter = formatter;
	}

	public ColumnWorkerBaseImpl<T, C> getColumnWorker() {
		return columnWorker;
	}

	public String[] getHeading() {
		if(heading != null) return heading;
		heading = new String[1];
		heading[0] = name;
		return heading;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public void setColumnWorker(ColumnWorkerBaseImpl<T, C> columnWorker) {
		this.columnWorker = columnWorker;
	}

	public Class<?> getType() {
		return type;
	}

	public ColumnDefinitionBaseImpl<T, C> setType(Class<? extends C> type) {
		this.type = type;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public String format(Object object) {
		if(object == null) return nullValue;
		if(formatter == null)
		{
			return object.toString();
		}
		String format = formatter.format((C) object);
		if(format == null) return nullValue;
		return format;
	}

	public int getIndex() {
		return this.getColumnWorker().getColumnGroupWorker().getIndex();
	}

	public boolean isKey() {
		return this.getColumnWorker().getColumnGroupWorker().isKey();
	}

	public Formatter<? super C> getFormatter() {
		return formatter;
	}

	@Override
	public Splitter getSplitter() {
		return splitter;
	}

	public void setSplitter(Splitter splitter) {
		this.splitter = splitter;
	}

}
