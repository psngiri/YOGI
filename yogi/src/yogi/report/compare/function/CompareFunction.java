package yogi.report.compare.function;

public interface CompareFunction<C> {
	String getName();
	Object compare(Object[] objects);
}
