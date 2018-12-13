package yogi.base.app;

import java.util.List;

import yogi.base.io.Reader;
import yogi.base.io.Writer;

public interface Application extends ActivationState{
	void setup();
	List<String> getPropertyFiles();
	List<Checker> getInitCheckers();
	List<Reader<?>> getReaders();
	List<Module> getModules();
	List<Writer> getWriters();
	void exit(int exitCode);
	void start();
}
