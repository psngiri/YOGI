package yogi.remote.simple;

public class CommandException extends Exception {

	private static final long serialVersionUID = -5127449075022315021L;

	public CommandException() {
		super();
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}
}
