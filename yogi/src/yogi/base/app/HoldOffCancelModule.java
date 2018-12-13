package yogi.base.app;

public abstract class HoldOffCancelModule extends BaseModule {

	@Override
	public void run() {
		Executor.HoldOffCancel = true;
		super.run();
		Executor.HoldOffCancel = false;
	}


}
