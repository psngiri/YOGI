package yogi.period.frequency.data;

import java.util.HashSet;
import java.util.Set;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.period.frequency.Frequency;

public abstract class MultiFrequencyDataAssistant<T extends RelationshipObject, I extends MultiFrequencyData<O>, O> extends RelationshipAssistant<T> {	
	protected abstract OneToOneSimpleRelationship<T, I> getMultiFrequencyDataRelationship();
	private boolean checkForDuplicateValuesOnSameDay = true;
	private FrequencyAssistant<? super T> frequencyAssistantToPropagateTo = null;
	
	protected MultiFrequencyDataAssistant() {
		super();
	}


	protected MultiFrequencyDataAssistant(boolean checkForDuplicateValuesOnSameDay, FrequencyAssistant<? super T> frequencyAssistantToPropagateTo) {
		super();
		this.checkForDuplicateValuesOnSameDay = checkForDuplicateValuesOnSameDay;
		this.frequencyAssistantToPropagateTo = frequencyAssistantToPropagateTo;
	}


	public I getData(T relationshipObject)
	{
		I frequencyData = getMultiFrequencyData(relationshipObject);
		return frequencyData;
	}

	public Set<O> getValues(T relationshipObject){		
		I data = getData(relationshipObject);
		if(data == null)
			return new HashSet<O>();
		return data.getValues();
	}
	
	public Frequency getFrequency(T relationshipObject, O value){
		return getData(relationshipObject).getFrequency(value);
	}
	
	private void propagate(T relationshipObject, Frequency frequency) {
		if(frequencyAssistantToPropagateTo == null) return;
		frequencyAssistantToPropagateTo.addFrequency(relationshipObject, frequency);
	}
	
	public void addFrequency(T relationshipObject, O value, Frequency frequency)
	{	
		propagate(relationshipObject, frequency);
		I frequencyData = getMultiFrequencyDataCreateIfNecessary(relationshipObject);
		frequencyData.add(value, frequency);
		checkForDuplicateValuesOnSameDay(value, frequency, frequencyData);	
	}

	private void checkForDuplicateValuesOnSameDay(O value, Frequency frequency, I frequencyData) {
		if(!checkForDuplicateValuesOnSameDay)
			return;
		frequencyData.checkForOverlap(value,frequency);
	}

	private I getMultiFrequencyDataCreateIfNecessary(T relationshipObject) {
		I multiFrequencyData = getMultiFrequencyData(relationshipObject);
		if(multiFrequencyData == null)
		{
			synchronized(relationshipObject)
			{
				multiFrequencyData = this.<I,OneToOneSimpleRelationship<T, I>>getRelationship(relationshipObject, getMultiFrequencyDataRelationship());
				if(multiFrequencyData == null)
				{
					multiFrequencyData = createMultiFrequencyData();
					this.setRelationship(relationshipObject, getMultiFrequencyDataRelationship(), multiFrequencyData);
				}
			}
		}
		return multiFrequencyData;
	}

	private I getMultiFrequencyData(T relationshipObject) {
		I frequencyData = this.<I,OneToOneSimpleRelationship<T, I>>getRelationship(relationshipObject, getMultiFrequencyDataRelationship());
		return frequencyData;
	}
	
	protected abstract I createMultiFrequencyData();

}
