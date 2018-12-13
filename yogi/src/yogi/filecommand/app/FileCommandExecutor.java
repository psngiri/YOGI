package yogi.filecommand.app;

import java.io.File;

import yogi.base.app.CommandProcessor;
import yogi.base.util.VariableReplacer;
import yogi.filecommand.FileCommand;
import yogi.filecommand.FileCommandManager;
/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandExecutor extends CommandProcessor {
	private static String SPACE = " ";
	private File file;
	private Replacer replacer;
	
	public FileCommandExecutor(File file) {
		super();	
		this.file =  file;
		replacer = new Replacer(file);
	}

	@Override
	public void run() {
		for (FileCommand fileCommand : FileCommandManager.get().getFileCommands(file.getName())) {
			String command = getCommand(fileCommand);
			setCommand(command);
			super.run();
		}
	}

	private String getCommand(FileCommand fileCommand) {
		String command = fileCommand.getCommand() + SPACE + replacer.replaceVariables(fileCommand.getArguments());
		return command;
	}

	static class Replacer extends VariableReplacer{
		private File file;

		protected Replacer(File file) {
			super();
			this.file = file;
		}

		@Override
		protected String getValue(String variableName) {
			if(variableName.equalsIgnoreCase("fileName"))
				return file.getAbsolutePath();
			else{
				return "";
			}
		}
		
	}
}
