package yogi.period.date;

import yogi.base.indexing.Index;

public interface Date extends Index<Long>, Comparable<Date>{
	long getValue();
}
