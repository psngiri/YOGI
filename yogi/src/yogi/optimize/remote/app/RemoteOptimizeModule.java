package yogi.optimize.remote.app;

import yogi.base.app.BaseModule;
import yogi.optimize.sm.app.SmModule;

public class RemoteOptimizeModule  extends BaseModule {
	public static boolean RUN = true;
	public static String IPSolutionFilePathFromMatrixLocation = "IPsolution";
	private String matrixLocation;

	public RemoteOptimizeModule(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addProcessor(new RemoteMpsOptimizeModule(matrixLocation));
		addProcessor(new SmModule(matrixLocation + "/" + IPSolutionFilePathFromMatrixLocation));
	}

}
