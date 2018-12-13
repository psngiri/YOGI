package examples.server.nonstopop.report;

import java.util.LinkedHashSet;
import java.util.Set;

import yogi.base.util.range.Range;
import yogi.paging.column.formatter.DoubleFormatter;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ReportConfig;
import yogi.report.server.config.column.DoubleColumnConfig;
import yogi.report.server.config.column.LongColumnConfig;
import yogi.report.server.config.column.StringColumnConfig;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowDoubleEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowLongEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowStringEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowWrapperEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddComplexTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddNDComplexTupleEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;
import yogi.report.split.Splitter;

import examples.odl.ODL;
import examples.odl.ODLAttribute;
import examples.odl.ODLInterface;
import examples.odl.ODLReader;
import examples.server.nonstopop.evaluators.NonStopOpTypeDoubleTupleEvaluator;
import examples.server.nonstopop.evaluators.NonStopOpTypeLongTupleEvaluator;
import examples.server.nonstopop.evaluators.NonStopOpTypeStringTupleEvaluator;

public class NonStopOpReportConfig extends ReportConfig<TupleRow> 
{
	private static final long serialVersionUID = 1L;

	public NonStopOpReportConfig()
	{
		super("IFS", "NonStopOp", new NonStopOpReportDataItems(),new BaseValidator("(.+)", "Enter forecat name"));
		String fileName = "*/examples/odl/test/NonStopOpOutDirect.odl";
		ODLReader reader = new ODLReader(fileName);
		ODL odl = reader.read();
		ODLInterface opNonStopOp = odl.getInterfaces().get("esOperationalNonStopOpOut");
		ODLInterface csNonStopOp = odl.getInterfaces().get("esDuplicateNonStopOpOut");
		Set<String> columnNames = new LinkedHashSet<String>();
		columnNames.addAll(opNonStopOp.getAttributes().keySet());
		columnNames.addAll(csNonStopOp.getAttributes().keySet());
		for(String columnName: columnNames){
			ODLAttribute opOdlAttribute = opNonStopOp.getAttributes().get(columnName);
			ODLAttribute csOdlAttribute = csNonStopOp.getAttributes().get(columnName);
			ODLAttribute attribute = null;
			Range<Integer> range = null;
			if(opOdlAttribute == null){
				attribute = csOdlAttribute;
			}else{
				attribute = opOdlAttribute;
				range = opOdlAttribute.getRange();
			}
			Range<Integer> csRange = null;
			if(csOdlAttribute != null){
				csRange = csOdlAttribute.getRange();
				if(csRange.equals(range)){
					csRange = null;
				}
			}
			switch(attribute.getType()){
			case FLOAT:
				if(csRange == null){
					this.addColumn(new DoubleColumnConfig<TupleRow>(columnName,new  TupleRowDoubleEvaluator(columnName, range,true), new DoubleFormatter(1,attribute.getDecimal())));
				}else{
					this.addColumn(new DoubleColumnConfig<TupleRow>(columnName,new  NonStopOpTypeDoubleTupleEvaluator(columnName, range,csRange, true), new DoubleFormatter(1,attribute.getDecimal())));
				}
				break;
			case INTEGER:
				if(csRange == null){
					this.addColumn(new LongColumnConfig<TupleRow>(columnName,new  TupleRowLongEvaluator(columnName, range,true)));
				}else{
					this.addColumn(new LongColumnConfig<TupleRow>(columnName,new  NonStopOpTypeLongTupleEvaluator(columnName, range,csRange, true)));
				}
				break;
			default:
				if(csRange == null){
					this.addColumn(new StringColumnConfig<TupleRow>(columnName,new  TupleRowStringEvaluator(columnName, range,true)));
				}else{
					this.addColumn(new StringColumnConfig<TupleRow>(columnName,new  NonStopOpTypeStringTupleEvaluator(columnName, range,csRange, true)));
				}
				break;
			}
		}
		this.addColumn(new StringColumnConfig<TupleRow>("Segment", new StringAddComplexTupleEvaluator("Segment", "departureAirportCode", "arrivalAirportCode")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND", new StringAddNDComplexTupleEvaluator("SegmentND", "departureAirportCode", "arrivalAirportCode")));
		StringColumnConfig<TupleRow> column = new StringColumnConfig<TupleRow>("Hub", new HubEvaluator("Hub"));
		column.setSplitter(new Splitter(){

			@Override
			public Comparable[] split(Object value) {
				String hub = (String) value;
				String[] split = hub.split(",");
				String[] split1 = new String[split.length+1];
				for(int i = 0; i < split.length;  i++){
					split1[i] = split[i];
				}
				split1[split.length] = "System";
				return split1;
			}
			
		});
		this.addColumn(column);
		
		this.addColumn(new StringColumnConfig<TupleRow>("MktAirline", new TupleRowWrapperEvaluator<String>("MktAirline", "airlineCode")));
		
	}
	
	class HubEvaluator extends TupleRowComplexEvaluator<String> {

		public HubEvaluator(String name) {
			super(name, null, false, "departureAirportCode", "arrivalAirportCode");
		}

		@Override
		public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
			String rtnValue = tupleRow.getValue("departureAirportCode")+ "," + tupleRow.getValue("arrivalAirportCode");
			return rtnValue;
		}
		
	}
}