package yogi.base.app.testing;

public abstract class CSIApplicationAcceptenceTestCase extends ApplicationAcceptenceTestCase {
	protected CSIApplicationAcceptenceTestCase(String workset) {
		super(workset);
	}

	@Override
	public int executeApplication(String[] args) {
		String[] csiArgs = new String[4];
		csiArgs[0] = args[0];
		csiArgs[3] = args[1];
		return executCSIApplication(csiArgs);
	}

	protected abstract int executCSIApplication(String[] args);
}
