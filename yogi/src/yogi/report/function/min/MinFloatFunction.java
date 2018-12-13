package yogi.report.function.min;

import yogi.report.function.Function;

public class MinFloatFunction implements Function<Float> {

		private float minValue = 0;

		@Override
		public Float getValue() {
			return minValue;
		}

		@Override
		public void process(Float object, int multiplier) {
			if(object == null) return;
			if (minValue == 0) {
				minValue = object;
			} else if (object < minValue)
				minValue = object;
		}

		@Override
		public void reset() {
			minValue = 0;

		}

	}