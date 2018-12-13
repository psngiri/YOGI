package examples.server.rdl.config;

import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.JsonAssistant;
import yogi.base.util.range.Range;
import yogi.paging.column.formatter.DoubleFormatter;
import yogi.report.rdl.RdlColumn;
import yogi.report.rdl.RdlColumns;
import yogi.report.server.ReportDataItemsProvider;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ReportConfig;
import yogi.report.server.config.Validator;
import yogi.report.server.config.column.DoubleColumnConfig;
import yogi.report.server.config.column.LongColumnConfig;
import yogi.report.server.config.column.StringColumnConfig;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.binary.TupleRowBinaryDoubleEvaluator;
import yogi.report.server.tuple.evaluators.binary.TupleRowBinaryLongEvaluator;
import yogi.report.server.tuple.evaluators.binary.TupleRowBinaryStringEvaluator;

public class RdlBinaryReportConfig extends ReportConfig<TupleRow> 
{
	private static final long serialVersionUID = 1L;

	public RdlBinaryReportConfig(){
		this("RdlReport", "Legs", new RdlReportDataItems(), new BaseValidator("(.+)", "Enter dataset name"),new ClassPathResource("legsColumnsLayout.json", RdlBinaryReportConfig.class));
	}
	
	protected RdlBinaryReportConfig(String reportType, String reportName,
			ReportDataItemsProvider<TupleRow> reportDataItems, Validator validator, SystemResource columnsConfig) {
		super(reportType, reportName, reportDataItems, validator);
		RdlColumns columns = JsonAssistant.get().fromJsonFile(columnsConfig, 0, RdlColumns.class);
		int start  = 0;
		for(RdlColumn column: columns){
			String columnName = column.getName();
			int size = column.getSize();
			int end = start + size;
			Range<Integer> range = new Range<Integer>(start, end);
			switch(column.getType()){
			case FLOAT:
					this.addColumn(new DoubleColumnConfig<TupleRow>(columnName,new  TupleRowBinaryDoubleEvaluator(columnName, range,true), new DoubleFormatter(1,6)));
				break;
			case INTEGER:
					this.addColumn(new LongColumnConfig<TupleRow>(columnName,new  TupleRowBinaryLongEvaluator(columnName, range,true)));
				break;
			default:
					this.addColumn(new StringColumnConfig<TupleRow>(columnName,new  TupleRowBinaryStringEvaluator(columnName, range,true)));
				break;
			}
			start = end;
		}
	
	}
}