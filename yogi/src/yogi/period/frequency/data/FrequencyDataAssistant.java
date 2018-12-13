package yogi.period.frequency.data;

import yogi.base.indexing.Indexer;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.period.frequency.Frequency;

public abstract class FrequencyDataAssistant<T extends RelationshipObject, O extends Enum<O>> extends BaseFrequencyAssistant<T> {
	private Indexer<O, OneToOneSimpleRelationship<T, Frequency>> relationships = new Indexer<O, OneToOneSimpleRelationship<T, Frequency>>();
	protected abstract Class<T> getRelationshipObjectClass();
	
	private OneToOneSimpleRelationship<T, Frequency> getFrequencyDataRelationship(O data)
	{
		OneToOneSimpleRelationship<T, Frequency> oneToOneSimpleRelationship = relationships.get(data);
		if(oneToOneSimpleRelationship == null)
		{
			synchronized(relationships){
				oneToOneSimpleRelationship = createRelationship(data);
			}
		}
		return oneToOneSimpleRelationship;
	}

	private OneToOneSimpleRelationship<T, Frequency> createRelationship(O data) {
		OneToOneSimpleRelationship<T, Frequency> oneToOneSimpleRelationship = relationships.get(data);
		if(oneToOneSimpleRelationship == null)
		{
			oneToOneSimpleRelationship = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(getRelationshipObjectClass(), Frequency.class, "" +data.name() +"FrequencyData");
			relationships.index(data, oneToOneSimpleRelationship);
		}
		return oneToOneSimpleRelationship;
	}

	public void addFrequency(T relationshipObject, O data, Frequency frequency) {
		this.getFrequencyAssistant().addFrequency(relationshipObject, frequency);
		super.addFrequency(relationshipObject, frequency, getFrequencyDataRelationship(data));
	}

	public Frequency getFrequency(T relationshipObject, O data) {
		return super.getFrequency(relationshipObject, getFrequencyDataRelationship(data));
	}

	public boolean containsDayOfWeek(T relationshipObject, int dayOfWeek, O data) {
		return containsDayOfWeek(relationshipObject, dayOfWeek, getFrequencyDataRelationship(data));
	}
	
	protected abstract FrequencyAssistant<T> getFrequencyAssistant();
}
