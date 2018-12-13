package yogi.report.server.row;

public interface Row {
	<T> T getValue(String name);
}
