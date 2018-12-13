package yogi.optimize.expr;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.Implementation;
import yogi.base.app.ErrorReporter;
import yogi.base.indexing.Indexer;
import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.logging.Logging;

public class VariableAssistant extends RelationshipAssistant<RelationshipObject> {
	private static VariableAssistant itsInstance = new VariableAssistant();
	private static OneToOneSimpleRelationship<RelationshipObject, Variable> variableRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(RelationshipObject.class, Variable.class, "Variable", false);
	Indexer<Class, VariableCreator> variableCreators = new Indexer<Class, VariableCreator>();
	private static Logger logger = Logging.getLogger(VariableAssistant.class);
	
	public static VariableAssistant get() {
		return itsInstance;
	}
	
	public static void clear() {
		itsInstance = new VariableAssistant();		
	}
	
	public void clearVariable(Variable variable) {		
		this.setRelationship(variable.getVariableObject(), variableRel, null);		
	}
	
	public Variable getVariable(RelationshipObject object)
	{
		Variable variable = this.getRelationship(object, variableRel);
		if(variable == null)
		{
			variable = createVariable(object);
			this.setRelationship(object, variableRel, variable);
		}
		return variable;
	}

	@SuppressWarnings("unchecked")
	private Variable createVariable(RelationshipObject object) {
		Class businnessInterface = Implementation.getInterface(object);
		VariableCreator variableCreator = getVariableCreator(businnessInterface);
		return variableCreator.create(object);
	}

	@SuppressWarnings("unchecked")
	private VariableCreator getSuperVariableCreator(Class myInterface) {
		Class superInterface = Implementation.getInterface(myInterface);
		if(superInterface == RelationshipObject.class) return null;
		VariableCreator variableCreator = variableCreators.get(superInterface);
		if(variableCreator == null)
		{
			variableCreator = getSuperVariableCreator(superInterface);
		}
		return variableCreator;
	}

	@SuppressWarnings("unchecked")
	public <T extends RelationshipObject> VariableCreator<T> getVariableCreator(Class<T> businnessInterface) {
		VariableCreator variableCreator = variableCreators.get(businnessInterface);
		if(variableCreator == null)
		{
			variableCreator = getSuperVariableCreator(businnessInterface);
			if(variableCreator == null) throw new RuntimeException("Variable Creator not set for Business Objects of Type: " + businnessInterface.getName());
			else {
				ErrorReporter.get().info("Variable Creator not defined for Business Objects of Type: " + businnessInterface + " using the variable creator definded for one of its parent: " + variableCreator.getClass().getName());
				variableCreators.index(businnessInterface, variableCreator);
			}
		}
		return variableCreator;
	}
	
	public <T extends RelationshipObject> VariableAssistant setVariableCreator(Class<T> businnessInterface, VariableCreator<T> variableCreator) {
		variableCreators.index(businnessInterface, variableCreator);
		return this;
	}
	
	public void setSolutionValue(Variable variable, double solutionValue)
	{
		variable.setSolutionValue(solutionValue);
		if (logger.isLoggable(Level.FINE))
		{
			logger.fine("Variable.setSolutionValue: "+variable.getName()+" : "+ solutionValue);
		}
	}
}
