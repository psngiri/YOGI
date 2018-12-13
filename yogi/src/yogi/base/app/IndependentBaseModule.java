package yogi.base.app;

public abstract class IndependentBaseModule extends BaseModule {

	@Override
	public void run() {
		setup();
		super.run();
	}

}
