package yogi.optimize.remote.app;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Processor;
import yogi.optimize.remote.client.CplexJavaConnectException;
import yogi.optimize.remote.client.CplextJavaConnectClient;

public class RemoteOptimizeProcessor  implements Processor {

	public static boolean RUN = true;
	private String matrixLocation;
	
	public RemoteOptimizeProcessor(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}
		
	public boolean isActivated() {
		return RUN;
	}

	public void run() {
		CplextJavaConnectClient client = new CplextJavaConnectClient();
		try {
			client.createIPSolution(matrixLocation + "/" + "matrix.mps" );
		} catch (CplexJavaConnectException e) {
			ErrorReporter.get().error("Error in RemoteOptimizeProcessor", e, e);
		}
	}
}
