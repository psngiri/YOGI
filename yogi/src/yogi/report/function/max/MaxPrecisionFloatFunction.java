package yogi.report.function.max;

import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;

public class MaxPrecisionFloatFunction implements Function<PrecisionFloat> {

		private float maxValue = 0.00f;
		private int precision = 0;

		@Override
		public PrecisionFloat getValue() {
			return new PrecisionFloat(maxValue, precision);
		}

		@Override
		public void process(PrecisionFloat object, int multiplier) {
			if(object == null) return;
			if (maxValue == 0.00f) {
				maxValue = object.getValue();
				precision = object.getDecimal();
			} else if (object.getValue() > maxValue) {
				maxValue = object.getValue();
				precision = object.getDecimal();
			}
		}

		@Override
		public void reset() {
			maxValue = 0.00f;
			precision = 0;
		}

	}