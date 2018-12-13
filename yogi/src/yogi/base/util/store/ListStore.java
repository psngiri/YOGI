package yogi.base.util.store;


public class ListStore<T> extends BaseListStore<T, Element<Integer, T>>{
	public Element<Integer, T> add(T value){
		Integer key = generateKey();
		return set(key, value);
	}
	
	public Element<Integer, T> set(Integer key, T value) {
		return set(key, new Element<Integer, T>(key, value));
	}

	@Override
	public Element<Integer, T> get(Integer key) {
		return super.get(key);
	}
}
