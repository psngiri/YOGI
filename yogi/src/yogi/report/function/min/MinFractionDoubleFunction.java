package yogi.report.function.min;

import yogi.base.util.FractionDouble;
import yogi.report.function.Function;

public class MinFractionDoubleFunction implements Function<FractionDouble> {

		private FractionDouble minValue = new FractionDouble(0,1);

		@Override
		public FractionDouble getValue() {
			return minValue;
		}

		@Override
		public void process(FractionDouble object, int multiplier) {
			if(object == null) return;
			if (minValue.getValue() == 0) {
				minValue = object;
			} else if (object.getValue() < minValue.getValue())
				minValue = object;
		}

		@Override
		public void reset() {
			minValue = new FractionDouble(0,1);

		}

	}