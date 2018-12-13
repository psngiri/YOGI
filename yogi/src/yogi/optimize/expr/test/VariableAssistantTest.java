package yogi.optimize.expr.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.relationship.RelationshipObject;
import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.VariableCreator;

import junit.framework.TestCase;

public class VariableAssistantTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		TestErrorReporter.start();
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		super.tearDown();
	}
	public void test()
	{
		AVariableCreator aVariableCreator = new AVariableCreator();
		VariableAssistant.get().setVariableCreator(A.class, aVariableCreator);
		assertEquals(aVariableCreator, VariableAssistant.get().getVariableCreator(A.class));
		assertEquals(aVariableCreator, VariableAssistant.get().getVariableCreator(C.class));
		BVariableCreator bVariableCreator = new BVariableCreator();
		VariableAssistant.get().setVariableCreator(B.class, bVariableCreator);
		assertEquals(bVariableCreator, VariableAssistant.get().getVariableCreator(B.class));
		assertEquals(aVariableCreator, VariableAssistant.get().getVariableCreator(C.class));
		CVariableCreator cVariableCreator = new CVariableCreator();
		VariableAssistant.get().setVariableCreator(C.class, cVariableCreator);
		assertEquals(cVariableCreator, VariableAssistant.get().getVariableCreator(C.class));
	}
	
	static interface A extends RelationshipObject
	{
		
	}
	static interface B extends A
	{
		
	}
	static interface C extends B
	{
		
	}
	
	static class AVariableCreator implements VariableCreator<A>
	{

		public Variable create(A variableObject) {
			return null;
		}
		
	}
	static class BVariableCreator implements VariableCreator<B>
	{

		public Variable create(B variableObject) {
			return null;
		}
		
	}
	static class CVariableCreator implements VariableCreator<C>
	{

		public Variable create(C variableObject) {
			return null;
		}
		
	}
}
