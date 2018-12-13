package yogi.report.column.serial.number;

import yogi.base.io.Formatter;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.group.Group;
import yogi.report.group.GroupEvaluatorAdapter;

public class SerialNumberColumn<T> extends ColumnDefinitionImpl<T, Integer> {
	
	public SerialNumberColumn(String name, int width, Formatter<? super Integer> formatter, String[] heading) {
		super(name, width, formatter, null, heading, null, new SerialNumberEvaluator<T>());
	}

	static class SerialNumberEvaluator<T> extends GroupEvaluatorAdapter<T, Integer>
	{

		public Integer evaluate(Group<T> group, int indexInGroup) {
			return indexInGroup + 1;
		}
		
	}

}
