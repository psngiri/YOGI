package yogi.report.function.max;

import yogi.report.function.Function;

public class MaxDoubleFunction implements Function<Double> {

		private double maxValue = 0;

		@Override
		public Double getValue() {
			return maxValue;
		}

		@Override
		public void process(Double object, int multiplier) {
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