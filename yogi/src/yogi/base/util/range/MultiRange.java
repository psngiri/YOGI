package yogi.base.util.range;

import java.util.ArrayList;
import java.util.List;


public class MultiRange<T extends Comparable<T>>{
	protected List<Range<T>> ranges = new ArrayList<Range<T>>();
	
	public MultiRange() {
		super();
	}
	
	public void addRange(Range<T> range)
	{
		if(intersects(range)) throw new RuntimeException("Range:" + range + " intersects with MultiRange:" + this);
		ranges.add(range);
	}

	public void addRange(MultiRange<T> range)
	{
		for(Range<T> myRange: range.ranges)
		{
			addRange(myRange);
		}
	}
		
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MultiRange)) return false;
		MultiRange multiRange = (MultiRange) obj;
		if(ranges.size() != multiRange.ranges.size()) return false;
		for(int i = 0; i < ranges.size(); i ++)
		{
			if(!ranges.get(i).equals(multiRange.ranges.get(i))) return false;
		}
		return true;
	}

	public int hashCode() {
		if(ranges.isEmpty())
			return 0;
		return ranges.get(0).hashCode();
	}

	public boolean isEmpty() {
		for(Range<T> range: ranges)
		{
			if(!range.isEmpty()) return false;
		}
		return true;
	}

	public boolean contains(T value) {
		for(Range<T> range: ranges)
		{
			if(range.contains(value)) return true;
		}
		return false;
	}

	public boolean intersects(Range<T> range) {
		for(Range<T> myRange: ranges)
		{
			if(myRange.intersects(range)) return true;
		}
		return false;
	}

	public boolean intersects(MultiRange<T> range) {
		for(Range<T> myRange: ranges)
		{
			if(range.intersects(myRange)) return true;
		}
		return false;
	}

	public boolean contains(Range<T> range) {
		for(Range<T> myRange: ranges)
		{
			if(range.contains(myRange)) return true;
		}
		return false;
	}

	public Range<T> union(Range<T> range) {
		throw new RuntimeException("not yet implemented");
	}

	public Range<T> intersect(Range<T> range) {
		throw new RuntimeException("not yet implemented");
	}

	public MultiRange subtract(Range<T> range) {
		throw new RuntimeException("not yet implemented");
	}
	
	public boolean contains(MultiRange<T> range) {
		for(Range<T> myRange: ranges)
		{
			if(range.contains(myRange)) return true;
		}
		return false;
	}

	public Range<T> union(MultiRange<T> range) {
		throw new RuntimeException("not yet implemented");
	}

	public Range<T> intersect(MultiRange<T> range) {
		throw new RuntimeException("not yet implemented");
	}
	
	public List<Range<T>> subtract(MultiRange<T> range) {
		List<Range<T>> result = new ArrayList<Range<T>>();
		result.addAll(ranges);
		for( Range<T> passedRange : range.ranges)
		{			
			List<Range<T>> tempResult = new ArrayList<Range<T>>();
			for(Range<T> myRange: result)
			{
				tempResult.addAll(myRange.subtract(passedRange));
			}		
			result = tempResult;
		}				
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Range range:ranges)
		{
			sb.append(range).append(' ');
		}
		sb.deleteCharAt(sb.lastIndexOf(" "));
		return sb.toString();
	}

}
