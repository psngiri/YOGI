package yogi.report.server.test.evaluator;

import yogi.base.evaluator.Evaluator;
import yogi.report.server.test.Item;

public class Key2Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.getKey2();
		}
		
	}