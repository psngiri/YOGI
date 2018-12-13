package yogi.base.app.multithread;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.Checker;
import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.app.Module;
import yogi.base.app.Processor;
import yogi.base.io.Reader;
import yogi.base.io.Writer;

public class MultiThreadModule extends MultiThreadExecutor<Module> implements Module{

	@Override
	public void setup() {
         for(Module module : getModules()){
        	 Executor.get().setup(module);
         }
	}	
	
	public List<Module> getModules() {
		return getUnits();
	}

	public void addModule(Module module)
	{
		addUnit(module);
	}
	
	public void run() {
		super.run();
	}
	
	static class ModuleTask extends BaseTask
	{
		Module module;

		public ModuleTask(Module module, ErrorReporter errorReporter) {
			super(errorReporter);
			this.module = module;
		}

		public void run() {
			super.run();
			try {
				module.run();
			} catch (Throwable e) {
				errorReporter.error("Error in MultiThreadModule", e.getMessage(), e);
			}
		}
		
	}

	@Override
	public void execute(Module module) {
		module.run();
	}

	@Override
	public Task getTask(Module module) {
		return new ModuleTask(module, ErrorReporter.get());
	}
	
	public List<Checker> getInitCheckers() {
		return new ArrayList<Checker>();
	}

	public List<Processor> getProcessors() {
		return new ArrayList<Processor>();
	}

	public List<String> getPropertyFiles() {
		return new ArrayList<String>();
	}

	public List<Reader<?>> getReaders() {
		return new ArrayList<Reader<?>>();
	}

	public List<Checker> getRuntimeCheckers() {
		return new ArrayList<Checker>();
	}

	public List<Writer> getWriters() {
		return new ArrayList<Writer>();
	}

}
