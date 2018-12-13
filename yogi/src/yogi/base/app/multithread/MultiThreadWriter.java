package yogi.base.app.multithread;

import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.io.Writer;

public class MultiThreadWriter extends MultiThreadExecutor<Writer> implements Writer {

	public List<Writer> getWriters() {
		return getUnits();
	}

	public void addWriter(Writer writer)
	{
		addUnit(writer);
	}
	
	public void write() {
		super.run();
	}
	
	static class WriterTask extends BaseTask
	{
		Writer writer;

		public WriterTask(Writer writer, ErrorReporter errorReporter) {
			super(errorReporter);
			this.writer = writer;
		}

		public void run() {
			super.run();
			try {
				Executor.get().execute(writer);
			} catch (Throwable e) {
				errorReporter.error("Error in MultiThreadWriter", e.getMessage(), e);
			}
		}
		
	}

	@Override
	public Task getTask(Writer writer) {
		return new WriterTask(writer, ErrorReporter.get());
	}

	@Override
	public void execute(Writer writer) {
		Executor.get().execute(writer);
	}

}
