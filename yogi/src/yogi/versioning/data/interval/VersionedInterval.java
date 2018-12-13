package yogi.versioning.data.interval;

import java.util.ArrayList;
import java.util.List;

import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.RelationshipObjectImpl;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.versioning.data.VersionedData;
import yogi.versioning.version.Version;

class VersionedInterval<T extends RelationshipObject> extends RelationshipObjectImpl<VersionedInterval> implements VersionedData {
	private ImmutableList<Interval> intervals;
	private T data;
	private Version version;
	private IntervalType type;

	public VersionedInterval(T data, Interval interval, Version version, IntervalType type) {
		this(data, version, type);
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(interval);
		this.intervals = new ImmutableList<Interval>(intervals);
	}
	public VersionedInterval(T data, List<Interval> intervals, Version version, IntervalType type) {
		this(data, version, type);
		this.intervals = new ImmutableList<Interval>(intervals);
	}
	
	private VersionedInterval(T data, Version version, IntervalType type) {
		super();
		this.data = data;
		this.version = version;
		this.type = type;
	}
	
	public ImmutableList<Interval> getIntervals() {
		return intervals;
	}

	public T getData() {
		return data;
	}

	public IntervalType getType() {
		return type;
	}

	public Version getVersion() {
		return version;
	}
	public String getName() {
		return "";
	}
}
