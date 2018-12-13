package yogi.period.date.range.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.app.ErrorReporter;
import yogi.base.indexing.Indexer;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;

public class MultiDateRangesData<T> {
	private DateRangesData<T> item;
	private Indexer<T, DateRangesData<T>> items;
	protected MultiDateRangesData() {
		super();
	}
	public Set<T> getValues(){
		if(items != null) return items.keySet();
		Set<T> rtnValue = new HashSet<T>(1);
		if(item != null) rtnValue.add(item.getData());
		return rtnValue;
	}
	
	public Collection<DateRangesData<T>> getItems()
	{
		if(items != null) return items.values();
		ArrayList<DateRangesData<T>> rtnValue = new ArrayList<DateRangesData<T>>(1);
		if(item != null) rtnValue.add(item);
		return rtnValue;
		
	}
	
	public ImmutableList<DateRange> getDateRanges(T value)
	{
		DateRangesData<T> dateRangeedData = item;
		
		if(items != null) {
			dateRangeedData = items.get(value);
		}
		if(dateRangeedData == null || dateRangeedData.getData() != value) return DateRanges.EMPTY_IMMUTABLE_LIST;
		return dateRangeedData.getDateRanges();				
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

	protected void add(T value, DateRange dateRange)
	{
		DateRangesData<T> dateRangeedData = getData(value);
		dateRangeedData.add(dateRange);
	}
	
	protected void checkForOverlap(T value, DateRange dateRange) {
		if(getNumberOfItems() > 1){
			for(DateRangesData<T> data : items.values()){
				if(data.getData() != value){
					List<DateRange> intersection = DateRanges.intersection(data.getDateRanges(), dateRange);
					if(!intersection.isEmpty()){
						ErrorReporter.get().error("Conflicting dateRange data, Intersection: " + intersection + " old data: " + data.getData() + ", new data: " + value);
					}
				}
			}
		}
		
	}
	
	private DateRangesData<T> getData(T value) {
		DateRangesData<T> dateRangeedData = item;
		if(items == null)
		{
			if(item == null){
				item = new DateRangesData<T>(value);
				dateRangeedData = item;
			}
			else{
				items = new Indexer<T, DateRangesData<T>>();
				items.index(dateRangeedData.getData(), dateRangeedData);
				item = null;
			}
		}
		if(item == null)
		{
			dateRangeedData = items.get(value);
			if(dateRangeedData == null){
				dateRangeedData = new DateRangesData<T>(value);
				items.index(value, dateRangeedData);
			}
		}
		return dateRangeedData;
	}
	
	protected void add(T value, List<DateRange> dateRanges)
	{
		DateRangesData<T> dateRangeedData = getData(value);
		dateRangeedData.add(dateRanges);
	}
}
