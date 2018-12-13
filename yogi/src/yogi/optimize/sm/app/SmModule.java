package yogi.optimize.sm.app;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseModule;
import yogi.base.app.ManagerChecker;
import yogi.optimize.expr.AssignedVariableObjectSelector;
import yogi.optimize.expr.VariableManager;
import yogi.optimize.expr.slack.io.SlackVariableObjectWriter;
import yogi.optimize.sm.io.SmReader;

public class SmModule extends BaseModule {
	public static boolean RUN = true;
	private String ipSolutionFilePath;

	
	public SmModule(String ipSolutionFilePath) {
		super();
		this.ipSolutionFilePath = ipSolutionFilePath;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addRuntimeChecker(new ManagerChecker(VariableManager.get()));
		
		addReader(new SmReader(ipSolutionFilePath));
		addWriter(new SlackVariableObjectWriter(ApplicationProperties.OutputLocation+"/SlackVariables.txt", new AssignedVariableObjectSelector()));
	}

}
