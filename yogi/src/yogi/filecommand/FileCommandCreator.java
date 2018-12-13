package yogi.filecommand;

import yogi.base.Creator;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandCreator implements Creator<FileCommand> {
	
	private String fileName;
	private String command;
	private String arguments;
	private boolean isRegularExpression;
	
	public FileCommand create() {
		return new FileCommandImpl(fileName, command, arguments, isRegularExpression);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public boolean isRegularExpression() {
		return isRegularExpression;
	}

	public void setIsRegularExpression(boolean isRegularExpression) {
		this.isRegularExpression = isRegularExpression;
	}
}
