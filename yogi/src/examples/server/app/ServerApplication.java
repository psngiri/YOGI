package examples.server.app;

import yogi.base.io.resource.db.DbResource;
import yogi.server.app.BaseServerApplication;
import yogi.server.util.BaseServerAssistant;


/**
 * @author Vikram Vadavala
 *
 */
public class ServerApplication extends BaseServerApplication {
	
	public ServerApplication(String dataLocation,String outputLocation) {
		super(dataLocation, outputLocation);
	}

	public void setup() {	
		super.setup();
		DbResource dbResource = BaseServerAssistant.get().getDbResource();
		this.addModule(new ServerStartUpModule(dbResource));
	}

	public static void main(String[] args)
	{
		if(args.length < 1) {
			System.out.println("Usage: CommandServerApplication inputLocation [outputLocation]");
			System.out.println("Note: [outputLocation] is optional");
			return;
		}
		
		String outputLocation = null;
		if(args.length == 2) outputLocation = args[1];
		
		ServerApplication domesticNgpServerApplication = new ServerApplication(args[0], outputLocation);
		domesticNgpServerApplication.execute();
	}
}
