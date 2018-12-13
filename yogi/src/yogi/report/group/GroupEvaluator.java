package yogi.report.group;

import yogi.base.evaluator.Evaluator;

public interface GroupEvaluator<I, O> extends Evaluator<I, O>{
	O evaluate(Group<I> group, int indexInGroup);
}
