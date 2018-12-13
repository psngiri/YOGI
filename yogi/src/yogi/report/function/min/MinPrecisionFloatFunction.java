package yogi.report.function.min;

import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;

public class MinPrecisionFloatFunction implements Function<PrecisionFloat> {

		private float minValue = 0.00f;
		private int precision = 0;

		@Override
		public PrecisionFloat getValue() {
			return new PrecisionFloat(minValue, precision);
		}

		@Override
		public void process(PrecisionFloat object, int multiplier) {
			if (minValue == 0.00f) {
				minValue = object.getValue();
				precision = object.getDecimal();
			} else if (object.getValue() < minValue) {
				minValue = object.getValue();
				precision = object.getDecimal();
			}						
		}

		@Override
		public void reset() {
			minValue = 0.00f;
			precision = 0;
		}

	}