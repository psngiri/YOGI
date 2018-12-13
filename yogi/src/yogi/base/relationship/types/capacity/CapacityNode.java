package yogi.base.relationship.types.capacity;

import java.util.HashMap;
import java.util.Map;

import yogi.base.relationship.RelationshipObject;
import yogi.base.util.node.Node;

public class CapacityNode extends Node<Capacity> {
	private static Map<Class<?>, CapacityNode> cpacityNodesByClass = new HashMap<Class<?>, CapacityNode>();
	private static CapacityNode rootNode = new CapacityNode(RelationshipObject.class);
	
	private CapacityNode(Class<?> klass)
	{
		this(null, klass);
	}
	
	public static CapacityNode getRootNode()
	{
		return rootNode;
	}
	
	protected static void clear()
	{
		cpacityNodesByClass = new HashMap<Class<?>, CapacityNode>();
		rootNode = new CapacityNode(RelationshipObject.class);
	}
	
	private CapacityNode(CapacityNode parent, Class<?> klass) {
		super(parent);
		this.setData(new Capacity(this, klass));
		cpacityNodesByClass.put(klass, this);
	}
	
	public CapacityNode(CapacityNode parent, Class<?> klass, int startIndex, int initialCapacity) {
		super(parent);
		this.setData(new Capacity(this, klass, startIndex, initialCapacity));
		cpacityNodesByClass.put(klass, this);
		if(parent == null) {
			if(!rootNode.isLeaf()) throw new RuntimeException("Capacity Root Node has already been defined and it has some childrent too. Capacity Root Node should be created before creating any other capacity nodes.");
			rootNode = this;
		}
	}
	
	public static CapacityNode getNode(Class<?> from)
	{
		CapacityNode rtnValue = cpacityNodesByClass.get(from);
		if(rtnValue != null) return rtnValue;
		return createNode(from);
	}

	private static synchronized CapacityNode createNode(Class<?> from) {
		CapacityNode rtnValue = cpacityNodesByClass.get(from);
		if(rtnValue != null) return rtnValue;
		for(Class<?> klass: from.getInterfaces())
		{
			rtnValue = getNode(klass);
			if(rtnValue != null) return new CapacityNode(rtnValue, from);
		}
		return rtnValue;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String parentName = "ROOT";
		if(getParent() != null) parentName = getParent().getData().getKlass().getName();
		sb.append(parentName).append(' ');
		sb.append(getData().getKlass().getName()).append(' ');
		sb.append(getData().getRange()).append(' ');
		sb.append(getData().getCapacity());
		return sb.toString();
	}

}
