package yogi.period.frequency.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import yogi.base.app.ErrorReporter;
import yogi.base.indexing.Indexer;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public class MultiFrequencyData<T> {
	private FrequencyData<T> item;
	private Indexer<T, FrequencyData<T>> items;
	protected MultiFrequencyData() {
		super();
	}
	public Set<T> getValues(){
		if(items != null) return items.keySet();
		Set<T> rtnValue = new HashSet<T>(1);
		if(item != null) rtnValue.add(item.getData());
		return rtnValue;
	}
	
	public Collection<FrequencyData<T>> getItems()
	{
		if(items != null) return items.values();
		ArrayList<FrequencyData<T>> rtnValue = new ArrayList<FrequencyData<T>>(1);
		if(item != null) rtnValue.add(item);
		return rtnValue;
		
	}
	
	public Frequency getFrequency(T value)
	{
		FrequencyData<T> frequencyData = item;
		
		if(items != null) {
			frequencyData = items.get(value);
		}
		if(frequencyData == null || frequencyData.getData() != value) return FrequencyManager.NoDayFrequency;
		return frequencyData.getFrequency();				
	}
	
	public int getNumberOfItems()
	{
		if(items == null)
		{
			if(item == null) return 0;
			else return 1;
		}
		return items.size();
	}

	protected void add(T value, Frequency frequency)
	{
		FrequencyData<T> frequencyData = getData(value);
		frequencyData.addFrequency(frequency);
	}
	
	protected void checkForOverlap(T value, Frequency frequency) {
		if(getNumberOfItems() > 1){
			for(FrequencyData<T> data : items.values()){
				if(data.getData() != value){
					if(Frequencies.intersects(data.getFrequency(), frequency)){
						ErrorReporter.get().error("Conflicting Frequency data, Intersection: " + Frequencies.intersection(frequency, data.getFrequency()) + " old data: " + data.getData() + ", new data: " + value);
					}
				}
			}
		}
		
	}
	
	private FrequencyData<T> getData(T value) {
		FrequencyData<T> frequencyData = item;
		if(items == null)
		{
			if(item == null){
				item = new FrequencyData<T>(value);
				frequencyData = item;
			}
			else{
				items = new Indexer<T, FrequencyData<T>>();
				items.index(frequencyData.getData(), frequencyData);
				item = null;
			}
		}
		if(item == null)
		{
			frequencyData = items.get(value);
			if(frequencyData == null){
				frequencyData = new FrequencyData<T>(value);
				items.index(value, frequencyData);
			}
		}
		return frequencyData;
	}
	
}
