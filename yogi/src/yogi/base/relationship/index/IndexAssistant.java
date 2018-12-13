package yogi.base.relationship.index;

import yogi.base.Manager;
import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;

public class IndexAssistant<T extends RelationshipObject> extends RelationshipAssistant<RelationshipObject> {
	private static OneToOneSimpleRelationship<RelationshipObject, Integer> indexRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(RelationshipObject.class, Integer.class, "Index");
	private Manager<T> manager;
	
	protected IndexAssistant(Manager<T> manager) {
		super();
		this.manager = manager;
	}

	public void index(){
		ImmutableList<T> objects = manager.findAll();
		for(int i = 0; i < objects.size(); i ++){
			setIndex(objects.get(i), i+1);
		}
	}
	
	private void setIndex(T object, int index){
		this.setRelationship(object, indexRel, index);
	}
	
	public int getIndex(T object){
		Integer index = this.getRelationship(object, indexRel);
		if(index == null) throw new RuntimeException("Looks like the object is not indexed, index the objects by calling IndexAssitant.index(Manager)");
		return convertTo(index);
	}
	
	protected int convertTo(Integer index) {
		return index;
	}

	public T getObject(int index){
		return manager.findAll().get(convertFrom(index)-1);
	}

	protected int convertFrom(int index) {
		return index;
	}
}
