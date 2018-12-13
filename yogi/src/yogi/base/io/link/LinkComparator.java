package yogi.base.io.link;


public interface LinkComparator<F, T>{
	int compareFrom(F from1, F from2);
	int compareTo(T to1, T to2);
	int compareFromTo(F from, T to);
}
