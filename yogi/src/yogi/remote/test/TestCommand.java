package yogi.remote.test;

import java.util.logging.Level;

import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;

public class TestCommand implements Command<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6983426677596434214L;
	private String id;
	private int numberOfMessages;
	private Level level;
	
	public TestCommand(int numberOfMessages, Level level, String id) {
		super();
		this.id = id;
		this.numberOfMessages = numberOfMessages;
		this.level = level;
	}

	public Integer execute() {
		for(int i = 0; i < numberOfMessages; i ++)
		{
			ErrorReporter.get().log(level, "Message " + i);
			try {
				Thread.sleep(10*i);
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return numberOfMessages;
	}

	public String getID() {
		return id;
	}

	@Override
	public String getUserId() {
		return null;
	}

}
