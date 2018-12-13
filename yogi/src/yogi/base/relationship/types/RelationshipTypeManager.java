package yogi.base.relationship.types;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.Factory;
import yogi.base.FactoryListener;
import yogi.base.Implementation;
import yogi.base.Manager;
import yogi.base.indexing.ManyIndexer;
import yogi.base.relationship.types.capacity.CapacityNode;
import yogi.base.relationship.types.io.RelationshipTypeFormatter;
import yogi.base.util.logging.Logging;

public class RelationshipTypeManager extends Manager<RelationshipType>{
	private static RelationshipTypeManager relationshipManager = new RelationshipTypeManager();
	ManyIndexer<Class, RelationshipType> fromRelationshipMap = new ManyIndexer<Class, RelationshipType>();
	private static Logger logger = Logging.getLogger(RelationshipTypeManager.class);
	private static RelationshipTypeFormatter formatter = new RelationshipTypeFormatter();
	public static Level DefaultLevel=Level.FINE;
	public static RelationshipTypeManager get() {
		return relationshipManager;
	}

	protected RelationshipTypeManager() {
		super();
	}

	@Override
	public void setFactory(Factory<? extends RelationshipType> factory) {
		super.setFactory(factory);
		factory.addFactoryListener(new FactoryListener<RelationshipType>()
				{

					public void add(RelationshipType relationship) {
						relationship.setIndex(CapacityNode.getNode(relationship.getFrom()).getData().getNextIndex());
						if(logger.isLoggable(DefaultLevel)) logger.info("Created RelationshipType" + formatter.format(relationship));
						fromRelationshipMap.index(relationship.getFrom(), relationship);
					}

					public boolean delete(RelationshipType relationship) {
						return true;
					}

					public boolean clearAll() {
						return true;
					}
					
				});
	}
	
	public List<RelationshipType> getRelationships(Class implementation)
	{
		List<RelationshipType> rtnValue = new ArrayList<RelationshipType>();
		Class objectInterface;
		try {
			objectInterface = Implementation.getInterface(implementation);
		} catch (Exception e) {
			return rtnValue;
		}
		CapacityNode node = CapacityNode.getNode(objectInterface);
		if(node == null) return rtnValue;
		List<RelationshipType> list = fromRelationshipMap.get(node.getData().getKlass());
		if(list != null) rtnValue.addAll(list);
		while((node = (CapacityNode) node.getParent()) != null)
		{
			list = fromRelationshipMap.get(node.getData().getKlass());
			if(list != null) rtnValue.addAll(list);			
		}
		return rtnValue;
	}

	public int getInitialCapacity(Class implementation)
	{
		return CapacityNode.getNode(Implementation.getInterface(implementation)).getData().getInitialCapacity();
	}
}
