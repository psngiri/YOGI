package yogi.report.group;

import java.util.Comparator;
import java.util.List;

import yogi.report.column.ColumnDefinition;


public interface GroupBy<T> extends Comparator<T>{
	List<ColumnDefinition<T>> getColumns();
	boolean hasKey(String keyName);
	GroupMultiplierCalculator<T> getMultiplierCalculator();
	void setMultiplierCalculator(GroupMultiplierCalculator<T> multiplierCalculator);
	GroupCreator<T> getGroupCreator();
}
