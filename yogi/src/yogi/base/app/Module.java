package yogi.base.app;

import java.util.List;

import yogi.base.io.Reader;
import yogi.base.io.Writer;

public interface Module extends Processor{
	void setup();
	List<String> getPropertyFiles();
	List<Checker> getInitCheckers();
	List<Checker> getRuntimeCheckers();
	List<Reader<?>> getReaders();
	List<Processor> getProcessors();
	List<Writer> getWriters();
}
