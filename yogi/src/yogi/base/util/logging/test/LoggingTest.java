package yogi.base.util.logging.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.util.logging.Logging;

import junit.framework.TestCase;

public class LoggingTest extends TestCase {

	public void testSetLevel()
	{
		Logging.setLevel("yogi.core.emogt.EmogtCalculator=SEVERE");
		assertEquals(Level.SEVERE, Logger.getLogger("yogi.core.emogt.EmogtCalculator").getLevel());
	}
}
