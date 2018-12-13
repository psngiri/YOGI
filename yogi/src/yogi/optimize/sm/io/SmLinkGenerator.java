package yogi.optimize.sm.io;

import java.util.List;

import yogi.base.io.link.LinkGeneratorImpl;
import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableManager;

public class SmLinkGenerator extends LinkGeneratorImpl<SMVariableCreator, Variable>{

	public SmLinkGenerator() {
		super(new SmLinkComparator());
	}


	@Override
	protected void buildLink(SMVariableCreator from, Variable to) {
		from.setVariable(to);
	}


	@Override
	public List<Variable> getToObjects() {
		return VariableManager.get().findAll();
	}

}
