package yogi.base.io.delayed;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;

public class ModifierAdapter<C> implements Modifier<C>{
	protected final List<C> EMPTY_CREATORS = new ImmutableList<C>(new ArrayList<C>(0));
	private List<C> creators;
	
	public ModifierAdapter() {
		super();
		creators = new ArrayList<C>(1);
		creators.add(null);
	}

	public List<C> modify(C object) {
		creators.set(0, object);
		return creators;
	}

	public List<C> modify(List<C> objects) {
		return objects;
	}

}
