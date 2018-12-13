package yogi.csi;

public interface CSIApplicaitonStatusUpdater {

	public abstract void start();

	public abstract void exit(int exitCode);

	public abstract void updateStatus(String status);

}