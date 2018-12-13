package yogi.versioning;

public interface IncrementalData<M, T> {
	M getMainData();
	T getData();
}
