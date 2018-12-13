package yogi.report.function.max;

import yogi.base.util.FractionDouble;
import yogi.report.function.Function;

public class MaxFractionDoubleFunction implements Function<FractionDouble> {

		private FractionDouble maxValue = new FractionDouble(0,1);

		@Override
		public FractionDouble getValue() {
			return maxValue;
		}

		@Override
		public void process(FractionDouble object, int multiplier) {
			if(object == null) return;
			if (maxValue.getValue() == 0) {
				maxValue = object;
			} else if (object.getValue() > maxValue.getValue())
				maxValue = object;
		}

		@Override
		public void reset() {
			maxValue = new FractionDouble(0,1);

		}

	}