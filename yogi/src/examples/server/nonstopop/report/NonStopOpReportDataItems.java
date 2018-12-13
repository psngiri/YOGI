package examples.server.nonstopop.report;


import yogi.report.server.tuple.TupleReportDataItems;

import examples.server.nonstopop.io.NonStopOpReportReader;

public class NonStopOpReportDataItems extends TupleReportDataItems {

	public NonStopOpReportDataItems() {
		super(NonStopOpReportReader.class);
	}	
}