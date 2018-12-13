package yogi.remote.client.app.test;

import java.util.logging.Level;

import yogi.base.app.ErrorReporter;
import yogi.remote.client.app.CommandAdapter;

public class TestCommand extends CommandAdapter<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1638181189543474056L;
	private int numberOfMessages;
	private Level level;
	
	public TestCommand(int numberOfMessages, Level level) {
		super("test");
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
	

}
