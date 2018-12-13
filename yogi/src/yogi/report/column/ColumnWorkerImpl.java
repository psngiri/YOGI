package yogi.report.column;

import java.util.Comparator;

import yogi.report.function.Function;
import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;

class ColumnWorkerImpl<T, C extends Comparable<? super C>> extends ColumnWorkerBaseImpl<T, C>{

	public ColumnWorkerImpl(ColumnDefinitionBaseImpl<T, C> columnDefinition, GroupEvaluator<T, C> groupEvaluator, Function<C> function, Comparator<C> comparator) {
		super(columnDefinition, groupEvaluator, function, comparator);
	}

	public int compare(T row1, T row2) {
		if(getGroupEvaluator() == null) return 0;
		if(this.getComparator() != null ) return super.compare(row1, row2);
		C column1 = getValue(row1);
		C column2 = getValue(row2);
		return compare(column1, column2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareGroup(Group<T> group1, Group<T> group2) {
		if(this.getComparator() != null ) return super.compareGroup(group1, group2);
		int columnIndex = this.getColumnGroupWorker().getIndex();
		C column1 = (C) group1.getValues()[columnIndex ];
		C column2 = (C) group2.getValues()[columnIndex];
		return compare(column1, column2);
	}

	private int compare(C column1, C column2) {
		if(column1 == null)
		{
			if(column2 == null) return 0;
			else return -1;
		}else
		{
			if(column2 == null) return 1;
		}
		return column1.compareTo(column2);
	}
}
