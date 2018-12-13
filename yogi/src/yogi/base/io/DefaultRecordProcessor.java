package yogi.base.io;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.validation.Validator;

public class DefaultRecordProcessor<T, R> extends FinderRecordProcessor<T, Creator<T>, R>{
	public DefaultRecordProcessor(Validator<T> validator, Scanner<T, R> scanner, Factory<T> factory) {
		this(validator, scanner, factory, null);
	}

	public DefaultRecordProcessor(Validator<T> validator, Scanner<T, R> scanner, Factory<T> factory,
			Selector<R> recordValidator) {
		super(validator, scanner, factory, recordValidator, null);
	}
}
