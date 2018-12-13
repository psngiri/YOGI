package yogi.base.app.error;

import java.util.HashMap;
import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.indexing.ManyIndexer;

public class StackIndexer extends ManyIndexer<String, Object>
{
	HashMap<String, Throwable> stacks;
	
	public StackIndexer() {
		super();
		stacks = new HashMap<String, Throwable>();
	}

	public StackIndexer(int capacity) {
		super(capacity);
		stacks = new HashMap<String, Throwable>(capacity);
	}

	@Override
	public List<Object> index(String key, Object value) {
		return index(key, value, null);
	}
	
	public List<Object> index(String key, Object value, Throwable e) {
		if(ErrorReporter.DumpErrorStack)
		{
			if(!stacks.containsKey(key))
			{
				stacks.put(key, new Throwable(key, e));
			}
		}
		return super.index(key, value);
	}
	
	public Throwable getThrowable(String key)
	{
		return stacks.get(key);
	}
}