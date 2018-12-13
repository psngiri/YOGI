package yogi.optimize.app;

import yogi.base.app.BaseModule;
import yogi.optimize.sm.app.SmModule;

public class OptimizeModule extends BaseModule {
	public static boolean RUN = true;
	public static String IPSolutionFilePathFromMatrixLocation = "IPsolution";
	private String matrixLocation;

	public OptimizeModule(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addProcessor(new MpsOptimizeModule(matrixLocation));
		addProcessor(new SmModule(matrixLocation + "/" + IPSolutionFilePathFromMatrixLocation));
	}

}
