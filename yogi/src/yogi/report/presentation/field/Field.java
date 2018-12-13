package yogi.report.presentation.field;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.base.relationship.RelationshipObject;
import yogi.report.function.Function;

public interface Field<I, O> extends RelationshipObject{
	Evaluator<I,O> getEvaluator();
	int getWidth();
	Function<O> getFunction();
	Formatter<? super O> getFormatter();
}
