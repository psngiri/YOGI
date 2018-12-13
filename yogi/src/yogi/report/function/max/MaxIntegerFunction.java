package yogi.report.function.max;

import yogi.report.function.Function;

public class MaxIntegerFunction implements Function<Integer> {

		private int maxValue = 0;

		@Override
		public Integer getValue() {
			return maxValue;
		}

		@Override
		public void process(Integer object, int multiplier) {
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