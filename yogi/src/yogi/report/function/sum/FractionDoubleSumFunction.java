package yogi.report.function.sum;


import yogi.base.util.FractionDouble;
import yogi.report.function.Function;

public class FractionDoubleSumFunction implements Function<FractionDouble> {
	protected double numerator;
	protected double denominator;
	
	

	@Override
	public void process(FractionDouble object, int multiplier) {
		numerator =  numerator + object.getNumerator()*multiplier;
		denominator =  denominator + object.getDenominator()*multiplier;
	}

	@Override
	public FractionDouble getValue() {

		return new FractionDouble(numerator,denominator);
	}
	
	@Override
	public void reset() {
		numerator = 0 ;
		denominator = 0;
		
	}

}
