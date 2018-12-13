package yogi.base.app;

public abstract class GeneratorAdapter<R> implements Generator<R> {
	public boolean isGenerated() {
		return getGenerated() != null;
	}
	public abstract R getGenerated();
}
