package yogi.optimize.remote.app;

import yogi.base.app.BaseModule;
import yogi.optimize.expr.app.MpsModule;

public class RemoteMpsOptimizeModule extends BaseModule {
	public static boolean RUN = true;
	private String matrixLocation;

	public RemoteMpsOptimizeModule(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addProcessor(new MpsModule(matrixLocation + "/matrix.mps"));
		addProcessor(new RemoteOptimizeProcessor(matrixLocation));
	}

}

