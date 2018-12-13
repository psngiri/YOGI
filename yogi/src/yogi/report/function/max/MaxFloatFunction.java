package yogi.report.function.max;

import yogi.report.function.Function;

public class MaxFloatFunction implements Function<Float> {

		private float maxValue = 0;

		@Override
		public Float getValue() {
			return maxValue;
		}

		@Override
		public void process(Float object, int multiplier) {
			if(object == null) return;
			if (maxValue == 0) {
				maxValue = object;
			} else if (object > maxValue)
				maxValue = object;
		}

		@Override
		public void reset() {
			maxValue = 0;

		}

	}