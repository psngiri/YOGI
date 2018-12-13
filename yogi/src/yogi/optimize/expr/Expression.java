package yogi.optimize.expr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.merge.MergeManager;

public class Expression {
	private List<LinearExpressionTerm> linearExpressionTerms = new ArrayList<LinearExpressionTerm>();
	private float constant;
	private BaseConstraint constraint;

	public Expression() {
		super();
	}

	public Expression plus(LinearExpressionTerm linearExpressionTerm) {
		linearExpressionTerms.add(linearExpressionTerm);
		linearExpressionTerm.setExpression(this);
		return this;
	}

	public Expression minus(LinearExpressionTerm linearExpressionTerm) {
		linearExpressionTerms.add(linearExpressionTerm.multiply(-1f));
		linearExpressionTerm.setExpression(this);
		return this;
	}
	
	public BaseConstraint getConstraint() {
		return constraint;
	}

	void setConstraint(BaseConstraint constraint) {
		this.constraint = constraint;
	}

	public boolean isEmpty()
	{
		return linearExpressionTerms.isEmpty();
	}
	
	@Override
	public String toString() {
		return linearExpressionTerms.toString();
	}

	public ImmutableList<LinearExpressionTerm> getLinearExpressionTerms() {
		return new ImmutableList<LinearExpressionTerm>(linearExpressionTerms);
	}

	public float getConstant() {
		return constant;
	}

	public void compress() {
		MergeManager.merge(linearExpressionTerms, comparator);
	}

	private static Comparator<LinearExpressionTerm> comparator = new Comparator<LinearExpressionTerm>() {

		public int compare(LinearExpressionTerm term1,
				LinearExpressionTerm term2) {
			return term1.getVariable().getName().compareTo(
					term2.getVariable().getName());
		}
	};

}
