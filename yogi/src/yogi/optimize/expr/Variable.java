package yogi.optimize.expr;

import java.util.ArrayList;
import java.util.List;

import yogi.base.relationship.RelationshipObject;
import yogi.base.util.immutable.ImmutableList;

import static yogi.optimize.expr.VariableFactory.get;

public class Variable{
	private int id = -1;
	private RelationshipObject variableObject;
	private float lowerBound;
	private float upperBound;
	private double solutionValue;
	private String name;
	private List<LinearExpressionTerm> terms = new ArrayList<LinearExpressionTerm>();
    
	public Variable(RelationshipObject variableObject, float lowerBound, float upperBound, String name) {
		super();
		this.variableObject = variableObject;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.name = name;
		get().addVariable(this);
	}

	public Variable(RelationshipObject variableObject, String name) {
		this(variableObject, Float.MIN_VALUE, Float.MAX_VALUE, name);
	}

	public Variable(RelationshipObject variableObject, float upperBound, String name) {
		this(variableObject, Float.MIN_VALUE, upperBound, name);
	}

	public float getLowerBound() {
		return lowerBound;
	}
	public double getSolutionValue() {
		return solutionValue;
	}
	public boolean isAssigned() {
		return solutionValue > 0;
	}
	public RelationshipObject getVariableObject() {
		return variableObject;
	}
	void setSolutionValue(double solutionValue) {
		this.solutionValue = solutionValue;
	}
	public float getUpperBound() {
		return upperBound;
	}
	public String getName()
	{
		return name;
	}
	
	boolean add(LinearExpressionTerm term)
	{
		synchronized(terms)
		{
			return terms.add(term);
		}
	}
	
	boolean remove(LinearExpressionTerm term)
	{
		synchronized(terms)
		{
			return terms.remove(term);
		}
	}

	@Override
	public String toString() {
		return String.format("Variable Name: %1$s Lowerbound: %2$s Upperbound: %3$s Solution Value: %4$s", getName(), getLowerBound(), getUpperBound(), getSolutionValue());
	}

	public ImmutableList<LinearExpressionTerm> getTerms() {
		return new ImmutableList<LinearExpressionTerm>(terms);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
}
