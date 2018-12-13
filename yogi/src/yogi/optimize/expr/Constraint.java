package yogi.optimize.expr;

import static yogi.optimize.expr.ConstraintFactory.get;

import yogi.base.relationship.RelationshipObject;

public class Constraint extends BaseConstraint {
    private float lowerBound;
    private float upperBound;
    private RelationshipObject constraintObject;
    private String prefix;
    private String suffix;
    private String name;
    
    public Constraint(String prefix, String suffix, RelationshipObject constraintObject, Expression expression) {
    	this(prefix, suffix, constraintObject, expression, Float.MIN_VALUE, Float.MAX_VALUE);
    }
    
    public Constraint(String prefix, RelationshipObject constraintObject, Expression expression) {
    	this(prefix, constraintObject, expression, Float.MIN_VALUE, Float.MAX_VALUE);
	}
    
	public Constraint(String prefix, RelationshipObject constraintObject, Expression expression, float lowerBound, float upperBound) {
		this(prefix, "", constraintObject, expression, lowerBound, upperBound);
	}
	
	public Constraint(String prefix, String suffix, RelationshipObject constraintObject, Expression expression, float lowerBound, float upperBound) {
		super(expression);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.prefix = prefix;
		this.suffix = suffix;
		this.constraintObject = constraintObject;
    	get().addConstraint(this);
	}

	public RelationshipObject getConstraintObject() {
		return constraintObject;
	}


	public float getLowerBound() {
		return lowerBound;
	}

	public float getUpperBound() {
		return upperBound;
	}
	
	@Override
	public String getName() {
		if(name == null)
		{
			name = new StringBuilder(prefix).append(constraintObject.getName()).append(suffix).toString();
		}
		return name;
	}

	@Override
	public String toString() {
		return String.format("%1$s Lowerbound: %2$s Upperbound: %3$s", super.toString(), getLowerBound(), getUpperBound());
	}

	public EqualityType getEqualityType() {
		if(getLowerBound() == getUpperBound()) return EqualityType.EQUAL_TO;
		if(getUpperBound() < Float.MAX_VALUE) return EqualityType.LESS_THAN;
		return EqualityType.GREATER_THAN;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public double getDualValue()
	{
		return getValue();
	}
}
