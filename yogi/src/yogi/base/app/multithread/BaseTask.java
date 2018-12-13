package yogi.base.app.multithread;

import yogi.base.app.ErrorReporter;
import yogi.base.app.ErrorReporterExtention;

public class BaseTask implements Task {
	protected ErrorReporter errorReporter;
	
	public BaseTask(ErrorReporter errorReporter) {
		super();
		this.errorReporter = errorReporter;
	}

	public ErrorReporter getErrorReporter() {
		return errorReporter;
	}

	public void run() {
		ErrorReporterExtention.push(errorReporter);
	}

}
