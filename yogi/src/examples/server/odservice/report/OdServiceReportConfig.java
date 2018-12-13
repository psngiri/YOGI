package examples.server.odservice.report;

import java.util.LinkedHashSet;
import java.util.Set;

import yogi.base.util.range.Range;
import yogi.paging.column.formatter.DoubleFormatter;
import yogi.paging.column.formatter.FractionDoubleFormatter;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ReportConfig;
import yogi.report.server.config.column.DoubleColumnConfig;
import yogi.report.server.config.column.FractionDoubleColumnConfig;
import yogi.report.server.config.column.LongColumnConfig;
import yogi.report.server.config.column.StringColumnConfig;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowDoubleEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowLongEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowStringEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowWrapperEvaluator;
import yogi.report.server.tuple.evaluators.complex.DependentGroupEvaluator;
import yogi.report.server.tuple.evaluators.complex.DoubleDivideComplexTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.DoubleMultiplyComplexTupleGroupEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddComplexSeparatorNDTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddComplexSeparatorTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddComplexTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddNDComplexTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.StringAddSeparatorComplexTupleEvaluator;
import yogi.report.server.tuple.evaluators.complex.TupleRowDiffDoubleGroupEvaluator;
import yogi.report.server.tuple.evaluators.complex.TupleRowFractionDoubleGroupEvaluator;
import yogi.report.split.SimpleSplitter;
import yogi.report.split.Splitter;
import yogi.report.split.UniqueSplitter;

import examples.odl.ODL;
import examples.odl.ODLAttribute;
import examples.odl.ODLInterface;
import examples.odl.ODLReader;
import examples.server.odservice.evaluators.ODServiceAAProrateRevenueEvaluator;
import examples.server.odservice.evaluators.ODServiceNonstopOpStringTupleEvaluator;
import examples.server.odservice.evaluators.ODServiceOpTypeStringTupleEvaluator;
import examples.server.odservice.evaluators.ODServiceProrateFactorEvaluator;
import examples.server.odservice.evaluators.ODServiceTypeDoubleTupleEvaluator;
import examples.server.odservice.evaluators.ODServiceTypeLongTupleEvaluator;
import examples.server.odservice.evaluators.ODServiceTypeStringTupleEvaluator;
import examples.server.odservice.evaluators.PooValueTupleEvaluator;

public class OdServiceReportConfig extends ReportConfig<TupleRow> 
{
	private static final long serialVersionUID = 1L;

	public OdServiceReportConfig()
	{
		super("IFS", "ODService", new ODserviceReportDataItems(),new BaseValidator("(.+)", "Enter forecat name"));
		
		String fileName = "*/examples/odl/test/ODServiceOutDirect.odl";
		ODLReader reader = new ODLReader(fileName);
		ODL odl = reader.read();
		ODLInterface opODService = odl.getInterfaces().get("esOperationalODServiceOut");
		ODLInterface csODservice = odl.getInterfaces().get("esDuplicateODServiceOut");
		Set<String> columnNames = new LinkedHashSet<String>();
		columnNames.addAll(opODService.getAttributes().keySet());
		columnNames.addAll(csODservice.getAttributes().keySet());
		for(String columnName: columnNames){
			ODLAttribute opOdlAttribute = opODService.getAttributes().get(columnName);
			ODLAttribute csOdlAttribute = csODservice.getAttributes().get(columnName);
			ODLAttribute attribute = opOdlAttribute;
			if(opOdlAttribute == null){
				attribute = csOdlAttribute;
			}
			Range<Integer> range = attribute.getRange();
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
					this.addColumn(new DoubleColumnConfig<TupleRow>(columnName,new  ODServiceTypeDoubleTupleEvaluator(columnName, range,csRange, true), new DoubleFormatter(1,attribute.getDecimal())));
				}
				break;
			case INTEGER:
				if(csRange == null){
					this.addColumn(new LongColumnConfig<TupleRow>(columnName,new  TupleRowLongEvaluator(columnName, range,true)));
				}else{
					this.addColumn(new LongColumnConfig<TupleRow>(columnName,new  ODServiceTypeLongTupleEvaluator(columnName, range,csRange, true)));
				}
				break;
			default:
				if(csRange == null){
					this.addColumn(new StringColumnConfig<TupleRow>(columnName,new  TupleRowStringEvaluator(columnName, range,true)));
				}else{
					this.addColumn(new StringColumnConfig<TupleRow>(columnName,new  ODServiceTypeStringTupleEvaluator(columnName, range,csRange, true)));
				}
				break;
			}
		}
		this.addColumn(new StringColumnConfig<TupleRow>("OD", new StringAddComplexTupleEvaluator("OD", "departureAirportCode", "arrivalAirportCode")));
		this.addColumn(new StringColumnConfig<TupleRow>("ODND", new StringAddNDComplexTupleEvaluator("ODND", "departureAirportCode", "arrivalAirportCode")));
		this.addColumn(new DoubleColumnConfig<TupleRow>("RevenuePerObservedPax", new DoubleDivideComplexTupleEvaluator("RevenuePerObservedPax", "revenue", "observedPassengers"), new DoubleFormatter(1,6)));
		this.addColumn(new DoubleColumnConfig<TupleRow>("RevenuePerObservedPax", new DoubleDivideComplexTupleEvaluator("RevenuePerObservedPax", "revenue", "observedPassengers"), new DoubleFormatter(1,6)));
		this.addColumn(new StringColumnConfig<TupleRow>("Segment1", new StringAddComplexTupleEvaluator("Segment1", "departureAirportCode1", "arrivalAirportCode1")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND1", new StringAddNDComplexTupleEvaluator("SegmentND1", "departureAirportCode1", "arrivalAirportCode1")));
		this.addColumn(new StringColumnConfig<TupleRow>("Segment2", new StringAddComplexTupleEvaluator("Segment2", "departureAirportCode2", "arrivalAirportCode2")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND2", new StringAddNDComplexTupleEvaluator("SegmentND2", "departureAirportCode2", "arrivalAirportCode2")));
		this.addColumn(new StringColumnConfig<TupleRow>("Segment3", new StringAddComplexTupleEvaluator("Segment3", "departureAirportCode3", "arrivalAirportCode3")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND3", new StringAddNDComplexTupleEvaluator("SegmentND3", "departureAirportCode3", "arrivalAirportCode3")));
		this.addColumn(new StringColumnConfig<TupleRow>("Segment4", new StringAddComplexTupleEvaluator("Segment4", "departureAirportCode4", "arrivalAirportCode4")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND4", new StringAddNDComplexTupleEvaluator("SegmentND4", "departureAirportCode4", "arrivalAirportCode4")));
		this.addColumn(new StringColumnConfig<TupleRow>("Segment5", new StringAddComplexTupleEvaluator("Segment5", "departureAirportCode5", "arrivalAirportCode5")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND5", new StringAddNDComplexTupleEvaluator("SegmentND5", "departureAirportCode5", "arrivalAirportCode5")));
		this.addColumn(new StringColumnConfig<TupleRow>("Segment6", new StringAddComplexTupleEvaluator("Segment6", "departureAirportCode6", "arrivalAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("SegmentND6", new StringAddNDComplexTupleEvaluator("SegmentND6", "departureAirportCode6", "arrivalAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegSegmentNew", new StringAddComplexSeparatorTupleEvaluator("LegSegmentNew", '/', 1, "Segment1", "Segment2", 
				"Segment3", "Segment4", "Segment5", "Segment6")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegSegmentNDNew", new StringAddComplexSeparatorTupleEvaluator("LegSegmentNDNew", '/', 1, "SegmentND1", "SegmentND2", 
				"SegmentND3", "SegmentND4", "SegmentND5", "SegmentND6")));

		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment1", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegment1", '-', "legAirline1", "Segment1")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentND1", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegmentND1", '-', "legAirline1", "SegmentND1")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment2", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegment2", '-', "legAirline2", "Segment2")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentND2", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegmentND2", '-', "legAirline2", "SegmentND2")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment3", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegment3", '-', "legAirline3", "Segment3")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentND3", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegmentND3", '-', "legAirline3", "SegmentND3")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment4", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegment4", '-', "legAirline4", "Segment4")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentND4", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegmentND4", '-', "legAirline4", "SegmentND4")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment5", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegment5", '-', "legAirline5", "Segment5")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentND5", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegmentND5", '-', "legAirline5", "SegmentND5")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment6", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegment6", '-', "legAirline6", "Segment6")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentND6", new StringAddSeparatorComplexTupleEvaluator("LegAirlineSegmentND6", '-', "legAirline6", "SegmentND6")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentNew", new StringAddComplexSeparatorTupleEvaluator("LegAirlineSegmentNew", '/', 1, "LegAirlineSegment1", "LegAirlineSegment2", 
				"LegAirlineSegment3", "LegAirlineSegment4", "LegAirlineSegment5", "LegAirlineSegment6")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegmentNDNew", new StringAddComplexSeparatorTupleEvaluator("LegAirlineSegmentNDNew", '/', 1, "LegAirlineSegmentND1", "LegAirlineSegmentND2", 
				"LegAirlineSegmentND3", "LegAirlineSegmentND4", "LegAirlineSegmentND5", "LegAirlineSegmentND6")));

		this.addColumn(new StringColumnConfig<TupleRow>("LegSegment", new StringAddComplexSeparatorTupleEvaluator("LegSegment", '/', 2, "departureAirportCode1", "arrivalAirportCode1", 
				"departureAirportCode2", "arrivalAirportCode2", "departureAirportCode3", "arrivalAirportCode3", "departureAirportCode4", "arrivalAirportCode4"
				, "departureAirportCode5", "arrivalAirportCode5", "departureAirportCode6", "arrivalAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("LegSegmentND", new StringAddComplexSeparatorNDTupleEvaluator("LegSegmentND", '/', 2, "departureAirportCode1", "arrivalAirportCode1", 
				"departureAirportCode2", "arrivalAirportCode2", "departureAirportCode3", "arrivalAirportCode3", "departureAirportCode4", "arrivalAirportCode4"
				, "departureAirportCode5", "arrivalAirportCode5", "departureAirportCode6", "arrivalAirportCode6")));

		this.addColumn(new StringColumnConfig<TupleRow>("legKey", new StringAddComplexSeparatorTupleEvaluator("legKey", '/', 1, "legKey1", "legKey2", 
				"legKey3", "legKey4", "legKey5", "legKey6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline", new StringAddComplexSeparatorTupleEvaluator("legAirline", '/', 1, "legAirline1", "legAirline2", 
				"legAirline3", "legAirline4", "legAirline5", "legAirline6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legFlightNumber", new StringAddComplexSeparatorTupleEvaluator("legFlightNumber", '/', 1, "legFlightNumber1", "legFlightNumber2", 
				"legFlightNumber3", "legFlightNumber4", "legFlightNumber5", "legFlightNumber6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legItineraryNumber", new StringAddComplexSeparatorTupleEvaluator("legItineraryNumber", '/', 1, "legItineraryNumber1", "legItineraryNumber2", 
				"legItineraryNumber3", "legItineraryNumber4", "legItineraryNumber5", "legItineraryNumber6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legNumber", new StringAddComplexSeparatorTupleEvaluator("legNumber", '/', 1, "legNumber1", "legNumber2", 
				"legNumber3", "legNumber4", "legNumber5", "legNumber6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legDepartureAirportCode", new StringAddComplexSeparatorTupleEvaluator("legDepartureAirportCode", '/', 1, "departureAirportCode1", "departureAirportCode2", 
				"departureAirportCode3", "departureAirportCode4", "departureAirportCode5", "departureAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legArrivalAirportCode", new StringAddComplexSeparatorTupleEvaluator("legArrivalAirportCode", '/', 1, "arrivalAirportCode1", "arrivalAirportCode2", 
				"arrivalAirportCode3", "arrivalAirportCode4", "arrivalAirportCode5", "arrivalAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legDepartureHour", new StringAddComplexSeparatorTupleEvaluator("legDepartureHour", '/', 1, "departureHour1", "departureHour2", 
				"departureHour3", "departureHour4", "departureHour5", "departureHour6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legDepartureMinute", new StringAddComplexSeparatorTupleEvaluator("legDepartureMinute", '/', 1, "departureMinute1", "departureMinute2", 
				"departureMinute3", "departureMinute4", "departureMinute5", "departureMinute6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legArrivalHour", new StringAddComplexSeparatorTupleEvaluator("legArrivalHour", '/', 1, "arrivalHour1", "arrivalHour2", 
				"arrivalHour3", "arrivalHour4", "arrivalHour5", "arrivalHour6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legArrivalMinute", new StringAddComplexSeparatorTupleEvaluator("legArrivalMinute", '/', 1, "arrivalMinute1", "arrivalMinute2", 
				"arrivalMinute3", "arrivalMinute4", "arrivalMinute5", "arrivalMinute6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legEquipCode", new StringAddComplexSeparatorTupleEvaluator("legEquipCode", '/', 1, "equipCode1", "equipCode2", 
				"equipCode3", "equipCode4", "equipCode5", "equipCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legFareRatio", new StringAddComplexSeparatorTupleEvaluator("legFareRatio", '/', 1, "fareRatio1", "fareRatio2", 
				"fareRatio3", "fareRatio4", "fareRatio5", "fareRatio6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legFare", new StringAddComplexSeparatorTupleEvaluator("legFare", '/', 1, "fare1", "fare2", 
				"fare3", "fare4", "fare5", "fare6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legType", new StringAddComplexSeparatorTupleEvaluator("legType", '/', 1, "legType1", "legType2", 
				"legType3", "legType4", "legType5", "legType6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legDuplicateType", new StringAddComplexSeparatorTupleEvaluator("legDuplicateType", '/', 1, "duplicateType1", "duplicateType2", 
				"duplicateType3", "duplicateType4", "duplicateType5", "duplicateType6")));

		this.addColumn(new StringColumnConfig<TupleRow>("LegAirlineSegment", new StringAddComplexSeparatorTupleEvaluator("LegAirlineSegment", '/', 3, "legAirline1", "departureAirportCode1", "arrivalAirportCode1", 
				"legAirline2", "departureAirportCode2", "arrivalAirportCode2", "legAirline3", "departureAirportCode3", "arrivalAirportCode3", "legAirline4", "departureAirportCode4", "arrivalAirportCode4"
				, "legAirline5", "departureAirportCode5", "arrivalAirportCode5", "legAirline6", "departureAirportCode6", "arrivalAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("opLegAirline1", new ODServiceOpTypeStringTupleEvaluator("opLegAirline1", new Range<Integer>(620,623), true)));
		this.addColumn(new StringColumnConfig<TupleRow>("opLegAirline2", new ODServiceOpTypeStringTupleEvaluator("opLegAirline2", new Range<Integer>(689,692), true)));
		this.addColumn(new StringColumnConfig<TupleRow>("opLegAirline3", new ODServiceOpTypeStringTupleEvaluator("opLegAirline3", new Range<Integer>(758,761), true)));
		this.addColumn(new StringColumnConfig<TupleRow>("opLegAirline4", new ODServiceOpTypeStringTupleEvaluator("opLegAirline4", new Range<Integer>(827,830), true)));
		this.addColumn(new StringColumnConfig<TupleRow>("opLegAirline5", new ODServiceOpTypeStringTupleEvaluator("opLegAirline5", new Range<Integer>(896,899), true)));
		this.addColumn(new StringColumnConfig<TupleRow>("opLegAirline6", new ODServiceOpTypeStringTupleEvaluator("opLegAirline6", new Range<Integer>(965,968), true)));
		this.addColumn(new StringColumnConfig<TupleRow>("OpLegAirlineSegment", new StringAddComplexSeparatorTupleEvaluator("OpLegAirlineSegment", '/', 3, "opLegAirline1", "departureAirportCode1", "arrivalAirportCode1", 
				"opLegAirline2", "departureAirportCode2", "arrivalAirportCode2", "opLegAirline3", "departureAirportCode3", "arrivalAirportCode3", "opLegAirline4", "departureAirportCode4", "arrivalAirportCode4"
				, "opLegAirline5", "departureAirportCode5", "arrivalAirportCode5", "opLegAirline6", "departureAirportCode6", "arrivalAirportCode6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline1NS", new ODServiceNonstopOpStringTupleEvaluator("legAirline1NS", new Range<Integer>(10,13), true, "legKey1")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline2NS", new ODServiceNonstopOpStringTupleEvaluator("legAirline2NS", new Range<Integer>(10,13), true, "legKey2")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline3NS", new ODServiceNonstopOpStringTupleEvaluator("legAirline3NS", new Range<Integer>(10,13), true, "legKey3")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline4NS", new ODServiceNonstopOpStringTupleEvaluator("legAirline4NS", new Range<Integer>(10,13), true, "legKey4")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline5NS", new ODServiceNonstopOpStringTupleEvaluator("legAirline5NS", new Range<Integer>(10,13), true, "legKey5")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirline6NS", new ODServiceNonstopOpStringTupleEvaluator("legAirline6NS", new Range<Integer>(10,13), true, "legKey6")));
		this.addColumn(new StringColumnConfig<TupleRow>("legAirlineSegmentNS", new StringAddComplexSeparatorTupleEvaluator("legAirlineSegmentNS", '/', 3, "legAirline1NS", "departureAirportCode1", "arrivalAirportCode1", 
				"legAirline2NS", "departureAirportCode2", "arrivalAirportCode2", "legAirline3NS", "departureAirportCode3", "arrivalAirportCode3", "legAirline4NS", "departureAirportCode4", "arrivalAirportCode4"
				, "legAirline5NS", "departureAirportCode5", "arrivalAirportCode5", "legAirline6NS", "departureAirportCode6", "arrivalAirportCode6")));

		this.addColumn(new DoubleColumnConfig<TupleRow>("AAProrateRevenue",new  ODServiceAAProrateRevenueEvaluator("AAProrateRevenue"), new DoubleFormatter(1,6)));
		
		StringColumnConfig<TupleRow> column = new StringColumnConfig<TupleRow>("POO", new TupleRowWrapperEvaluator<String>("POO", "OD"));
		column.setSplitter(new Splitter(){

			@Override
			public Comparable<?>[] split(Object value) {
				String[] split = new String[2];
				String myValue = value.toString();
				split[0] = myValue.substring(0, 3);
				split[1] = myValue.substring(3, 6);
				return split;
			}
			
		});
		this.addColumn(column);
		
		this.addColumn(new DoubleColumnConfig<TupleRow>("PooObservedPassengers", new PooValueTupleEvaluator<Double>("PooObservedPassengers", "observedPassengersOutbound", "observedPassengersReturn"), new DoubleFormatter(1,6)));
		PooValueTupleEvaluator<Double> pooRevenueEvaluator = new PooValueTupleEvaluator<Double>("PooRevenue", "revenueOutbound", "revenueReturn");
		this.addColumn(new DoubleColumnConfig<TupleRow>("PooRevenue", pooRevenueEvaluator, new DoubleFormatter(1,6)));
		ODServiceProrateFactorEvaluator prorateFactorEvaluator = new ODServiceProrateFactorEvaluator("ProrateFactor");
//		this.addColumn(new DoubleColumnConfig<TupleRow>("ProrateFactor", prorateFactorEvaluator, new DoubleFormatter(1,6)));

		column = new StringColumnConfig<TupleRow>("ProrateCarrier", new TupleRowWrapperEvaluator<String>("ProrateCarrier", "legAirline"));
		column.setSplitter(new UniqueSplitter());
		this.addColumn(column);
		column = new StringColumnConfig<TupleRow>("ProrateSegment", new TupleRowWrapperEvaluator<String>("ProrateSegment", "LegSegment"));
		column.setSplitter(new SimpleSplitter());
		this.addColumn(column);

//		this.addColumn(new DoubleColumnConfig<TupleRow>("PooProrateRevenue", new DoubleMultiplyComplexTupleGroupEvaluator("PooProrateRevenue", pooRevenueEvaluator, prorateFactorEvaluator), new DoubleFormatter(1,6)));
		DependentGroupEvaluator<Double> revenue = new DependentGroupEvaluator<Double>("revenue");
		DoubleMultiplyComplexTupleGroupEvaluator prorateRevenue = new DoubleMultiplyComplexTupleGroupEvaluator("ProrateRevenue", pooRevenueEvaluator, prorateFactorEvaluator);
		this.addColumn(new DoubleColumnConfig<TupleRow>("ProrateRevenue", prorateRevenue, new DoubleFormatter(1,6)));
		this.addColumn(new FractionDoubleColumnConfig<TupleRow>("ProrateFactor",new TupleRowFractionDoubleGroupEvaluator("ProrateFactor",prorateRevenue,revenue), new FractionDoubleFormatter(1,6)));
		this.addColumn(new DoubleColumnConfig<TupleRow>("ProrateRevenueUpDown", new TupleRowDiffDoubleGroupEvaluator("ProrateRevenueUpDown", revenue, prorateRevenue), new DoubleFormatter(1,6)));

	}
	
}