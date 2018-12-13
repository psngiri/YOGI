package yogi.period.frequency.data;

import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.period.frequency.Frequency;

public abstract class FrequencyAssistant<T extends RelationshipObject> extends BaseFrequencyAssistant<T> {
	protected abstract OneToOneSimpleRelationship<T, Frequency> getFrequencyDataRelationship();
	public void addFrequency(T relationshipObject, Frequency frequency) {
		addFrequency(relationshipObject, frequency, getFrequencyDataRelationship());
	}

	public boolean containsDayOfWeek(T relationshipObject, int dayOfWeek) {
		return containsDayOfWeek(relationshipObject, dayOfWeek, getFrequencyDataRelationship());
	}

	public Frequency getFrequency(T relationshipObject) {
		return getFrequency(relationshipObject, getFrequencyDataRelationship());
	}

}
