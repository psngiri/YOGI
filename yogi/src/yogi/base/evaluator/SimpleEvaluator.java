package yogi.base.evaluator;

public class SimpleEvaluator<I> implements Evaluator<I, I> {

	@Override
	public I evaluate(I object) {
		return object;
	}

}
