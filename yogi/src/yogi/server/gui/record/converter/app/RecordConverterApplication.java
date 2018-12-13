package yogi.server.gui.record.converter.app;

import yogi.base.app.standard.StandardApplication;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.PooledSynchronizedDbResource;
import yogi.server.gui.record.converter.RecordConverter;



public class RecordConverterApplication extends StandardApplication {
	protected DbResource dbresource;

	public RecordConverterApplication(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}
	
	public void setup() {
		super.setup();
		dbresource = new PooledSynchronizedDbResource();
		this.addReader(new RecordConverter(dbresource));
	}
	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: RecordConverterApplication worksetLocation [outputLocation]");
			System.out.println("[outputLocation] is optional");
			return;
		}
		String outputLocation = null;
		if(args.length == 2) {
			outputLocation = args[1];
		}
		RecordConverterApplication recordConverterApplication = new RecordConverterApplication(args[0], outputLocation);
		recordConverterApplication.execute();
	}
}

