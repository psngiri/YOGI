package yogi.optimize.expr.app;

import yogi.base.app.BaseModule;
import yogi.base.app.ManagerChecker;
import yogi.optimize.expr.ConstraintManager;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.io.MpsWriter;
import yogi.optimize.expr.slack.SlackVariableObject;
import yogi.optimize.expr.slack.variable.SlackVariableCreator;

public class MpsModule extends BaseModule {
	public static boolean RUN = true;
	private String matrixFilePath;

	
	public MpsModule(String matrixFilePath) {
		super();
		this.matrixFilePath = matrixFilePath;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		VariableAssistant.get().setVariableCreator(SlackVariableObject.class, new SlackVariableCreator());
		addRuntimeChecker(new ManagerChecker(ConstraintManager.get()));
		addWriter(new MpsWriter(matrixFilePath));
	}

}
