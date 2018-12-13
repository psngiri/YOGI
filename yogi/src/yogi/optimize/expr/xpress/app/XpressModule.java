package yogi.optimize.expr.xpress.app;

import yogi.base.app.BaseModule;
import yogi.optimize.expr.xpress.bp.XpressOptimizer;

public class XpressModule extends BaseModule {
	public static boolean RUN = true;
	private String name;
	
	public XpressModule(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		this.addProcessor(new XpressOptimizer(name));
	}

}
