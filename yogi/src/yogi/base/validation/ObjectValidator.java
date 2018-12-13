package yogi.base.validation;

public interface ObjectValidator<T> {
  boolean validate(T object);
}
