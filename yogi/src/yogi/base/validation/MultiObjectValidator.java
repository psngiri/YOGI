package yogi.base.validation;

import java.util.ArrayList;
import java.util.List;

public class MultiObjectValidator<T> implements ObjectValidator<T> {
	private final List<ObjectValidator<? super T>> objectValidators = new ArrayList<ObjectValidator<? super T>>();

	public void addObjectValidator(ObjectValidator<? super T> objectValidator) {
		objectValidators.add(objectValidator);
	}

	public boolean validate(T object) {
		for (ObjectValidator<? super T> each : objectValidators) {
			if (!each.validate(object))
				return false;
		}
		return true;
	}
}
