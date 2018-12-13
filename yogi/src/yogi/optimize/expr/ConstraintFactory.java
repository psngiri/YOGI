package yogi.optimize.expr;


import yogi.base.SynchronizedFactory;

public class ConstraintFactory extends SynchronizedFactory<Constraint> {
	private static ConstraintFactory constraintFactory = new ConstraintFactory(ConstraintManager.get());

	public ConstraintFactory(ConstraintManager manager) {
		super(manager);
	}

	public static ConstraintFactory get() {
		return constraintFactory;
	}

	void addConstraint(Constraint object) {
		int id = this.size();
		object.setId(id);
		super.add(object);
	}
	
}
