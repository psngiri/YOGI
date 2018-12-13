package yogi.base.app.management;

public interface ExecutionManagerMBean {
	boolean getPause();
	boolean getCancel();
	void pause();
	void cancel();
	void writeSystemConfigFiles();
}
