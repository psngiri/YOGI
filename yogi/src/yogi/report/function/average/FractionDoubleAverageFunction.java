package yogi.report.function.average;

import yogi.base.util.FractionDouble;
import yogi.report.function.Function;

public class FractionDoubleAverageFunction implements Function<FractionDouble> {
	private double sum;
	private int count;
	public void reset() {
		sum = 0;
		count = 0;
	}

	public void process(FractionDouble object, int multiplier) {
		if(object == null) return;
		sum = sum + object.getValue()*multiplier;
		count = count + multiplier;
	}

	public FractionDouble getValue() {
		return new FractionDouble (sum/count,1);
	}

}
