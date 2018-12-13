package yogi.base.relationship;

import java.util.List;

import yogi.base.Assistant;
import yogi.base.Manager;
import yogi.base.relationship.types.OneToOneSimpleRelationship;

public class RelationshipAssistant<F extends RelationshipObject> extends Assistant<F>{
	
	protected <T, R extends OneToOneSimpleRelationship<? super F,? super T>> void setRelationship(F from, R oneToOneSimpleRelationship, T to) {
		from.getRelationshipController().setOneToOneRelationship(oneToOneSimpleRelationship, to);
	}

	protected <T, R extends OneToOneSimpleRelationship<? super F,? super T>> T getRelationship(F from, R oneToOneSimpleRelationship) {
		return  from.getRelationshipController().<T>getRelationship(oneToOneSimpleRelationship);
	}
	
	protected <T, R extends OneToOneSimpleRelationship<? super F,? super T>> T getRelationship(F from, R oneToOneSimpleRelationship, boolean inherit) {
		return  from.getRelationshipController().<T>getRelationship(oneToOneSimpleRelationship, inherit);
	}
	/*
	 * @param object -- whose parent is requested
	 * @return parent or null if the object is the root
	 */
	@SuppressWarnings("unchecked")
	public F getParent(F object) {
		return (F) object.getRelationshipController().getParent();
	}

	/*
	 * @param object -- whose root is requested
	 * @return root, the root could be itself.
	 */
	@SuppressWarnings("unchecked")
	public F getRoot(F object)
	{
		return (F) object.getRelationshipController().getRoot();
	}
	/**
	 * 
	 * @param object -- whose parent is requested
	 * @param order -- of the parent in the tree, for example immediate parents order is 1 and 
	 * grand parents order is 2 and the great grand parents order is 3 etc.
	 * @return parent at the requested order or throws a runtime exception.
	 */
	public F getParent(F object, int order)
	{
		if(order < 1) throw new RuntimeException("Parent order cant be less than 1 it is " + order);
		F rtnValue = object;
		for(int i = 0; i < order; i++)
		{
			rtnValue = getParent(rtnValue);
			if(rtnValue == null) throw new RuntimeException("Parent at order " + i + " is null");
		}
		return rtnValue;
	}
	
	public <T, R extends OneToOneSimpleRelationship<? super F,? super T>> void reset(Manager<F> manager, T value, R ... oneToOneSimpleRelationship)
	{
		List<F> objects = manager.findAll();
		for(F object: objects)
		{
			for(OneToOneSimpleRelationship<? super F,? super T> relationship: oneToOneSimpleRelationship)
			{
				this.setRelationship(object, relationship, value);
			}
		}
	}
	
}
