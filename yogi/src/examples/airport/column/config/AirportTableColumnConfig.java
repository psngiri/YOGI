package examples.airport.column.config;

import yogi.paging.column.types.StringTableColumnConfig;

public class AirportTableColumnConfig extends StringTableColumnConfig {

	private static final long serialVersionUID = 8177077130451331008L;
	
	public static String ViewName = "Airport";
	public static int Width = 60;
	
	public AirportTableColumnConfig(String name, int width) {
		super(name, width);
	}
	
	public AirportTableColumnConfig() {
		this(ViewName, Width);
	}
}
