package yogi.optimize.expr.io;

import yogi.base.io.Formatter;
import yogi.optimize.expr.Variable;

public class VariableFormatter implements Formatter<Variable> {

	public String format(Variable variable) {
		// TODO Auto-generated method stub
		return "Name:"+variable.getName()+"      Solution Value:"+variable.getSolutionValue();		
	}

}
