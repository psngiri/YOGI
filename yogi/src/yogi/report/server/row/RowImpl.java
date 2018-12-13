package yogi.report.server.row;

import java.util.Map;

public class RowImpl implements Row{
	private Object[] row;
	private Map<String, Integer> columnIndexMap;
	
	public RowImpl(Object[] row, Map<String, Integer> columnIndexMap) {
		super();
		this.row = row;
		this.columnIndexMap = columnIndexMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(String name) {
		try {
			return (T) row[columnIndexMap.get(name)];
		} catch (Exception e) {
			throw new RuntimeException("Error parsing column:"+name, e);
		}
	}

}
