package examples.server.odservice.report;


import yogi.report.server.tuple.TupleReportDataItems;

import examples.server.odservice.io.ODServiceReportReader;

public class ODserviceReportDataItems extends TupleReportDataItems {

	public ODserviceReportDataItems() {
		super(ODServiceReportReader.class);
	}	
}