package yogi.filecommand.app;

import java.io.File;

import yogi.base.app.Executor;

public class FileCommandProcessor extends BaseFileProcessor {

	@Override
	protected void executeFile(File file) {
		FileCommandExecutor executor = new FileCommandExecutor(file);
		Executor.get().execute(executor);
	}

}
