package yogi.report.function.min;

import yogi.report.function.Function;

public class MinLongFunction implements Function<Long> {

		private long minValue = 0;

		@Override
		public Long getValue() {
			return minValue;
		}

		@Override
		public void process(Long object, int multiplier) {
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