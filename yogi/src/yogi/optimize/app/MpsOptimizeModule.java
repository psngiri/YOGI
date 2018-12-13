package yogi.optimize.app;

import yogi.base.app.BaseModule;
import yogi.optimize.expr.app.MpsModule;
import yogi.optimize.remote.OptimizeRemoteProcessor;

public class MpsOptimizeModule extends BaseModule {
	public static boolean RUN = true;
	private String matrixLocation;

	public MpsOptimizeModule(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addProcessor(new MpsModule(matrixLocation + "/matrix.mps"));
		addProcessor(new OptimizeProcessor(matrixLocation));
		addProcessor(new OptimizeRemoteProcessor(matrixLocation));
	}

}
