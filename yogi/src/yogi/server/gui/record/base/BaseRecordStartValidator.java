package yogi.server.gui.record.base;

import yogi.base.app.ApplicationProperties;
import yogi.base.relationship.RelationshipObject;
import yogi.base.validation.ValidatorAdapter;

public class BaseRecordStartValidator<K extends RelationshipObject, T extends BaseRecord<K>> extends ValidatorAdapter<T> {
	private BaseRecordValidator<K, T> validator;
	private boolean lessThanStart = true;
	public BaseRecordStartValidator(boolean lessThanStart) {
		super();
		this.lessThanStart = lessThanStart;
	}

	public BaseRecordStartValidator(BaseRecordValidator<K, T> validator) {
		super();
		this.validator = validator;
	}

	@Override
	public boolean validate(T record) {
		if(lessThanStart){
			if(record.getTimeStamp() >= ApplicationProperties.StartTimeStamp) return !lessThanStart;
			if(validator == null) return lessThanStart;
		}else{
			if(record.getTimeStamp() < ApplicationProperties.StartTimeStamp) return lessThanStart;
			if(validator == null) return !lessThanStart;
		}
		return validator.validate(record);
	}

}
