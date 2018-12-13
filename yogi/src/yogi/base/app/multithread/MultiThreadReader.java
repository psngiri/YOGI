package yogi.base.app.multithread;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.io.Reader;

public class MultiThreadReader extends MultiThreadExecutor<Reader<?>> implements Reader<Object> {

	public List<Reader<?>> getReaders() {
		return getUnits();
	}

	public void addReader(Reader<?> reader)
	{
		addUnit(reader);
	}
	
	public List<Object> read() {
		super.run();
		return new ArrayList<Object>(0);
	}
	
	static class ReaderTask extends BaseTask
	{
		Reader<?> reader;

		public ReaderTask(Reader<?> reader, ErrorReporter errorReporter) {
			super(errorReporter);
			this.reader = reader;
		}

		public void run() {
			super.run();
			try {
				Executor.get().execute(reader);
			} catch (Throwable e) {
				errorReporter.error("Error in MultiThreadReader", e.getMessage(), e);
			}
		}
		
	}

	@Override
	public Task getTask(Reader<?> reader) {
		return new ReaderTask(reader, ErrorReporter.get());
	}

	@Override
	public void execute(Reader<?> reader) {
		Executor.get().execute(reader);
	}

}
