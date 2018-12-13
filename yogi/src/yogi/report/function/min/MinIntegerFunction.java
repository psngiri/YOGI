package yogi.report.function.min;

import yogi.report.function.Function;

public class MinIntegerFunction implements Function<Integer> {

		private int minValue = 0;

		@Override
		public Integer getValue() {
			return minValue;
		}

		@Override
		public void process(Integer object, int multiplier) {
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