package yogi.server.gui.report.app;

import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.app.GuiServerApplication;

public class ReportServerApplication extends GuiServerApplication {

	public ReportServerApplication(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}

	@Override
	protected void addModules(DbResource ngpDbResource) {
		super.addModules(ngpDbResource);
		this.addModule(new ReportStartUpModule(ngpDbResource));
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
		
		ReportServerApplication reportServerApplication = new ReportServerApplication(args[0], outputLocation);
		reportServerApplication.execute();
	}

}
