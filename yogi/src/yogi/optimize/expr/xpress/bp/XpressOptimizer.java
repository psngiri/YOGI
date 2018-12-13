package yogi.optimize.expr.xpress.bp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.BaseProcessor;
import yogi.base.app.ErrorReporter;
import yogi.optimize.expr.BaseConstraint;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.ConstraintAssistant;
import yogi.optimize.expr.ConstraintManager;
import yogi.optimize.expr.EqualityType;
import yogi.optimize.expr.LinearExpressionTerm;
import yogi.optimize.expr.Objective;
import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.VariableManager;
import com.dashoptimization.XOctrl;
import com.dashoptimization.XPRB;
import com.dashoptimization.XPRBctr;
import com.dashoptimization.XPRBprob;
import com.dashoptimization.XPRBvar;
import com.dashoptimization.XPRS;
import com.dashoptimization.XPRSconstants;
import com.dashoptimization.XPRSprob;

public class XpressOptimizer extends BaseProcessor {
	public static boolean Run = true;
	public static int NumberOfDecimalPlaces = 4;
	private XPRB bcl;
	private XPRBprob problem;
	private List<XPRBvar> xpressVaribales;
	private List<XPRBctr> xpressConstraints;
	private XPRBctr xpressObjective;
	private int variableIndex = 0;
	private int constraintIndex = 0;
	private String name ="default";
	private String algorithmType = "b";
	public static int NumThreads = 8;
	public static int Debug =0;
	public static int sense = 1; //1:maximize, 0:minimize
	private int LPStatus;
	private int MIPStatus;
	private Integer numberOfRun=0;
	public static int NumberOfMatrixOutput = 2;

	public XpressOptimizer(String name) {
		super();
		this.name = name;
	}


	protected void init()
	{
		bcl = new XPRB();
		problem = bcl.newProb(name);
		XPRS.init();
		XPRSprob prob = problem.getXPRSprob();
		prob.setIntControl(XOctrl.XPRS_BARTHREADS, NumThreads);
		//prob.setIntControl(XPRS.BARPRESOLVEOPS,5);
		prob.setIntControl(XPRSconstants.PRESOLVE, 1);
		xpressVaribales = new ArrayList<XPRBvar>();
		xpressConstraints = new ArrayList<XPRBctr>();
		xpressObjective = problem.newCtr(Objective.objectiveFunction.getName());
		problem.setObj(xpressObjective);
		problem.setSense(sense);
		problem.setColOrder(1);
	}

	public void run() {

		if(variableIndex == 0) init();
		compressConstraints();
		List<Constraint> constraints = ConstraintManager.get().findAll();

		createNewConstraints(constraints);

		List<Variable> variables = VariableManager.get().findAll();

		createNewVariablesAndBridgeOldConstraints(variables);

		bridgeNewConstraints(constraints);
		problem.setMsgLevel(0);
		if (Debug==1)
		{
			problem.setMsgLevel(3);
			numberOfRun++;
			if (numberOfRun <= NumberOfMatrixOutput)
			{
			String fileName = ApplicationProperties.OutputLocation +"/matrix.lp";
			fileName = fileName.concat(numberOfRun.toString());
			try {
				problem.exportProb(XPRB.LP, fileName);
				//MpsWriter writer = new MpsWriter(fileName);
				//writer.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}
		problem.solve(algorithmType);

		LPStatus=getLPStat();
		if (!algorithmType.equalsIgnoreCase("bg"))
		{
			if (LPStatus ==1)
			{
				for(Variable variable: variables)
				{
					XPRBvar xpressVariable = getXpressVariable(variable);
					VariableAssistant.get().setSolutionValue(variable, xpressVariable.getSol());
				}
				for(Constraint constraint: constraints)
				{
					XPRBctr xpressConstraint = getXpressConstraint(constraint);
					ConstraintAssistant.get().setDualValue(constraint, xpressConstraint.getDual());
				}
				ConstraintAssistant.get().setObjectiveValue(problem.getObjVal());
			}
			else
			{
				if (LPStatus == 2)
					ErrorReporter.get().warning("LP is Infeasible");
				if (LPStatus == 4)
					ErrorReporter.get().warning("LP is Unfinished");
				if (LPStatus == 5)
					ErrorReporter.get().warning("LP is Unbounded");
	
				ErrorReporter.get().warning("LP cannot be solved! Error code = " + LPStatus);
			}
		}
		else
		{
			MIPStatus = getMIPStat();
			if (MIPStatus ==4 || MIPStatus ==6)
			{
				for(Variable variable: variables)
				{
					XPRBvar xpressVariable = getXpressVariable(variable);
					VariableAssistant.get().setSolutionValue(variable, xpressVariable.getSol());
				}
				for(Constraint constraint: constraints)
				{
					XPRBctr xpressConstraint = getXpressConstraint(constraint);
					ConstraintAssistant.get().setDualValue(constraint, xpressConstraint.getDual());
				}
				ConstraintAssistant.get().setObjectiveValue(problem.getObjVal());
			}
			else
			{
				if (MIPStatus == 1)
					ErrorReporter.get().warning("LP has not been optimized");
				if (MIPStatus == 2)
					ErrorReporter.get().warning("LP has been optimized");
				if (MIPStatus == 3 || MIPStatus == 5)
					ErrorReporter.get().warning("No Integer Solution Found");
	
				ErrorReporter.get().warning("MIP cannot be solved! Error code = " + MIPStatus);
			}
		}
	}

	private void createNewConstraints(List<Constraint> constraints) {
		for(int i = constraintIndex; i < constraints.size(); i++)
		{
			Constraint constraint = constraints.get(i);
			XPRBctr xpressConstraint = problem.newCtr(constraint.getName());

			setRHS(constraint, xpressConstraint);
			xpressConstraints.add(xpressConstraint);
		}
		if(constraintIndex == 0) constraintIndex = constraints.size();
	}

	private void setRHS(Constraint constraint, XPRBctr xpressConstraint) {
		EqualityType type = EqualityType.EQUAL_TO;
		if(constraint.getLowerBound() == constraint.getUpperBound()) type= EqualityType.EQUAL_TO;
		else
			if(constraint.getUpperBound() < Float.MAX_VALUE) type= EqualityType.LESS_THAN;
		else
			if ((constraint.getLowerBound()!=Float.MIN_VALUE) &&(constraint.getUpperBound()==Float.MAX_VALUE)) type= EqualityType.GREATER_THAN;

		if (type ==EqualityType.EQUAL_TO)
		{
			xpressConstraint.setType(XPRB.E);
			xpressConstraint.setTerm(constraint.getLowerBound());
		}else if (type ==EqualityType.LESS_THAN){
			xpressConstraint.setType(XPRB.L);
			xpressConstraint.setTerm(constraint.getUpperBound());
		}else if (type ==EqualityType.GREATER_THAN){
			xpressConstraint.setType(XPRB.G);
			xpressConstraint.setTerm(constraint.getLowerBound());
		}else{
			xpressConstraint.setRange(constraint.getLowerBound(), constraint.getUpperBound());
		}
	}

	private void createNewVariablesAndBridgeOldConstraints(List<Variable> variables) {
		XPRBctr xpressConstraint = null;
		for(int i = variableIndex; i < variables.size(); i++)
		{
			Variable variable = variables.get(i);
			XPRBvar xpressVariable = null;
			if (variable.getName().charAt(0) == 'z') {
				xpressVariable = problem.newVar(variable.getName(), XPRB.BV, variable.getLowerBound(), variable.getUpperBound());
			} else
				xpressVariable = problem.newVar(variable.getName(), XPRB.PL, variable.getLowerBound(), variable.getUpperBound());
			xpressVaribales.add(xpressVariable);
			for(LinearExpressionTerm term: variable.getTerms())
			{
				BaseConstraint constraint = term.getExpression().getConstraint();
				if(constraint.getId() < constraintIndex)
				{
					xpressConstraint = getXpressConstraint(constraint);
					xpressConstraint.addTerm(xpressVariable, term.getCoefficient());
				}
			}
		}
		variableIndex = variables.size();
	}

	private void bridgeNewConstraints(List<Constraint> constraints) {
		if(constraintIndex != 0 && constraintIndex != constraints.size())
		{
			for(int i = constraintIndex; i < constraints.size(); i++)
			{
				Constraint constraint = constraints.get(i);

				XPRBctr xpressConstraint = getXpressConstraint(constraint);
				for(LinearExpressionTerm term: constraint.getExpression().getLinearExpressionTerms())
				{
					XPRBvar xpressVariable = getXpressVariable(term.getVariable());
					xpressConstraint.addTerm(xpressVariable, term.getCoefficient());
				}
			}
			constraintIndex = constraints.size();
		}
	}

	public XPRBctr getXpressConstraint(BaseConstraint constraint)
	{
		if(constraint.getId() < 0) return xpressObjective;
		return xpressConstraints.get(constraint.getId());
	}

	public XPRBvar getXpressVariable(Variable variable)
	{
		return xpressVaribales.get(variable.getId());
	}

	private void compressConstraints() {
		Objective.objectiveFunction.compress();
		List<Constraint> constraints = ConstraintManager.get().findAll();
		for(Constraint constraint: constraints)
		{
			constraint.compress();
		}
	}

	public XPRB getBcl() {
		return bcl;
	}

	public XPRBprob getProblem() {
		return problem;
	}

	public boolean isActivated() {
		return Run;
	}

	public String getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}

	public void useBarrierAlgorithm() {
		setAlgorithmType("b");
	}

	public void usePrimalSimplexAlgorithm() {
		setAlgorithmType("p");
	}

	public void useBarrierAlgorithmMixedInteger() {
		setAlgorithmType("bg");
	}

	public List<XPRBvar> getXpressVariables()
	{
		return xpressVaribales;
	}

	public int getLPStat()
	{
		return problem.getLPStat();
	}
	
	public int getMIPStat()
	{
		return problem.getMIPStat();
	}
	
	public void addToRHS(Constraint constraint, double valueToAddToRHS)
	{
		XPRBctr xpressConstraint = getXpressConstraint(constraint);
		xpressConstraint.setTerm(xpressConstraint.getRHS() + valueToAddToRHS);
	}
	
	public void changeToRHS(Constraint constraint, double valueToChangeToRHS)
	{
		XPRBctr xpressConstraint = getXpressConstraint(constraint);
		xpressConstraint.setTerm(valueToChangeToRHS);
	}
	
	public boolean isFeasable() {
		return (problem.getLPStat() == 1 || problem.getMIPStat() == 4 || problem.getMIPStat()==6);
	}
	
	public void printMatrix(String file){
		try {
			problem.exportProb(XPRB.LP, file);
		} catch (IOException e) {}
	}
}
