package yogi.base.validation;

import yogi.base.app.error.ErrorReporterAdapter;

public class ValidatorAdapter<T> extends ErrorReporterAdapter<T> implements Validator<T> {
	private MultiObjectValidator<T> multiObjectValidator = new MultiObjectValidator<T>();

	public void addObjectValidator(ObjectValidator<? super T> objectValidator) {
		multiObjectValidator.addObjectValidator(objectValidator);
	}

	public boolean validate() {
		return true;
	}

	public boolean validate(T object) {
		return multiObjectValidator.validate(object);
	}
}
