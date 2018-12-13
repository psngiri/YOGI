package yogi.report.group;

import yogi.base.evaluator.Evaluator;

public class GroupEvaluatorWraper<I, O> implements GroupEvaluator<I, O> {
	Evaluator<I,O> evaluator;
	
	GroupEvaluatorWraper(Evaluator<I, O> evaluator) {
		super();
		this.evaluator = evaluator;
	}

	public O evaluate(Group<I> group, int indexInGroup) {
		return evaluator.evaluate(group.getItem(indexInGroup));
	}
	
	public static <I, O>GroupEvaluator<I,O> getGroupEvaluator(Evaluator<I,O> evaluator)
	{
		if(evaluator != null) return new GroupEvaluatorWraper<I, O>(evaluator);
		return null;
	}

	public O evaluate(I object) {
		return evaluator.evaluate(object);
	}
}
