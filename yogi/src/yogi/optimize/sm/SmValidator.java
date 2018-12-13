package yogi.optimize.sm;

import yogi.base.app.ErrorReporter;
import yogi.base.validation.ValidatorAdapter;

public class SmValidator extends ValidatorAdapter<Sm> {

	@Override
	public boolean validate(Sm sm) {
		if(sm.getVariable() == null) 
		{
			ErrorReporter.get().error("Could not find VariableObject for Variable Name", sm.getVariableName());
			return false;
		}
		return true;
	}
	
}
