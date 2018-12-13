package yogi.base.app;

;

public class AsynchronousModuleAdapter extends BaseAsynchronousModule {
	private Processor processor;
	public AsynchronousModuleAdapter(Processor processor) {
		super();
		this.processor = processor;
		
	}

	@Override
	public boolean isActivated() {
		return true;
	}

	@Override
	public void setup() {
		this.addProcessor(processor);
	}
}
		
