package yogi.base.io.db;

import yogi.base.Manager;
import yogi.base.Selector;
import yogi.base.io.ObjectWriter;
import yogi.base.io.Writer;
import yogi.base.relationship.RelationshipObject;
import yogi.base.util.immutable.ImmutableList;

public class BaseIncrementalWriter<T extends RelationshipObject> implements Writer
{	
	private Manager<T> manager;
	private Selector<? super T> selector;
	private ObjectWriter<T> writer;
	
	public BaseIncrementalWriter(ObjectWriter<T> writer, Manager<T> manager, Selector<? super T> selector) {
		super();
		this.writer = writer;
		this.manager = manager;
		this.selector = selector;
	}

	public void write()
	{
		ImmutableList<T> objects = manager.findAll();
		int index = -1;
		for(int i = objects.size()-1; i >=0; i --)
		{
			T object = objects.get(i);
			if (selector != null && !selector.select(object)){
				index = i;
				break;
			}
		}
		if(index == objects.size()-1) return;
		if(!writer.open()) return;
		for(int i = index +1; i < objects.size(); i ++){
			writer.write(objects.get(i));
		}
		writer.close();
	}

	@Override
	public boolean isActivated() {
		return true;
	}

	public void setSelector(Selector<? super T> selector) {
		this.selector = selector;
	}

}