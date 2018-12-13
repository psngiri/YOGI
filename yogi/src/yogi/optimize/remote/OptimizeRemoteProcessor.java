package yogi.optimize.remote;

import java.io.IOException;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseProcessor;
import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.io.remote.RemoteReaderImpl;
import yogi.base.io.resource.FileResource;
import yogi.remote.client.app.CommandClientProcessor;

public class OptimizeRemoteProcessor extends BaseProcessor{
	public static boolean RUN = false;
	public static String commandServerAddressesColonPortNumbers = "Q0513984.corpaa.aa.com";
	private String matrixLocation;

	public OptimizeRemoteProcessor(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}

	public void run() {
		try {
			CommandClientProcessor<OptimizerOutput> commandClientProcessor = new CommandClientProcessor<OptimizerOutput>();
			commandClientProcessor.getCommandClient().addCommandServerAddressesColonPortNumbers(commandServerAddressesColonPortNumbers);
			FileResource fileResource = new FileResource(matrixLocation + "/matrix.mps");
			RemoteReaderImpl matrixReader = new RemoteReaderImpl(fileResource);
			OptimizeCommand command = new OptimizeCommand(matrixReader, ApplicationProperties.EmployeeID, ApplicationProperties.TaskID);
			commandClientProcessor.setCommand(command);
			Executor.get().execute(commandClientProcessor);
			OptimizerOutput optimizerOutput = commandClientProcessor.getCommandResult();
			optimizerOutput.write(matrixLocation);
		} catch (IOException e) {
			ErrorReporter.get().error((Object) e.getMessage(), e);
		}
	}

	public boolean isActivated() {
		return RUN;
	}
}
