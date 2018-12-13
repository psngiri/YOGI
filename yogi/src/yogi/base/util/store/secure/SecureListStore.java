package yogi.base.util.store.secure;

import yogi.base.util.store.BaseListStore;

public class SecureListStore<T, S> extends BaseListStore<T, SecureElement<Integer, T, S>>{
	public SecureElement<Integer, T, S> add(T value, S secureId){
		Integer key = generateKey();
		return set(key, value, secureId);
	}
	
	public SecureElement<Integer, T, S> set(Integer key, T value, S secureId) {
		return set(key, new SecureElement<Integer, T, S>(key, value, secureId));
	}

	public SecureElement<Integer, T, S> get(Integer key, S secureId) {
		SecureElement<Integer, T, S> secureElement = super.get(key);
		if(!secureElement.getSecureId().equals(secureId)) throw new RuntimeException(String.format("SecurityException: Creator SecureId %s not same as the requestor SecureId %s", secureElement.getSecureId(), secureId));
		return secureElement;
	}
	
}
