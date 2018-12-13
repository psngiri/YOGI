package yogi.optimize.expr.io;

import java.util.Comparator;

import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.ConstraintFactory;

public class ConstraintDualValueWriter extends FactoryWriter<Constraint> {
	public static boolean RUN = true;
	public ConstraintDualValueWriter(String fileName) {
		super(ConstraintFactory.get(), new FileWriter<Constraint>(fileName, new ConstraintDualValueFormatter()),
				null);
		this.setComparator(myComparator);
	}
	

	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	private static Comparator<Constraint> myComparator = new Comparator<Constraint>(){

		public int compare(Constraint constraint1, Constraint constraint2) {
			return constraint1.getName().compareTo(constraint2.getName());
		}};
}
