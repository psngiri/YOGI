package yogi.base.app;

public interface Generator<R> extends Processor {
	boolean isGenerated();
	R getGenerated();
}
