package yogi.period.frequency.data;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public abstract class BaseFrequencyAssistant<T extends RelationshipObject> extends RelationshipAssistant<T> {
	protected Frequency getFrequency(T relationshipObject, OneToOneSimpleRelationship<T, Frequency> frequencyDataRelationship) {
		Frequency frequency = this.<Frequency,OneToOneSimpleRelationship<T, Frequency>>getRelationship(relationshipObject, frequencyDataRelationship);
		if(frequency == null)
		{
			return FrequencyManager.NoDayFrequency;
		}
		return frequency;
	}

	protected void addFrequency(T relationshipObject, Frequency frequency, OneToOneSimpleRelationship<T, Frequency> frequencyDataRelationship) {
		Frequency myFrequency = getFrequency(relationshipObject, frequencyDataRelationship);
		myFrequency = Frequencies.add(myFrequency, frequency);
		this.setRelationship(relationshipObject, frequencyDataRelationship, myFrequency);
	}
	
	protected boolean containsDayOfWeek(T relationshipObject, int dayOfWeek, OneToOneSimpleRelationship<T, Frequency> frequencyDataRelationship)
	{
		Frequency frequency = this.getFrequency(relationshipObject, frequencyDataRelationship);
		return Frequencies.contains(frequency, dayOfWeek);
	}

}
