package yogi.filecommand;

import yogi.base.relationship.RelationshipObjectImpl;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandImpl extends RelationshipObjectImpl<FileCommand> implements FileCommand {
	
	private String fileName;
	private String command;
	private String arguments;
	private boolean isRegularExpression;
	
	protected FileCommandImpl(String fileName, String command, String arguments, boolean isRegularExpression) {
		super();
		this.fileName = fileName;
		this.command = command;
		this.arguments = arguments;
		this.isRegularExpression = isRegularExpression;
	}

	public String getName() {
		return fileName+"/"+command;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public String getFileName() {
		return fileName;
	}

	public String getCommand() {
		return command;
	}

	public String getArguments() {
		return arguments;
	}

	public boolean isRegularExpression() {
		return isRegularExpression;
	}

	@Override
	public String getIndex() {
		return getFileName();
	}

}
