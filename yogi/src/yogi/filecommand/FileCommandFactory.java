package yogi.filecommand;

import yogi.base.Factory;

public class FileCommandFactory extends Factory<FileCommand> {
	private static FileCommandFactory itsInstance = new FileCommandFactory(FileCommandManager.get());

	protected FileCommandFactory(FileCommandManager manager) {
		super(manager);
	}

	public static FileCommandFactory get() {
		return itsInstance;
	}
}
