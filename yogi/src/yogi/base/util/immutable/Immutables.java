package yogi.base.util.immutable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.util.merge.MergeManager;
import yogi.base.util.merge.Merger;

public class Immutables {

	public static <T> void sort(ImmutableList<T> immutableList, Comparator<? super T> comparator)
	{
		Collections.sort(immutableList.getList(), comparator);
	}
	
	public static <T> List<T> merge(ImmutableList<T> immutableList, Merger<T> merger, Comparator<T> comparator)
	{
		return MergeManager.merge(immutableList.getList(), merger, comparator);
	}
}
