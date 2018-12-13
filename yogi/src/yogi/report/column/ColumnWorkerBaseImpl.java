package yogi.report.column;

import java.util.Comparator;

import yogi.report.function.Function;
import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;

class ColumnWorkerBaseImpl<T, C> implements ColumnWorker<T>{
	private GroupEvaluator<T, C> groupEvaluator;
	private Comparator<C> comparator;
	private ColumnGroupWorkerImpl<T, C> columnGroupWorker;

	public ColumnWorkerBaseImpl(ColumnDefinitionBaseImpl<T, C> columnDefinition, GroupEvaluator<T, C> groupEvaluator, Function<C> function, Comparator<C> comparator) {
		setColumnGroupWorker(new ColumnGroupWorkerImpl<T, C>(columnDefinition, function, this));
		this.groupEvaluator = groupEvaluator;
		this.comparator = comparator;
	}


	C getValue(T row)
	{
		if(groupEvaluator == null) return null;
		C evaluate = groupEvaluator.evaluate(row);
		return evaluate;
	}
	
	public C getValue(Group<T> group, int indexInGroup)
	{
		if(groupEvaluator == null) return null;
		C evaluate = groupEvaluator.evaluate(group, indexInGroup);
		return evaluate;
	}
	
	public int compare(T row1, T row2) {
		if(groupEvaluator == null) return 0;
		C column1 = getValue(row1);
		C column2 = getValue(row2);
		if(comparator == null) throw new RuntimeException("Comparator needs to be set.");
		return comparator.compare(column1, column2);
	}

	public Object getGroupValue(Group<T> group) {
		return columnGroupWorker.getGroupValue(group);
	}

	public ColumnGroupWorkerImpl<T, C> getColumnGroupWorker() {
		return columnGroupWorker;
	}

	public void setColumnGroupWorker(ColumnGroupWorkerImpl<T, C> columnGroupWorker) {
		this.columnGroupWorker = columnGroupWorker;
	}

	public Comparator<C> getComparator() {
		return comparator;
	}

	protected GroupEvaluator<T, C> getGroupEvaluator() {
		return groupEvaluator;
	}


	@SuppressWarnings("unchecked")
	public int compareGroup(Group<T> group1, Group<T> group2) {
		
		if(comparator == null) throw new RuntimeException("Comparator needs to be set.");
		int columnIndex = this.getColumnGroupWorker().getIndex();
		C column1 = (C) group1.getValues()[columnIndex ];
		C column2 = (C) group2.getValues()[columnIndex];
		return comparator.compare(column1, column2);
	}


	public int getIndex() {
		return this.getColumnGroupWorker().getIndex();
	}


	public boolean isKey() {
		return this.getColumnGroupWorker().isKey();
	}
}
