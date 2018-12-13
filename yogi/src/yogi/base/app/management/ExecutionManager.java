package yogi.base.app.management;

import yogi.base.app.Executor;

public class ExecutionManager implements ExecutionManagerMBean {

	public void pause() {
		Executor.Pause = !Executor.Pause;
	}

	public void cancel() {
		Executor.Cancel = true;
	}

	public boolean getPause() {
		return Executor.Pause;
	}

	public boolean getCancel() {
		return Executor.Cancel;
	}
	
	public void writeSystemConfigFiles()
	{
		Executor.get().writeSystemConfigFiles();
	}
}
