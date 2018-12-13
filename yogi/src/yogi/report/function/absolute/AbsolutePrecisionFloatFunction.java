package yogi.report.function.absolute;

import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;

public class AbsolutePrecisionFloatFunction implements Function<PrecisionFloat> {

		private float value = 0.00f;
		private int precision = 0;

		@Override
		public PrecisionFloat getValue() {
			return new PrecisionFloat(value, precision);
		}

		@Override
		public void process(PrecisionFloat object, int multiplier) {
			if (object.getValue() < 0.00f) {
				value = Math.abs(object.getValue());
				precision = object.getDecimal();
			} else {
				value = object.getValue();
				precision = object.getDecimal();
			}
		}

		@Override
		public void reset() {
			value = 0.00f;
			precision = 0;
		}

	}