package yogi.base.io.multi;

public class ObjectClass<T> {
	private Class<T> klass;
	private T object;
	public ObjectClass(Class<T> klass, T object) {
		super();
		this.klass = klass;
		this.object = object;
	}
	public Class<T> getKlass() {
		return klass;
	}
	public T getObject() {
		return object;
	}
	
}
