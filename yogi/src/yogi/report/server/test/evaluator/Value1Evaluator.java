package yogi.report.server.test.evaluator;

import yogi.base.evaluator.Evaluator;
import yogi.report.server.test.Item;

public class Value1Evaluator implements Evaluator<Item, Integer>
	{

		public Integer evaluate(Item item) {
			return item.getValue1();
		}
		
	}