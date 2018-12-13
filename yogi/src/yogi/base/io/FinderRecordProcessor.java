package yogi.base.io;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.validation.Validator;

public class FinderRecordProcessor<T, C extends Creator<T>, R> extends BaseRecordProcessor<T, C, R>{

	public FinderRecordProcessor(Validator<T> validator, CreatorScanner<T, C, R> scanner, Factory<T> factory,
			Selector<R> recordValidator, Finder<T, C> finder) {
		super(validator, scanner, factory, recordValidator, finder);
	}

	public T process(R record)
	{
		if(!valid(record)) return null;
		return processRecord(record);
	}

	private T processRecord(R record) {
		C creator = scan(record);
		if(creator == null) return null;
		return addObject(create(creator));
	}

}
