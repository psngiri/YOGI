package yogi.base.util.store.secure;

import yogi.base.util.store.Element;

public class SecureElement<K, T, S> extends Element<K, T> {
	private S secureId;

	public SecureElement(K key, T value, S secureId) {
		super(key, value);
		this.secureId = secureId;
	}

	public S getSecureId() {
		return secureId;
	}
	
}
