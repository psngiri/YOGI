package yogi.optimize.expr.io;

import java.util.Comparator;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableFactory;

public class VariableWriter extends FactoryWriter<Variable> {
	public static boolean RUN = true;
	public VariableWriter(String fileName) {
		this(fileName, null);
	}
	
	public VariableWriter(String fileName, Selector<Variable> selector) {
		super(VariableFactory.get(), new FileWriter<Variable>(fileName, new VariableFormatter()),
				selector);
		this.setComparator(myComparator);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	private static Comparator<Variable> myComparator = new Comparator<Variable>(){

		public int compare(Variable variable1, Variable variable2) {
			return variable1.getName().compareTo(variable2.getName());
		}};
}
