package yogi.server.base.purge;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.relationship.RelationshipManager;

/**
 * @author Vikram Vadavala
 *
 */

public abstract class PurgeableManager<O extends Purgeable> extends RelationshipManager<O> {

	protected PurgeableManager() {
		super();
	}
	
	protected void purge(Factory<O> factory)
	{
		factory.purge(getPurgeSelector());
	}
	
	protected Selector<O> getPurgeSelector(){
		return new PurgeSelector<O>();
	}
	
}
