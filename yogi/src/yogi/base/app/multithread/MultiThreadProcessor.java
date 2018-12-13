package yogi.base.app.multithread;

import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.app.Module;
import yogi.base.app.Processor;

public class MultiThreadProcessor extends MultiThreadExecutor<Processor> implements Processor {

	public List<Processor> getProcessors() {
		return getUnits();
	}

	public void addProcessor(Processor processor)
	{
		addUnit(processor);
	}
	/**
	 * "Modules are not supposed to be added to a Multi-Thread Processor please use Multi-Thread Module for this purpose"
	 * @param module
	 */
	public void addProcessor(Module module)
	{
		throw new RuntimeException("Modules are not supposed to be added to a Multi-Thread Processor please use Multi-Thread Module for this purpose");
	}
	
	public void run() {
		super.run();
	}
	
	static class ProcessorTask extends BaseTask
	{
		Processor processor;

		public ProcessorTask(Processor processor, ErrorReporter errorReporter) {
			super(errorReporter);
			this.processor = processor;
		}

		public void run() {
			super.run();
			try {
				Executor.get().execute(processor);
			} catch (Throwable e) {
				errorReporter.error("Error in MultiThreadProcessor", e.getMessage(), e);
			}
		}
		
	}

	@Override
	public Task getTask(Processor processor) {
		return new ProcessorTask(processor, ErrorReporter.get());
	}

	@Override
	public void execute(Processor processor) {
		Executor.get().execute(processor);
	}
}
