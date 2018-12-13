package yogi.report.server.test.evaluator;

import yogi.base.evaluator.Evaluator;
import yogi.report.server.test.Item;

public class Key3Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.getKey3();
		}
		
	}