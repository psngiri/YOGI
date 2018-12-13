package yogi.base.app;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.IoConnector;
import yogi.base.util.logging.Logging;

public class CommandProcessor extends BaseProcessor {
	private static Logger logger = Logging.getLogger(CommandProcessor.class);
	private ProcessBuilder processBuilder;
	private Process process;
	private boolean waitForProcessToComplete = true;
	private Writer errorWriter;
	private Writer outputWriter;
	public static boolean nonJavaErrorCode=false;
	
	public CommandProcessor(String command) {
		this();
		setCommand(command);
	}
	
	public CommandProcessor() {
		super();
		processBuilder = new ProcessBuilder();
		this.setErrorWriter(System.err);
		this.setOutputWriter(System.out);
	}

	private List<String> getTokens(String command) {
		Scanner scanner = new Scanner(command);
		List<String> tokens = new ArrayList<String>();
		while(scanner.hasNext())
		{
			tokens.add(scanner.next());
		}
		return tokens;
	}

	public Writer getErrorWriter() {
		return errorWriter;
	}

	public Writer getOutputWriter() {
		return outputWriter;
	}

	public void setErrorWriter(OutputStream errorStream) {
		this.errorWriter = new PrintWriter(errorStream);
	}

	public void setOutputWriter(OutputStream outputStream) {
		this.outputWriter = new PrintWriter(outputStream);
	}

	public void setErrorWriter(Writer errorWriter) {
		this.errorWriter = errorWriter;
	}

	public void setOutputWriter(Writer outputWriter) {
		this.outputWriter = outputWriter;
	}

	public boolean isWaitForProcessToComplete() {
		return waitForProcessToComplete;
	}

	public void setWaitForProcessToComplete(boolean waitForProcessToComplete) {
		this.waitForProcessToComplete = waitForProcessToComplete;
	}

	public CommandProcessor setCommand(String command)
	{
		processBuilder.command(getTokens(command));
		return this;
	}
	
	public CommandProcessor setCommand(String... tokens)
	{
		processBuilder.command(tokens);
		return this;
	}
	
	public ProcessBuilder getProcessBuilder() {
		return processBuilder;
	}


	public void setProcessBuilder(ProcessBuilder processBuilder) {
		this.processBuilder = processBuilder;
	}


	public Process getProcess() {
		return process;
	}

	public boolean isActivated() {
		return true;
	}

	public void run() {
		try {
			logger.info("Executing command: " + getCommand());
			if(logger.isLoggable(Level.FINE))logger.fine(logEnvironment());
			process = processBuilder.start();
			IoConnector errorStreamConnector = new IoConnector(new InputStreamReader(process.getErrorStream()), getErrorWriter());
			IoConnector outputStreamConnector = new IoConnector(new InputStreamReader(process.getInputStream()), getOutputWriter());
			errorStreamConnector.setCloseWriterOnCompletion(false);
			outputStreamConnector.setCloseWriterOnCompletion(false);
			errorStreamConnector.start();
			outputStreamConnector.start();
			if(waitForProcessToComplete)
			{
				logger.info("Waiting for the process to complete");
				int exitStatus = process.waitFor();
				if(isError(exitStatus)) {
					throw new RuntimeException(getCommand() + " exited with error status: " + exitStatus);
				}
				logger.info("Completed with exit status: " + exitStatus);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected boolean isError(int exitStatus) {
		if(nonJavaErrorCode) return exitStatus != 0;
		return exitStatus < 0;
	}
	
	private String logEnvironment() {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		printWriter.println("EnvironMent:");
		Map<String, String> environment = getProcessBuilder().environment();
		for(Map.Entry<String, String> entry: environment.entrySet())
		{
			printWriter.println("Key:" + entry.getKey() + " - Value:" + entry.getValue());
		}
		return stringWriter.toString();
	}

	public String getCommand()
	{
		StringBuilder sb = new StringBuilder();
		for(String item: processBuilder.command())
		{
			sb.append(item);
			sb.append(' ');
		}
		return sb.toString();
	}
}
