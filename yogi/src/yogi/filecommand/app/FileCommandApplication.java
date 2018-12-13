package yogi.filecommand.app;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.standard.StandardApplication;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandApplication extends StandardApplication {

	public static boolean RUN = true;

	public FileCommandApplication(String dataLocation, String outputLocation) {
		super(dataLocation,outputLocation);
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {	
		super.setup();
		this.addModule(new FileCommandModule(ApplicationProperties.InputDataLocation+"/commandsFile.txt"));
	}
	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: Application dataLocation [outputLocation]");
			System.out.println("Note: [outputLocation] is optional");
			return;
		}
		
		String outputLocation = null;
		if(args.length == 2) outputLocation = args[1];
		
		FileCommandApplication ngpServerApplicaiton = new FileCommandApplication(args[0], outputLocation);
		ngpServerApplicaiton.execute();
	}

	@Override
	public void exit(int exitCode) {
		if(exitCode<0) System.exit(exitCode);
		System.out.println("Running");
	}

}
