package yogi.period.interval.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.app.ErrorReporter;
import yogi.base.indexing.Indexer;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;

public class MultiIntervaledData<T> {
	private IntervaledData<T> item;
	private Indexer<T, IntervaledData<T>> items;
	protected MultiIntervaledData() {
		super();
	}
	public Set<T> getValues(){
		if(items != null) return items.keySet();
		Set<T> rtnValue = new HashSet<T>(1);
		if(item != null) rtnValue.add(item.getData());
		return rtnValue;
	}
	
	public Collection<IntervaledData<T>> getItems()
	{
		if(items != null) return items.values();
		ArrayList<IntervaledData<T>> rtnValue = new ArrayList<IntervaledData<T>>(1);
		if(item != null) rtnValue.add(item);
		return rtnValue;
		
	}
	
	public ImmutableList<Interval> getIntervals(T value)
	{
		IntervaledData<T> intervaledData = item;
		
		if(items != null) {
			intervaledData = items.get(value);
		}
		if(intervaledData == null || intervaledData.getData() != value) return Interval.EMPTY_IMMUTABLE_LIST;
		return intervaledData.getIntervals();				
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

	protected void add(T value, Interval interval)
	{
		IntervaledData<T> intervaledData = getData(value);
		intervaledData.add(interval);
	}
	
	protected void checkForOverlap(T value, Interval interval) {
		if(getNumberOfItems() > 1){
			for(IntervaledData<T> data : items.values()){
				if(data.getData() != value){
					List<Interval> intersection = Intervals.intersection(data.getIntervals(), interval);
					if(!intersection.isEmpty()){
						ErrorReporter.get().error("Conflicting interval data, Intersection: " + intersection + " old data: " + data.getData() + ", new data: " + value);
					}
				}
			}
		}
		
	}
	
	private IntervaledData<T> getData(T value) {
		IntervaledData<T> intervaledData = item;
		if(items == null)
		{
			if(item == null){
				item = new IntervaledData<T>(value);
				intervaledData = item;
			}
			else{
				items = new Indexer<T, IntervaledData<T>>();
				items.index(intervaledData.getData(), intervaledData);
				item = null;
			}
		}
		if(item == null)
		{
			intervaledData = items.get(value);
			if(intervaledData == null){
				intervaledData = new IntervaledData<T>(value);
				items.index(value, intervaledData);
			}
		}
		return intervaledData;
	}
	
	protected void add(T value, List<Interval> intervals)
	{
		IntervaledData<T> intervaledData = getData(value);
		intervaledData.add(intervals);
	}
}
