package yogi.base.validation;


public interface Validator<T> extends ObjectValidator<T>{
	void addObjectValidator(ObjectValidator<? super T> objectValidator);
    boolean validate();
}
