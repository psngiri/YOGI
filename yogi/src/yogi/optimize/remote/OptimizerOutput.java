package yogi.optimize.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import yogi.base.Util;
import yogi.base.app.ErrorReporter;
import yogi.base.io.IoConnector;
import yogi.base.io.remote.RemoteReader;
import yogi.base.io.remote.RemoteReaderImpl;
import yogi.base.io.remote.RemoteReaderWraper;
import yogi.base.io.resource.FileResource;
import yogi.optimize.app.OptimizeModule;
import yogi.optimize.app.OptimizeProcessor;

public class OptimizerOutput implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7654523718357726846L;
	private RemoteReader ipSolution;
	private RemoteReader cplexLog;
	private RemoteReader optimizerLog;
	public OptimizerOutput(String matrixLocation) throws IOException
	{
		FileResource fileResource = new FileResource(matrixLocation + "/" + OptimizeProcessor.IpSolutionFileName);
		ipSolution = new RemoteReaderImpl(fileResource);
		fileResource = new FileResource(matrixLocation + "/" + OptimizeProcessor.CplexLogFileName);
		cplexLog = new RemoteReaderImpl(fileResource);
		fileResource = new FileResource(matrixLocation + "/" + OptimizeProcessor.OptimizerLogFileName);
		optimizerLog = new RemoteReaderImpl(fileResource);
	}
	
	public void write(String matrixLocation) throws IOException
	{
		File file = new File(matrixLocation + "/" + OptimizeModule.IPSolutionFilePathFromMatrixLocation);
		writeFile(file, ipSolution);
		file = new File(file.getParentFile(), OptimizeProcessor.CplexLogFileName);
		writeFile(file, cplexLog);
		file = new File(file.getParentFile(), OptimizeProcessor.OptimizerLogFileName);
		writeFile(file, optimizerLog);
	}
	
	private void writeFile(File file, RemoteReader reader) throws IOException {
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(file);
		FileWriter fileWriter = new FileWriter(file);
		ErrorReporter.get().info(
				"Writing to Resource - " + file.getAbsolutePath());
		IoConnector ioConnector = new IoConnector(new BufferedReader(new RemoteReaderWraper(reader)), fileWriter);
		ioConnector.run();
	}
}
