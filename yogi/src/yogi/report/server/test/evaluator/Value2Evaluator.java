package yogi.report.server.test.evaluator;

import yogi.base.evaluator.Evaluator;
import yogi.report.server.test.Item;

public class Value2Evaluator implements Evaluator<Item, Float>
	{

		public Float evaluate(Item item) {
			return item.getValue2();
		}
		
	}