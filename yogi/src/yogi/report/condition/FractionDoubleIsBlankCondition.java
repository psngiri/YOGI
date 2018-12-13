package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleIsBlankCondition extends ConditionBaseImpl<FractionDouble> {
	
	public FractionDoubleIsBlankCondition(String value) {
		super(value);
	}
	
	public boolean satisfied(FractionDouble data) {
		if(data==FractionDouble.BLANK)
			return true;
		return false;
	}

}