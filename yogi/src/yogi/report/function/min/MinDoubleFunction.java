package yogi.report.function.min;

import yogi.report.function.Function;

public class MinDoubleFunction implements Function<Double> {

		private double minValue = 0;

		@Override
		public Double getValue() {
			return minValue;
		}

		@Override
		public void process(Double object, int multiplier) {
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