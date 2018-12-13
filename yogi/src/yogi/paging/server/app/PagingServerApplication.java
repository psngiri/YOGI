package yogi.paging.server.app;

import yogi.server.app.BaseServerApplication;

public class PagingServerApplication extends BaseServerApplication {

	public PagingServerApplication(String dataLocation, String outputLocation) {
		super(dataLocation, outputLocation);
	}

	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: PagingServerApplication inputLocation [outputLocation]");
			System.out.println("Note: [outputLocation] is optional");
			return;
		}
		
		String outputLocation = null;
		if(args.length == 2) outputLocation = args[1];
		
		PagingServerApplication pagingServerApplication = new PagingServerApplication(args[0], outputLocation);
		pagingServerApplication.execute();
	}
}
