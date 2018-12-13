package yogi.base.stats;

import java.util.logging.Level;

public interface Collector{

	String getMessage();

	Collector setMessage(String message);

	Level getLevel();

	void setLevel(Level level);

	Collector start();

	Collector stop();
}