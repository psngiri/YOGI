package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleIsNotBlankCondition extends ConditionBaseImpl<FractionDouble> {
	
	public FractionDoubleIsNotBlankCondition(String value) {
		super(value);
	}
	
	public boolean satisfied(FractionDouble data) {
		if(data==FractionDouble.BLANK)
			return false;
		return true;
	}

}
