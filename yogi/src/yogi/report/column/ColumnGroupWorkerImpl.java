package yogi.report.column;

import yogi.report.function.Function;
import yogi.report.group.Group;
import yogi.report.group.GroupBy;

class ColumnGroupWorkerImpl<T, C> implements ColumnGroupWorker<T>{
	private Function<C> funtcion;
	private C keyValue;
	private ColumnWorkerBaseImpl<T, C> columnWorker;
	private ColumnDefinitionBaseImpl<T, C> columnDefinition;
	private int index;
	
	public ColumnGroupWorkerImpl(ColumnDefinitionBaseImpl<T, C> columnDefinition, Function<C> funtcion, ColumnWorkerBaseImpl<T, C> columnWorker) {
		super();
		this.columnDefinition = columnDefinition;
		this.funtcion = funtcion;
		this.columnWorker = columnWorker;
	}

	public void reset() {
		if(funtcion == null)
		{
			keyValue = null;
			return;
		}
		funtcion.reset();
	}

	public void processObject(Group<T> group, int indexInGroup, int multiplier) {
		if(funtcion == null)
		{
			if(keyValue == null) keyValue = columnWorker.getValue(group, indexInGroup);
			return;
		}
		funtcion.process(columnWorker.getValue(group, indexInGroup), multiplier);
	}

	@SuppressWarnings("unchecked")
	public C getGroupValue(Group<T> group)
	{
		if(funtcion == null)
		{
			GroupBy<T> groupBy = group.getGroupBy();
			if(groupBy == null || !groupBy.hasKey(columnDefinition.getName())) return null;
			Comparable<?> rtnValue = group.getKeyValue(columnDefinition.getName());
            if(rtnValue != null) return (C)rtnValue;
			return keyValue;
		}
		return funtcion.getValue();

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isKey() {
		return (funtcion == null);
	}
	
}
