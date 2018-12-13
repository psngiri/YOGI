package yogi.server.gui.report.converter.app;

import yogi.base.app.standard.StandardApplication;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.PooledSynchronizedDbResource;
import yogi.server.gui.report.converter.ReportRecordConverter;



public class ReportRecordConverterApplication extends StandardApplication {
	protected DbResource dbresource;

	public ReportRecordConverterApplication(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}
	
	public void setup() {
		super.setup();
		dbresource = new PooledSynchronizedDbResource();
		this.addReader(new ReportRecordConverter(dbresource));
	}
	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: ReportRecordConverterApplication worksetLocation [outputLocation]");
			System.out.println("[outputLocation] is optional");
			return;
		}
		String outputLocation = null;
		if(args.length == 2) {
			outputLocation = args[1];
		}
		ReportRecordConverterApplication recordConverterApplication = new ReportRecordConverterApplication(args[0], outputLocation);
		recordConverterApplication.execute();
	}
}

