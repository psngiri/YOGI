package yogi.base.relationship.types;


public abstract class RelationshipType<F, T>{
	private Class<F> from;
	private Class<T> to;
	private String name;
	private String key;
	private boolean inverse = true;
	private boolean oneToOne = false;
	private RelationshipType<T, F> otherRelationship;
	private int index = -1;
	private boolean inherited = false;


	protected RelationshipType(Class<F> from, Class<T> to, String name, boolean inverse, boolean oneToOne, RelationshipType<T, F> otherRelationship, boolean inherited) {
		super();
		this.from = from;
		this.to = to;
		this.name = name;
		this.inverse = inverse;
		this.oneToOne = oneToOne;
		this.otherRelationship = otherRelationship;
		String fromName = from.getSimpleName();
		String toName = to.getSimpleName();
		key = fromName + toName + name;
		this.inherited = inherited;
	}


	public boolean isInherited() {
		return inherited;
	}
	
	public int getIndex() {
		return index;
	}

	void setIndex(int index) {
		this.index = index;
	}

	public boolean isInverse() {
		return inverse;
	}

	public String toString()
	{
		return key;
	}

	public boolean equals(RelationshipType<F,T> obj) {
		return this.key.equals(obj.key);
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	public Class<F> getFrom() {
		return from;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public Class<T> getTo() {
		return to;
	}
	
	public boolean hasDirectRelationship()
	{
		if(!isInverse())return false;
		if(this.getOtherRelationship() == null) return false;
		return true;
	}
	
	public boolean hasInverseRelationship()
	{
		if(isInverse())return false;
		if(this.getOtherRelationship() == null) return false;
		return true;
	}

	public boolean isOneToOne() {
		return oneToOne;
	}


	public RelationshipType<T, F> getOtherRelationship() {
		return otherRelationship;
	}


	void setOtherRelationship(RelationshipType<T, F> otherRelationship) {
		this.otherRelationship = otherRelationship;
	}
}
