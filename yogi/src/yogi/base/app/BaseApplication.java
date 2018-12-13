package yogi.base.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import yogi.base.Util;
import yogi.base.app.management.ManagementAssistant;
import yogi.base.io.Reader;
import yogi.base.io.Writer;
import yogi.base.io.db.dump.DumpProperties;

public abstract class BaseApplication extends BaseAppModule implements Application {
	private List<Module> modules;

	protected BaseApplication(List<Reader<?>> readers, List<Module> modules, List<Writer> writers) {
		super(readers, writers);
		this.modules = modules;
		setupManagementMBeans();
	}

	protected void setupManagementMBeans() {
		ManagementAssistant.get().registerPropertyMBean();
		ManagementAssistant.get().registerExecutionManagerMBean();
	}

	protected BaseApplication() {
		this(new ArrayList<Reader<?>>(), new ArrayList<Module>(), new ArrayList<Writer>());
	}

	public List<Module> getModules()
	{
		return modules;
	}
	
	public void execute()
	{
		Executor.get().execute(this);
	}
	
	public BaseApplication addModule(Module module)
	{
		this.getModules().add(module);
		return this;
	}

	public BaseApplication addReader(Reader<?> reader)
	{
		this.getReaders().add(reader);
		return this;
	}
	
	public BaseApplication addWriter(Writer writer)
	{
		this.getWriters().add(writer);
		return this;
	}
	
	public BaseApplication addInitChecker(Checker checker)
	{
		this.getInitCheckers().add(checker);
		return this;
	}
	
	public BaseApplication addPropertyFile(String propertyFileName)
	{
		this.getPropertyFiles().add(propertyFileName);
		return this;
	}

	public void exit(int exitCode) {
		if(exitCode == 0 && DumpProperties.DeleteDumpOnSuccessfullExit)
		{
			File file = new File(ApplicationProperties.InputDataLocation + DumpProperties.getDumpLocation());
			Util.deleteFile(file);
			file = new File(ApplicationProperties.OutputLocation + DumpProperties.getDumpLocation());
			Util.deleteFile(file);
		}
	}
	
	public void start()
	{
		
	}
}
