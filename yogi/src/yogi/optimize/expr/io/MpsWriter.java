package yogi.optimize.expr.io;

import java.util.List;

import yogi.base.io.BaseHeaderFileWriter;
import yogi.base.io.Writer;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.ConstraintManager;
import yogi.optimize.expr.LinearExpressionTerm;
import yogi.optimize.expr.Objective;
import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableManager;


import static yogi.optimize.expr.mps.MpsBound.getMpsBound;
import static yogi.optimize.expr.mps.MpsColumn.getMpsColumn;
import static yogi.optimize.expr.mps.MpsHeader.getMpsHeader;
import static yogi.optimize.expr.mps.MpsOther.getMpsOther;
import static yogi.optimize.expr.mps.MpsRange.getMpsRange;
import static yogi.optimize.expr.mps.MpsRhs.getMpsRhs;
import static yogi.optimize.expr.mps.MpsRow.getMpsRow;

public class MpsWriter extends BaseHeaderFileWriter implements Writer{
	public static boolean RUN = true;
	public MpsWriter(String fileName) {
		super(fileName);
	}

	public void write()
	{
		open();
		Objective.objectiveFunction.compress();
		List<Constraint> constraints = ConstraintManager.get().findAll();
		for(Constraint constraint: constraints)
		{
			constraint.compress();
		}
		
		List<Variable> variables = VariableManager.get().findAll();
		
		writeHeader();
		writeRows(constraints);
		writeColumns(variables);
		writeRhs(constraints);
		writeRanges(constraints);
		writeBounds(variables);
		writeEnd();
		close();
	}

	private void writeEnd() {
		writer.println(getMpsHeader().initialize("ENDATA").format());
	}

	private void writeBounds(List<Variable> variables) {
		writer.println(getMpsHeader().initialize("BOUNDS").format());
		for(Variable variable: variables)
		{
			if(getMpsBound().initialize(variable).isValid()) writer.println(getMpsBound().format());
		}
	}

	private void writeRanges(List<Constraint> constraints) {
		writer.println(getMpsHeader().initialize("RANGES").format());
		for(Constraint constraint: constraints)
		{
		    if(getMpsRange().initialize(constraint).isValid()) writer.println(getMpsRange().format());
		}
	}

	private void writeRhs(List<Constraint> constraints) {
		writer.println(getMpsHeader().initialize("RHS").format());
		for(Constraint constraint: constraints)
		{
		    if(getMpsRhs().initialize(constraint).isValid()) writer.println(getMpsRhs().format());
		}
	}

	private void writeColumns(List<Variable> variables) {
		writer.println(getMpsHeader().initialize("COLUMNS").format());
		for(Variable variable: variables)
		{
			for(LinearExpressionTerm term: variable.getTerms())
			{
				if(getMpsColumn().initialize(term).isValid()) writer.println(getMpsColumn().format());
			}
		}
	}

	private void writeRows(List<Constraint> constraints) {
		writer.println(getMpsHeader().initialize("ROWS").format());
		writer.println(getMpsOther().initialize("N    obj").format());
		for(Constraint constraint: constraints)
		{
		    writer.println(getMpsRow().initialize(constraint).format());
		}
	}

	private void writeHeader() {
		writer.println(getMpsHeader().initialize("NAME").format());
	}

	public boolean isActivated() {
		return RUN;
	}
}
