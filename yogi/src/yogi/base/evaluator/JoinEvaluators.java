package yogi.base.evaluator;

public class JoinEvaluators<I, M, O> implements Evaluator<I, O>{
	Evaluator<I, M> evaluator1;
	Evaluator<M, O> evaluator2;
	
	public JoinEvaluators(Evaluator<I, M> evaluator1, Evaluator<M, O> evaluator2) {
		super();
		this.evaluator1 = evaluator1;
		this.evaluator2 = evaluator2;
	}

	public O evaluate(I object) {
		return evaluator2.evaluate(evaluator1.evaluate(object));
	}
}
