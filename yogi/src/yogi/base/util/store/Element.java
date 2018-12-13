package yogi.base.util.store;

public class Element<K, T> {
	private long timeStamp;
	private T value;
	private K key;
	public Element(K key, T value) {
		super();
		this.key = key;
		this.value = value;
		timeStamp = System.currentTimeMillis();
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public T getValue() {
		timeStamp = System.currentTimeMillis();
		return value;
	}
	public K getKey() {
		return key;
	}
}
