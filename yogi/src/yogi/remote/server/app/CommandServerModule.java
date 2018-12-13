package yogi.remote.server.app;

import yogi.base.app.BaseModule;

public class CommandServerModule extends BaseModule {

	public void setup() {
		this.addProcessor(new CommandServerProcessor());
	}

}
