package yogi.filecommand.app;

import yogi.base.app.BaseLoopModule;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandExecutorLoopModule extends BaseLoopModule{

	
	public FileCommandExecutorLoopModule() {
		super();
	}

	@Override
	public void setup() {
		this.addProcessor(new FileCommandExecutorListenerProcessor());
		this.addLoopChecker(new FileCommandExecutorLoopChecker());
	}

}
