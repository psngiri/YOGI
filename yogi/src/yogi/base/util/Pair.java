package yogi.base.util;

import java.io.Serializable;

/**
 * @author Vikram Vadavala
 *
 */
public class Pair<A, B> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1402827369671360994L;
	private final A a;
	private final B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	public A getFirst() {
		return a;
	}
	
	public B getSecond() {
		return b;
	}

}