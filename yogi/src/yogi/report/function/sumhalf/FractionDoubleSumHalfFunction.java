package yogi.report.function.sumhalf;


import yogi.base.util.FractionDouble;
import yogi.report.function.sum.FractionDoubleSumFunction;

public class FractionDoubleSumHalfFunction extends FractionDoubleSumFunction {
	@Override
	public void process(FractionDouble object, int multiplier) {
		numerator =  numerator + object.getNumerator()*multiplier/2;
		denominator =  denominator + object.getDenominator()*multiplier;
	}


}
