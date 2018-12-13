package yogi.report.function.max;

import yogi.report.function.Function;

public class MaxLongFunction implements Function<Long> {

		private long maxValue = 0;

		@Override
		public Long getValue() {
			return maxValue;
		}

		@Override
		public void process(Long object, int multiplier) {
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