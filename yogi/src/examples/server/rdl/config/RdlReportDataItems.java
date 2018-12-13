package examples.server.rdl.config;


import yogi.report.server.tuple.TupleReportDataItems;

import examples.server.rdl.io.RdlReportReader;

public class RdlReportDataItems extends TupleReportDataItems {

	public RdlReportDataItems() {
		super(RdlReportReader.class);
	}	
}