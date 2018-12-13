package yogi.optimize.expr.xpress.bp;

import java.util.List;

import yogi.base.app.BaseProcessor;
import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableManager;
import yogi.optimize.fixbound.FixBoundVariableObjectAssistant;
import com.dashoptimization.XPRBvar;


public class FixBoundXpressProcessor extends BaseProcessor {
	public static boolean Run = true;
	private XpressOptimizer process;
	
	public FixBoundXpressProcessor(XpressOptimizer process) {
		super();
		this.process = process;
	}

	
	protected void init(){	
		
	}

	public void run() {
		List<Variable> variables = VariableManager.get().findAll();
		for(int i = 0; i < variables.size(); i++)
		{
			Variable variable = variables.get(i);
			if (FixBoundVariableObjectAssistant.get().getBound(variable.getVariableObject())!=null)
			{
				float bound = FixBoundVariableObjectAssistant.get().getBound(variable.getVariableObject());
				XPRBvar xpressVariable = process.getXpressVariable(variable);
				xpressVariable.setLB(bound);
				xpressVariable.setUB(bound);
				
			}
			else
			{
				XPRBvar xpressVariable = process.getXpressVariable(variable);			
				xpressVariable.setUB(variable.getUpperBound());
				xpressVariable.setLB(variable.getLowerBound());
			}
		}
	}

	public boolean isActivated() {		
		return Run;
	}

}
