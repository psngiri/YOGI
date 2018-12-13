package examples.server.oaAvailability.report;

import yogi.base.util.range.Range;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ReportConfig;
import yogi.report.server.config.column.StringColumnConfig;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowStringEvaluator;

public class OaAvailabilityReportConfig extends ReportConfig<TupleRow> 
{
	private static final long serialVersionUID = 1L;

	public OaAvailabilityReportConfig()
	{
		super("RM", "OaAvailability", new OaAvailabilityReportDataItems(),new BaseValidator("(.+)", "Enter Dataset name"));
		this.addColumn(getStringColumnConfig("Column1", 0, false));
		this.addColumn(getStringColumnConfig("Column2", 1, false));
		this.addColumn(getStringColumnConfig("Column3", 2, false));
		this.addColumn(getStringColumnConfig("Column4", 3, false));
		this.addColumn(getStringColumnConfig("Column5", 4, false));
		this.addColumn(getStringColumnConfig("Column6", 5, false));
		this.addColumn(getStringColumnConfig("Column7", 6, false));
		this.addColumn(getStringColumnConfig("Column8", 7, false));
		this.addColumn(getStringColumnConfig("Column9", 8, false));
		this.addColumn(getStringColumnConfig("Column10", 9, true));
	}

	private StringColumnConfig<TupleRow> getStringColumnConfig(String columnName, int columnIndex, boolean trim) {
		return new StringColumnConfig<TupleRow>(columnName, new TupleRowStringEvaluator(columnName, new Range<Integer>(columnIndex,columnIndex+1), trim));
	}
}