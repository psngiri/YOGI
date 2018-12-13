package yogi.base.util.immutable;

import java.util.List;
// This class is not to be used in normal usage
// only to be used in some very special casses in framework.
// Usage of this would brake the immutablity rule of the framework.
// Please do talk to someone who know about the framework well if you 
// are thinking of using it. Thanks.
public class ImmutableListAdjuster {
	protected <T> List<T> getList(ImmutableList<T> immutableList)
	{
		return immutableList.getList();
	}
}
