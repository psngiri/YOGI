package examples.server.odservice.binary.report;


import yogi.report.server.tuple.TupleReportDataItems;

import examples.server.odservice.binary.io.ODServiceReportReader;



public class ODserviceReportDataItems extends TupleReportDataItems {

	public ODserviceReportDataItems() {
		super(ODServiceReportReader.class);
	}	
}