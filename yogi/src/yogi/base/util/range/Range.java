package yogi.base.util.range;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Range <T extends Comparable<T>> implements Serializable{

	private static final long serialVersionUID = 4527773568088668826L;
	private T startValue;
	private T endValue;
	private boolean isStartIncluded;
	private boolean isEndIncluded;


	public Range(T start, T end) {
		this(start, true, end, true);
	}

	public Range(T start, boolean isStartIncluded, T end, boolean isEndIncluded) {
		this.startValue = start;
		this.endValue = end;
		this.isStartIncluded = isStartIncluded;
		this.isEndIncluded = isEndIncluded;
	}

	public boolean isEndIncluded() {
		return isEndIncluded;
	}

	public boolean isStartIncluded() {
		return isStartIncluded;
	}

	public T getEnd() {
		return endValue;
	}

	public T getStart() {
		return startValue;
	}

	/* (non-Javadoc)
	 * @see yogi.base.util.range.SimpleRange#contains(T)
	 */
	public boolean contains(T value) {
		if (isEmpty())
			return false;
		else
			return isUnderUpperBound(value) && isOverLowerBound(value);
	}

	private boolean isUnderUpperBound(T value) {
		if (endValue == null)
			return true;
		if (value == null)
			return false;
		if (isEndIncluded)
			return endValue.compareTo(value) >= 0;
		else
			return endValue.compareTo(value) > 0;
	}

	private boolean isOverLowerBound(T value) {
		if (startValue == null)
			return true;
		if (value == null)
			return false;
		if (isStartIncluded)
			return startValue.compareTo(value) <= 0;
		else
			return startValue.compareTo(value) < 0;
	}

	public boolean contains(Range<T> range) {
		if (range.isEmpty())
			return true;
		T start = range.getStart();
		T end = range.getEnd();
		boolean endSide;
		if (end == null)
			endSide = endValue == null;
		else
			endSide = isUnderUpperBound(end)
					|| isEndIncluded == range.isEndIncluded()
					&& end.equals(endValue);
		boolean startSide;
		if (start == null)
			startSide = startValue == null;
		else
			startSide = isOverLowerBound(start)
					|| isStartIncluded == range.isStartIncluded()
					&& start.equals(startValue);
		return startSide && endSide;
	}

	/* (non-Javadoc)
	 * @see yogi.base.util.range.Range#intersects(yogi.base.util.range.Range)
	 */
	public boolean intersects(Range<T> range) {
			return !intersection(range).isEmpty();
	}

	public boolean intersects(Collection<? extends Range<T>> ranges)
	{
		for(Range<T> myDateRange: ranges)
		{
				if(myDateRange.intersects(this))
				{
					return true;
				}
		}
		return false;
	}

	public Range<T> union(Range<T> range) {
		if (isEmpty())
		return new Range<T>(range.getStart(), range
				.isStartIncluded(), range.getEnd(), range
				.isEndIncluded());
	if (range.isEmpty()) {
		return new Range<T>(this.startValue, this.isStartIncluded,
				this.endValue, this.isEndIncluded);
	} else {
		boolean containMin = !isOverLowerBound(range.getStart());
		boolean containMax = !isUnderUpperBound(range.getEnd());
		T startValue = containMin ? range.getStart()
				: this.startValue;
		T endValue = containMax ? range.getEnd()
				: this.endValue;
		boolean isStartIncluded = containMin ? range.isStartIncluded()
				: this.isStartIncluded;
		boolean isEndIncluded = containMax ? range.isEndIncluded()
				: this.isEndIncluded;
		return new Range<T>(startValue, isStartIncluded, endValue,
				isEndIncluded);
	}
	}

	public Range<T> intersection(Range<T> range) {
		if (isEmpty()) {
		T temp = this.startValue;
		if (temp == null)
			temp = this.endValue;
		return new Range<T>(temp, false, temp, false);
	}
	if (range.isEmpty()) {
		T temp = range.getStart();
		if (temp == null)
			temp = range.getEnd();
		return new Range<T>(temp, false, temp, false);
	} else {
		boolean containMin = !isOverLowerBound(range.getStart());
		boolean containMax = !isUnderUpperBound(range.getEnd());
		T startValue = containMin ? this.startValue : range
				.getStart();
		T endValue = containMax ? this.endValue : range
				.getEnd();
		boolean isStartIncluded = containMin ? this.isStartIncluded : range
				.isStartIncluded();
		boolean isEndIncluded = containMax ? this.isEndIncluded : range
				.isEndIncluded();
		return new Range<T>(startValue, isStartIncluded, endValue,
				isEndIncluded);
	}
	}

	public List<Range<T>> subtract(Range<T> range) {
		List<Range<T>> ra = new ArrayList<Range<T>>();
		if (isEmpty() || range.isEmpty()) {
			ra.add(new Range<T>(startValue, isStartIncluded,
					endValue, isEndIncluded));
			return ra;
		}
		T start = range.getStart();
		T end = range.getEnd();
		boolean startIn = range.isStartIncluded();
		boolean endIn = range.isEndIncluded();
		if (startValue == null && endValue == null && start == null && end == null) {
			return ra;
		}
		boolean containMin = contains(start);
		boolean containMax = contains(end);
		if (containMin && containMax) {
			Range<T> r1 = new Range<T>(startValue, isStartIncluded, start,
					!startIn);
			Range<T> r2 = new Range<T>(end, !endIn, endValue,
					isEndIncluded);
			if (r1.isEmpty() || startValue == null && start == null) {
				ra.add(r2);
				return ra;
			}
			if (r2.isEmpty() || endValue == null && end == null) {
				ra.add(r1);
				return ra;
			} else {
				ra.add(r1);
				ra.add(r2);
				return ra;
			}
		}
		if (containMax) {
			ra.add(new Range<T>(end, !endIn, endValue,
					isEndIncluded));
			return ra;
		}
		if (containMin) {
			ra.add(new Range<T>(startValue, isStartIncluded,
					start, !startIn));
			return ra;
		}
		if (start != null && !isUnderUpperBound(start) || end != null
				&& !isOverLowerBound(end)) {
			ra.add(new Range<T>(startValue, isStartIncluded,
					endValue, isEndIncluded));
			return ra;
		} else {
			start = startValue != null ? startValue : endValue;
			ra.add(new Range<T>(start, false, start, false));
			return ra;
		}
	}

	public int hashCode() {
		int code = startValue.getClass().hashCode();
		if (isEmpty())
			return code;
		code ^= 0x7fffffff;
		if (startValue != null) {
			code ^= startValue.hashCode();
			if (isStartIncluded)
				code ^= 0xffff0000;
		}
		if (endValue != null) {
			code ^= endValue.hashCode() * 31;
			if (isEndIncluded)
				code ^= 0xffff;
		}
		return code;
	}

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof Range))
			return false;
		Range r =  (Range) other;
		if (isEmpty() && r.isEmpty())
			return true;
		if(r.getStart().getClass() != this.getStart().getClass())
			return false;
		Comparable start = r.getStart();
		if (startValue != null) {
			if (!startValue.equals(start))
				return false;
			if (isStartIncluded != r.isStartIncluded())
				return false;
		} else if (start != null)
			return false;
		Comparable end = r.getEnd();
		if (endValue != null) {
			if (!endValue.equals(end))
				return false;
			if (isEndIncluded != r.isEndIncluded())
				return false;
		} else if (end != null)
			return false;
		return true;
	}

	public boolean isEmpty() {
		if (startValue == null || endValue == null)
			return false;
		int cmp = startValue.compareTo(endValue);
		if (cmp > 0)
			return true;
		if (cmp == 0)
			return !(isStartIncluded & isEndIncluded);
		else
			return false;
	}

	public String toString() {
		char c1 = isStartIncluded ? '[' : '(';
		char c2 = isEndIncluded ? ']' : ')';
		if (startValue != null && endValue != null)
			return new String(c1 + startValue.toString() + ", "
					+ endValue.toString() + c2);
		if (endValue != null)
			return new String(c1 + "---, " + endValue.toString() + c2);
		if (startValue != null)
			return new String(c1 + startValue.toString() + ", " + "---" + c2);
		else
			return new String(c1 + "---, ---" + c2);
	}


}
