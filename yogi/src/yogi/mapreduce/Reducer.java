package yogi.mapreduce;

import java.util.List;

public interface Reducer<R> {
	R reduce(List<R> results);
}
