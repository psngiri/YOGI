package yogi.tools.swingx.treetable;

public interface TreeTableDataHelper<T> {
	Object getValueAt(T data, int column);
	int getColumnCount();
	boolean isEditable(T data, int column);
	void setValueAt(T data, Object aValue, int column);
	String getColumnName(int column);
}
