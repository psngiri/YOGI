package yogi.report.group;

public class GroupEvaluatorAdapter<I, O> implements GroupEvaluator<I, O> {

	public O evaluate(Group<I> group, int indexInGroup) {
		return null;
	}

	public O evaluate(I object) {
		return null;
	}

}
