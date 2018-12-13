package yogi.optimize.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import yogi.base.Util;
import yogi.base.app.ApplicationProperties;
import yogi.base.app.Command;
import yogi.base.app.Executor;
import yogi.base.io.IoConnector;
import yogi.base.io.remote.RemoteReader;
import yogi.base.io.remote.RemoteReaderWraper;
import yogi.optimize.app.OptimizeProcessor;

public class OptimizeCommand implements Command<OptimizerOutput> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6983426677596434213L;
	private RemoteReader matrix;
	private String userID;
	private String id;
	
	public OptimizeCommand(RemoteReader matrix, String userID, String id) {
		super();
		this.matrix = matrix;
		this.userID = userID;
		this.id = id;
	}

	public OptimizerOutput execute() {
		try {
			String matrixLocation = ApplicationProperties.OutputLocation + "/" + userID + "/" + id ;
			File file = new File(matrixLocation + "/matrix.mps");
			Util.checkAndCreateFileAlongWithParentDirsAsRequired(file);
			FileWriter fileWriter = new FileWriter(file);
			IoConnector ioConnector = new IoConnector(new BufferedReader(new RemoteReaderWraper(matrix)), fileWriter);
			ioConnector.run();
			Executor.get().execute(new OptimizeProcessor(matrixLocation));
			return new OptimizerOutput(matrixLocation);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public String getID() {
		return id;
	}

	@Override
	public String getUserId() {
		return userID;
	}

}
