package yogi.optimize.app;

import java.util.logging.Logger;

import yogi.base.app.CommandProcessor;
import yogi.base.io.resource.FileResource;
import yogi.base.util.FileToErrorReporter;
import yogi.base.util.logging.Logging;

public class OptimizeProcessor extends CommandProcessor {
	private Logger logger = Logging.getLogger(OptimizeProcessor.class);
	public static boolean RUN = true;
	public static String OptimizerCommand = "xxx";
	public static boolean SupplyOptimizationParameters = false;
	public static double IntegralityGap = 0.005;
	public static int NumberOfIntegerSolutionsToSearch = 8;
	public static int NumberOfThreads = 4;
	public static int maxTime = 0;
	public static String SolutionAlgorithm = "d:dual";
	public static String IpSolutionFileName = "IPsolution";
	public static String CplexLogFileName = "cplex.log";
	public static String OptimizerLogFileName = "Optimizer.log";
	private String matrixLocation;

	public OptimizeProcessor(String matrixLocation) {
		super();
		this.matrixLocation = matrixLocation;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	public void run() {
		logger.info("Starting Optimization");
		if(SupplyOptimizationParameters)
		{
			this.setCommand(String.format("%1$s %2$s %3$s %4$s %5$s %6$s %7$s", OptimizerCommand, matrixLocation, IntegralityGap, NumberOfIntegerSolutionsToSearch, NumberOfThreads, SolutionAlgorithm, maxTime));
		}else
		{
			this.setCommand(String.format("%1$s %2$s", OptimizerCommand, matrixLocation));
		}
		FileToErrorReporter fileToErrorReporterForCplexLog = null;
		FileToErrorReporter fileToErrorReporterForOptimizerLog = null;
		fileToErrorReporterForCplexLog = new FileToErrorReporter(new FileResource(matrixLocation + "/" + CplexLogFileName));
		fileToErrorReporterForCplexLog.setLogger(logger);
		fileToErrorReporterForCplexLog.start();
		fileToErrorReporterForOptimizerLog = new FileToErrorReporter(new FileResource(matrixLocation + "/" + OptimizerLogFileName));
		fileToErrorReporterForOptimizerLog.setLogger(logger);
		fileToErrorReporterForOptimizerLog.start();
		
		try {
			super.run();
		} catch (RuntimeException e) {
			throw e;
		}finally{
			if(fileToErrorReporterForCplexLog != null) fileToErrorReporterForCplexLog.flush();
			if(fileToErrorReporterForOptimizerLog != null) fileToErrorReporterForOptimizerLog.flush();
		}
	}
}
