package yogi.report.column.alias;

import java.util.ArrayList;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.report.column.alias.condition.AliasCondition;

public class AliasColumnEvaluator<I, C> implements Evaluator<I, String> {
	private List<AliasCondition<C>> aliasConditions = new ArrayList<AliasCondition<C>>();
	private Evaluator<I, C> columnEvaluator;
	
	
	public AliasColumnEvaluator(Evaluator<I, C> columnEvaluator) {
		super();
		this.columnEvaluator = columnEvaluator;
	}

	public void addAliasCondition(AliasCondition<C> aliasCondition)
	{
		aliasConditions.add(aliasCondition);
	}
	
	@Override
	public String evaluate(I object) {
		C value = columnEvaluator.evaluate(object);
		for(AliasCondition<C> aliasCondition: aliasConditions)
		{
			if(aliasCondition.getCondition().satisfied(value)) return aliasCondition.getAlias();
		}
		return "";
	}

	public void setAliasConditions(List<AliasCondition<C>> aliasConditions, Formatter<C> formatter) {
		this.aliasConditions = aliasConditions;
		for(AliasCondition<C> aliasCondition: aliasConditions)
		{
			aliasCondition.getCondition().setFormatter(formatter);
		}
	}

}
