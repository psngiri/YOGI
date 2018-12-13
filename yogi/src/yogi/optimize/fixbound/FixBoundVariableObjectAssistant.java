package yogi.optimize.fixbound;

import java.util.List;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;

public class FixBoundVariableObjectAssistant extends RelationshipAssistant<RelationshipObject>{
	private static FixBoundVariableObjectAssistant variableObjectAssistant = new FixBoundVariableObjectAssistant();
	private static OneToOneSimpleRelationship<RelationshipObject, Float> excludedRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(RelationshipObject.class, Float.class, "Excluded", true);
	
	public static FixBoundVariableObjectAssistant get(){
		return variableObjectAssistant;
	}
	public Float getBound(RelationshipObject variableObject)
	{
		return this.getRelationship(variableObject, excludedRel);
	}
	
	public void setBound(RelationshipObject variableObject, Float bound)
	{
		this.setRelationship(variableObject, excludedRel,bound);
	}
	
	public void clear(RelationshipObject variableObject)
	{
		this.setRelationship(variableObject, excludedRel, null);
	}
	
	public void clear(List< ? extends RelationshipObject> variableObjects)
	{
		for(RelationshipObject variableObject: variableObjects)
		{
			clear(variableObject);
		}
	}
	
	public boolean isExcluded(RelationshipObject object)
	{
		Float bound = this.getBound(object);
		if(bound == null) return false;
		return (bound == 0);
	}
	
	public boolean isIncluded(RelationshipObject object)
	{
		Float bound = this.getBound(object);
		if(bound == null) return false;
		return (bound == 1);
	}
	
	public void exclude(RelationshipObject object)
	{
		this.setBound(object, 0.0f);
	}

	public void exclude(List< ? extends RelationshipObject> variableObjects)
	{
		for(RelationshipObject variableObject: variableObjects)
		{
			this.setBound(variableObject, 0.0f);
		}
	}
	
	public void include(List< ? extends RelationshipObject> variableObjects)
	{
		for(RelationshipObject variableObject: variableObjects)
		{
			this.setBound(variableObject, 1.0f);
		}
	}
}
