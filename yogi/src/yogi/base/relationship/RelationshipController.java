package yogi.base.relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import yogi.base.Selector;
import yogi.base.Util;
import yogi.base.relationship.types.OneToManyDirectWithInverseRelationship;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.OneToManyRelationship;
import yogi.base.relationship.types.OneToOneRelationship;
import yogi.base.relationship.types.RelationshipType;
import yogi.base.relationship.types.RelationshipTypeManager;
import yogi.base.util.immutable.ImmutableList;

class RelationshipController{

	private RelationshipObject relationshipObject;

	private List<Object> items;
	
	public RelationshipController(RelationshipObject relationshipObject) {
		super();
		this.relationshipObject = relationshipObject;
		items = new ArrayList<Object>(RelationshipTypeManager.get().getInitialCapacity(relationshipObject.getClass()));
	}
	
	public RelationshipObject getParent()
	{
		return null;
	}
	
	public RelationshipObject getRoot()
	{
		return relationshipObject;
	}
	
	private RelationshipObject getRelationshipObject() {
		return relationshipObject;
	}

	private int getIndex(RelationshipType relationship) {
		return relationship.getIndex();
	}

	public <T> T getRelationship(OneToOneRelationship relationship, boolean inherit) {
		return this.<T>getRelationship(relationship);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getRelationship(OneToOneRelationship relationship) {
		return (T) getItem(getIndex(relationship));
	}
	
	public <T> ImmutableList<T> getRelationship(OneToManyRelationship relationship, boolean inherit) {
		return this.<T>getRelationship(relationship);
	}
	
	@SuppressWarnings("unchecked")
	public <T> ImmutableList<T> getRelationship(OneToManyRelationship relationship) {
		List<T> rtnValue = (List<T>) getItem(getIndex(relationship));
		if(rtnValue == null) rtnValue = new ArrayList<T>();
		return new ImmutableList<T>(rtnValue);
	}

	private <T extends RelationshipObject> void setRelationship(RelationshipType relationship, T value) {
		if(relationship.isOneToOne())
		{
			OneToOneRelationship oneToOneRelationship = (OneToOneRelationship)relationship;
			Object oldValue = getRelationship(oneToOneRelationship, false);
			if(oldValue != null) throw new RuntimeException(String.format("%4$s:%1$s already added to %5$s:%2$s as %6$s %4$s remove it from %5$s:%2$s before adding it to %5$s:%3$s.", this.getRelationshipObject(), oldValue, value,
					oneToOneRelationship.getFrom().getSimpleName(), oneToOneRelationship.getTo().getSimpleName(), oneToOneRelationship.getName()));
			setOneToOneRelationship(oneToOneRelationship, value);
		}else
		{
			synchronized(getRelationshipObject())
			{
				addToRelationship((OneToManyRelationship)relationship, value);
			}
		}
	}
	
	public void setOneToOneRelationship(OneToOneRelationship relationship, Object value) {
		setItem(getIndex(relationship), value);
	}

	public <T> void addToRelationship(OneToManyRelationship relationship, T value) {
		List<T> item = getRelationshipCreateEmptyIfNull(relationship);
		add(relationship, value, item);
	}

	public <T extends RelationshipObject> void addToRelationship(OneToManyDirectWithInverseRelationship relationship, T value) {
		List<T> item = getRelationshipCreateEmptyIfNull(relationship);
		add(relationship, value, item);
	}
	
	private <T> void add(OneToManyRelationship relationship, T value, List<T> item) {
		item.add(value);
	}
	
	private <T extends RelationshipObject> void add(OneToManyDirectWithInverseRelationship relationship, T value, List<T> item) {
		item.add(value);
		if(relationship.hasInverseRelationship())
		{
			RelationshipType otherRelationship = relationship.getOtherRelationship();
			value.getRelationshipController().setRelationship(otherRelationship, this.getRelationshipObject());
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> getRelationshipCreateEmptyIfNull(OneToManyRelationship relationship) {
		int index = getIndex(relationship);
		List<T> item = null;
		if(index < items.size()) item = (List<T>) getItem(index);
		if (item == null) {
			synchronized (items) {
				if(index < items.size()) item = (List<T>) getItem(index);
				if (item == null) {
					item = new ArrayList<T>();
					setItem(index, item);
				}
			}
		}
		return item;
	}
	
	public <T extends RelationshipObject> void addAllToRelationship(OneToManyDirectWithInverseRelationship relationship, Collection<T> objects)
	{
		if(objects == null || objects.isEmpty()) return;
		List<T> item = getRelationshipCreateEmptyIfNull(relationship);
		for(T value : objects)
		{
			add(relationship, value, item);
		}
	}

	public <T> void addAllToRelationship(OneToManyRelationship relationship, Collection<T> objects)
	{
		if(objects == null || objects.isEmpty()) return;
		List<T> item = getRelationshipCreateEmptyIfNull(relationship);
		for(T value : objects)
		{
			add(relationship, value, item);
		}
	}

	private <T extends RelationshipObject> void deleteRelationship(RelationshipType relationship, T value) {
		if(relationship.isOneToOne())
		{
			deleteOneToOneRelationship((OneToOneRelationship)relationship);
		}else
		{
			synchronized(getRelationshipObject())
			{
				removeFromRelationship((OneToManyRelationship)relationship, value);
			}
		}
	}
	
	public void deleteOneToOneRelationship(OneToOneRelationship relationship) {
		items.set(getIndex(relationship), null);
	}

	public void clearOneToManyRelationship(OneToManyInverseRelationship relationship) {
		List items = (List) getItem(getIndex(relationship));
		items.clear();
	}

	public void deleteOneToManyRelationship(OneToManyInverseRelationship relationship) {
		items.set(getIndex(relationship), null);
	}

	public <T> boolean removeFromRelationship(OneToManyRelationship relationship, T value) {
		List item = (List) getItem(getIndex(relationship));
		return remove(relationship, value, item);
	}
	
	public <T> void purgeRelationship(OneToManyInverseRelationship relationship, Selector<T> selector)
	{
		List<T> item = (List<T>) getItem(getIndex(relationship));
		if(item == null || item.isEmpty())return;
		Util.purge(item, selector);
	}

	
	public <T extends RelationshipObject> boolean removeFromRelationship(OneToManyDirectWithInverseRelationship relationship, T value) {
		List item = (List) getItem(getIndex(relationship));
		boolean rtnValue = remove(relationship, value, item);
		if(rtnValue) removeInverse(relationship, value);
		return rtnValue;
	}

	private <T> boolean remove(OneToManyRelationship relationship, T value, List from) {
		if(from == null || from.isEmpty()) 
		{
			System.out.println("Object: "+ value + " not found in relationship: " + relationship + " with: " + this.getRelationshipObject() + " to DELETE(Empty relationship).");
			return false;
		}
		boolean success = from.remove(value);
		if(!success)
		{
			System.out.println("Object: "+ value + " not found in relationship: " + relationship + " with: " + this.getRelationshipObject() + " to DELETE.");
		}
		return success;
	}
	
	private<T extends RelationshipObject> void removeInverse(OneToManyDirectWithInverseRelationship relationship, T value)
	{
		if(relationship.hasInverseRelationship())
		{
			value.getRelationshipController().deleteRelationship(relationship.getOtherRelationship(), this.getRelationshipObject());
		}
	}
	
	public <T> void removeAllFromRelationship(OneToManyRelationship relationship, Collection<T> objects) {
		List item = (List) getItem(getIndex(relationship));
		if(item == null || item.isEmpty()) return;
		for (T value : objects) {
			remove(relationship, value, item);
		}
	}
	
	public <T extends RelationshipObject> void removeAllFromRelationship(OneToManyDirectWithInverseRelationship relationship, Collection<T> objects) {
		List item = (List) getItem(getIndex(relationship));
		if(item == null || item.isEmpty()) return;
		for (T value : objects) {
			boolean success = remove(relationship, value, item);
			if(success) removeInverse(relationship, value);
		}
	}
		
		
	private Object getItem(int index)
	{
		if(index >= items.size()) return null;
		return items.get(index);
	}
	
	private <T> void setItem(int index, Object item) {
		if(index >= items.size()){
			synchronized (items) {
				if(index >= items.size()){
					for (int i = items.size(); i <= index; i++) {
						items.add(i, null);
					}
				}
			}
		}
		items.set(index, item);
	}

	static void removeFromRelationships(Object obj)
	{
		List<RelationshipType> relationships = RelationshipTypeManager.get().getRelationships(obj.getClass());
		if(relationships.isEmpty()) return;
		RelationshipObject relationshipObject = (RelationshipObject) obj;
		RelationshipController.isDeletable(relationshipObject, relationships);
		
		for(RelationshipType relationship: relationships)
		{
			if(relationship.hasInverseRelationship() || (relationship.hasDirectRelationship() && !relationship.isOneToOne()))
			{
				List objects = (List) relationshipObject.getRelationshipController().getItem(relationship.getIndex());
				if(objects == null || objects.isEmpty()) continue;
				RelationshipType otherRelationship = relationship.getOtherRelationship();
				for(Object object: objects)
				{
					((RelationshipObject)object).getRelationshipController().deleteRelationship(otherRelationship, relationshipObject);
				}
			}else if(relationship.hasDirectRelationship())
			{
				RelationshipObject item = (RelationshipObject) relationshipObject.getRelationshipController().getItem(relationship.getIndex());
				if(item == null) continue;
				RelationshipType otherRelationship = relationship.getOtherRelationship();
				item.getRelationshipController().deleteRelationship(otherRelationship, relationshipObject);
			}
		}
	}
	
	static private void isDeletable(RelationshipObject relationshipObject, List<RelationshipType> relationships) {
		for(RelationshipType relationship: relationships)
		{
			if(relationship.isInverse() && !relationship.hasDirectRelationship())
			{
				Object item = relationshipObject.getRelationshipController().getItem(relationship.getIndex());
				if(item != null)
				{
					 if(item instanceof List)
					 {
						 if(!((List)item).isEmpty()){							 
							 throw new RuntimeException("Could not delete: ("+ relationship.getFrom().getSimpleName()+":" + relationshipObject + ") It is Reffered by other objects "+ relationship.getTo().getSimpleName()+":" +item);
						 }
					 }else {
						 throw new RuntimeException("Could not delete: (" + relationship.getFrom().getSimpleName()+":" + relationshipObject + ") It is Reffered by other objects "+ relationship.getTo().getSimpleName()+":" +item);
					 }
				}
			}
		}		
	}
	
}
