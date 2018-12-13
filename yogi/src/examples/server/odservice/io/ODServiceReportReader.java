package examples.server.odservice.io;

import examples.server.ifs.io.IFSReportReader;



public class ODServiceReportReader extends IFSReportReader {
	public ODServiceReportReader() {
		super(1373, "/ODService.gz");
		this.setRecordLengthFileName(0, 1589, "/NonStopOp.gz");
	}
	
}
