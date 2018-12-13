package yogi.optimize.expr.test;

import junit.framework.TestCase;

import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.relationship.RelationshipObject;
import yogi.base.util.immutable.ImmutableList;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.ConstraintManager;
import yogi.optimize.expr.DummyConstraintObject;
import yogi.optimize.expr.Expression;

public class ConstraintManagerTest extends TestCase {
	@Override
	protected void setUp() throws Exception {
		TestErrorReporter.start();
	}
	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
	}
	
	public void test() throws ObjectNotFoundException
	{
		RelationshipObject constraintObject = new DummyConstraintObject();
		Expression expression = new Expression();
		Constraint constraint = new Constraint("Prefix", "Sufix", constraintObject, expression);
		ImmutableList<Constraint> constraints = ConstraintManager.get().getConstraints(constraintObject);
		assertEquals(1, constraints.size());
		assertEquals(constraint, ConstraintManager.get().getConstraint(constraintObject, "Prefix", "Sufix"));
		assertTrue(ErrorReporter.get().getErrors().isEmpty());
	}
	public void test1() throws ObjectNotFoundException
	{
		RelationshipObject constraintObject = new DummyConstraintObject();
		Expression expression = new Expression();
		Constraint constraint = new Constraint("Prefix", "Sufix", constraintObject, expression);
		Constraint constraint1 = new Constraint("Prefix1", "Sufix1", constraintObject, expression);
		ImmutableList<Constraint> constraints = ConstraintManager.get().getConstraints(constraintObject);
		assertEquals(2, constraints.size());
		assertEquals(constraint, ConstraintManager.get().getConstraint(constraintObject, "Prefix", "Sufix"));
		assertEquals(constraint1, ConstraintManager.get().getConstraint(constraintObject, "Prefix1", "Sufix1"));
		assertTrue(ErrorReporter.get().getErrors().isEmpty());
	}
	public void test2() throws ObjectNotFoundException
	{
		RelationshipObject constraintObject = new DummyConstraintObject();
		Expression expression = new Expression();
		Constraint constraint = new Constraint("Prefix", "Sufix", constraintObject, expression);
		new Constraint("Prefix", "Sufix", constraintObject, expression);
		ImmutableList<Constraint> constraints = ConstraintManager.get().getConstraints(constraintObject);
		assertEquals(2, constraints.size());
		assertEquals(constraint, ConstraintManager.get().getConstraint(constraintObject, "Prefix", "Sufix"));
		assertTrue(!ErrorReporter.get().getErrors().isEmpty());
	}
}
