package yogi.base.evaluator;

public interface Evaluator<I, O> {
	O evaluate(I object);
}
