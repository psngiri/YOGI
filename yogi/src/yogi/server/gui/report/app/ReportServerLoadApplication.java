package yogi.server.gui.report.app;

import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.app.ApplicationStartUpModule;
import yogi.server.gui.app.GuiServerApplication;

public class ReportServerLoadApplication extends GuiServerApplication {

	public ReportServerLoadApplication(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}

	@Override
	protected void addModules(final DbResource ngpDbResource) {
		super.addModules(ngpDbResource);
//		this.addModule(new ReportStartUpModule(ngpDbResource));
		this.addModule(new BaseModule(){

			@Override
			public void setup() {
				this.addProcessor(new ApplicationStartUpModule(ngpDbResource));				
				this.addProcessor(new ReportReaderModule(ngpDbResource));
				this.addProcessor(new ReportDbWriterModule(ngpDbResource,null));
			}
			
		});
	}
	
	public static void main(String[] args)
	{
		if(args.length < 1) {
			System.out.println("Usage: ReportServerApplication inputLocation [outputLocation]");
			System.out.println("Note: [outputLocation] is optional");
			return;
		}
		
		String outputLocation = null;
		if(args.length == 2) outputLocation = args[1];
		
		ReportServerLoadApplication reportServerApplication = new ReportServerLoadApplication(args[0], outputLocation);
		reportServerApplication.execute();
	}

}
