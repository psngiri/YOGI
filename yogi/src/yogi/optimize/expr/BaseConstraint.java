package yogi.optimize.expr;

public class BaseConstraint {
	private int id = -1;
	private double value;
	private Expression expression;

	public BaseConstraint(Expression expression) {
		super();
		this.expression = expression;
		expression.setConstraint(this);
	}

	public void clear() {
		this.expression = new Expression();
		expression.setConstraint(this);
	}
	
	public String getName() {
		return "";
	}

	public Expression getExpression() {
		return expression;
	}

	public Expression plus(LinearExpressionTerm linearExpressionTerm) {
		expression.plus(linearExpressionTerm);
		return expression;
	}

	public Expression minus(LinearExpressionTerm linearExpressionTerm) {
		expression.minus(linearExpressionTerm);
		return expression;
	}

	public void compress() {
		getExpression().compress();
	}
	
	@Override
	public String toString() {
		return String.format("Constraint Name: %1$s expression: %2$s", getName(), getExpression());
	}
	
	void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	protected double getValue() {
		return value;
	}

	void setValue(double value) {
		this.value = value;
	}

}
