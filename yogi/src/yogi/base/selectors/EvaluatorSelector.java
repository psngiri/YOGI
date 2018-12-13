package yogi.base.selectors;

import yogi.base.Selector;
import yogi.base.evaluator.Evaluator;

public class EvaluatorSelector<I, O> implements Selector<I>{
	private Evaluator<I, O> evaluator;
	private Selector<O> selector;
	
	public EvaluatorSelector(Evaluator<I, O> evaluator, Selector<O> selector) {
		super();
		this.evaluator = evaluator;
		this.selector = selector;
	}

	public boolean select(I item) {
		if(item == null) return false;
		return selector.select(evaluator.evaluate(item));
	}

}
