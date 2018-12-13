package yogi.base.relationship.types.capacity.test;


//import yogi.base.Base;
import yogi.base.Factory;
//import yogi.base.Implementation;
//import yogi.base.relationship.types.capacity.CapacityNode;
//import yogi.osm.bm.retime.flight.RetimeFlightImpl;
//import yogi.osm.core.emogt.rule.EmogtRuleImpl;
//import yogi.osm.core.flight.Flight;
//import yogi.osm.core.puck.Puck;
//import yogi.osm.core.puck.turn.Turn;
//import yogi.osm.core.puck.turn.TurnImpl;

import junit.framework.TestCase;

public class CapacityNodeTest extends TestCase {

	
	@Override
	protected void setUp() throws Exception {
		Factory.clearAllFactories();
//		CapacityNode.clear();
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		Factory.clearAllFactories();
//		CapacityNode.clear();
	}
	public void test()
	{
		
	}

//	public void test2()
//	{
//		Class klass = TurnImpl.class;
//		Class firstInterface = Implementation.getInterface(klass);
//		CapacityNode node = CapacityNode.getNode(firstInterface);
//		assertEquals(node, CapacityNode.getNode(firstInterface));
//		assertEquals(3, CapacityNode.getRootNode().getMyLeaves().size());
//		assertEquals("Puck", node.getParent().getData().getKlass().getSimpleName());
//		assertEquals("Base", node.getParent().getParent().getData().getKlass().getSimpleName());
//		assertEquals(CapacityNode.getRootNode(), node.getParent().getParent().getParent());
//		
//	}
//	
//	public void test3()
//	{
//		Class klass = EmogtRuleImpl.class;
//		Class firstInterface = Implementation.getInterface(klass);
//		CapacityNode node = CapacityNode.getNode(firstInterface);
//		assertEquals(null, node);		
//	}
//	
//	public void test4()
//	{
//		Class klass = RetimeFlightImpl.class;
//		Class firstInterface = Implementation.getInterface(klass);
//		CapacityNode node = CapacityNode.getNode(firstInterface);
//		assertEquals(3, CapacityNode.getRootNode().getMyLeaves().size());
//		assertEquals("Flight", node.getParent().getData().getKlass().getSimpleName());
//		assertEquals("Base", node.getParent().getParent().getData().getKlass().getSimpleName());
//		assertEquals(CapacityNode.getRootNode(), node.getParent().getParent().getParent());
//	}
//	
//	public void test5()
//	{
//		Class klass = RetimeFlightImpl.class;
//		Class firstInterface = Implementation.getInterface(klass);
//		CapacityNode retimeFlightNode = CapacityNode.getNode(firstInterface);
//		CapacityNode flightNode = (CapacityNode) retimeFlightNode.getParent();
//		CapacityNode baseNode = (CapacityNode) flightNode.getParent();
//		assertEquals(0, retimeFlightNode.getData().getNextIndex());
//		assertEquals(1, flightNode.getData().getNextIndex());
//		assertEquals(2, baseNode.getData().getNextIndex());
//		assertEquals(3, retimeFlightNode.getData().getNextIndex());
//		assertEquals(4, flightNode.getData().getNextIndex());
//		assertEquals(5, baseNode.getData().getNextIndex());
//		assertEquals(6, baseNode.getData().getNextIndex());
//		assertEquals(7, flightNode.getData().getNextIndex());
//		assertEquals(8, flightNode.getData().getNextIndex());
//		assertEquals(9, retimeFlightNode.getData().getNextIndex());
//		assertEquals(10, CapacityNode.getNode(Flight.class).getData().getNextIndex());
//		assertEquals(11, CapacityNode.getNode(Base.class).getData().getNextIndex());
//		assertEquals(0, CapacityNode.getNode(Turn.class).getData().getNextIndex());
//		assertEquals(1, CapacityNode.getNode(Puck.class).getData().getNextIndex());
//		assertEquals(12, CapacityNode.getNode(Base.class).getData().getNextIndex());
//		assertEquals(3, CapacityNode.getNode(Turn.class).getData().getNextIndex());
//		assertEquals(4, CapacityNode.getNode(Puck.class).getData().getNextIndex());
//		assertEquals(7, CapacityNode.getNode(Puck.class).getData().getNextIndex());
//		assertEquals(8, CapacityNode.getNode(Turn.class).getData().getNextIndex());
//	}
	
}
