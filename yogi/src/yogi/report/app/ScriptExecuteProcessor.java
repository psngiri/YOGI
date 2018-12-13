package yogi.report.app;

import yogi.base.app.RemoteCommandProcessor;
import yogi.property.PropertyReplacer;

public class ScriptExecuteProcessor extends RemoteCommandProcessor {
	public static boolean SendFtpDoneFile = false;
	public static boolean RUN = true;
	public static String ScriptDir = "";
	public static  String Extension = ".sh";
	public String scriptName;
	public String localFileName;
	public String remoteFileName;
	private static PropertyReplacer propertyReplacer = new PropertyReplacer();

	public ScriptExecuteProcessor(String scriptName,String localFileName, String remoteFileName) {
		super();
		this.scriptName = scriptName;
		this.localFileName = localFileName;
		this.remoteFileName = remoteFileName;
	}

	@Override
	public void run() {
		this.setCommand(propertyReplacer.replaceVariables(ScriptDir)+"/"+scriptName+Extension, localFileName, remoteFileName);
		super.run();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
