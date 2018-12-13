package examples.server.odservice.binary.io;

import examples.server.ifs.io.IFSReportReader;



public class ODServiceReportReader extends IFSReportReader {
	public ODServiceReportReader() {
		super(657, "/ODService.gz");
		this.setRecordLengthFileName(0, 1589, "/NonStopOp.gz");
	}
	
}
