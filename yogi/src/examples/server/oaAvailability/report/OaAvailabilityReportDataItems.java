package examples.server.oaAvailability.report;


import yogi.report.server.tuple.TupleReportDataItems;

import examples.server.oaAvailability.io.OaAvailablityReportReader;

public class OaAvailabilityReportDataItems extends TupleReportDataItems {

	public OaAvailabilityReportDataItems() {
		super(OaAvailablityReportReader.class);
	}	
}