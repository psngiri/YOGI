package yogi.base.app;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.Reader;
import yogi.base.io.Writer;


public abstract class BaseLoopModule extends BaseModule {
	private List<Checker> loopCheckers;

	public BaseLoopModule() {
		super();
		this.loopCheckers = new ArrayList<Checker>();
	}

	public BaseLoopModule(List<Checker> checkers, List<Reader<?>> readers, List<Processor> processors, List<Writer> writers, List<Checker> loopCheckers) {
		super(checkers, readers, processors, writers);
		this.loopCheckers = loopCheckers;
	}

	@Override
	public void run() {
		do
		{
			super.run();
		}while(Executor.get().check(loopCheckers));
	}

	public List<Checker> getLoopCheckers() {
		return loopCheckers;
	}

	public BaseLoopModule addLoopChecker(Checker loopCchecker) {
		this.loopCheckers.add(loopCchecker);
		return this;
	}

}
