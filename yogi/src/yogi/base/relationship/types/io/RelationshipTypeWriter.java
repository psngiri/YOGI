package yogi.base.relationship.types.io;

import java.util.Comparator;

import yogi.base.Selector;
import yogi.base.app.ApplicationProperties;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.base.relationship.types.RelationshipType;
import yogi.base.relationship.types.RelationshipTypeFactory;



public class RelationshipTypeWriter extends FactoryWriter<RelationshipType> {
	public static String FileName = "relationshipTypes.dat";
	public static boolean RUN = true;

	public RelationshipTypeWriter(String fileName, Selector<RelationshipType> selector)
	{
		super(RelationshipTypeFactory.get(), new FileWriter<RelationshipType>(fileName, new RelationshipTypeFormatter()), selector);
		this.setComparator(new Comparator<RelationshipType>(){

			public int compare(RelationshipType o1, RelationshipType o2) {
				int rtnValue = o1.getFrom().getName().compareTo(o2.getFrom().getName());
				if(rtnValue != 0) return rtnValue;
				return o1.getIndex() - o2.getIndex();
			}
			
		});
	}
	
	public RelationshipTypeWriter(String fileName)
	{
		this(fileName, null);
	}
	
	public RelationshipTypeWriter()
	{
		this(ApplicationProperties.ConfigDirectoryLocation + "/" + FileName);
	}

	@Override
	public void write() {
		if(!RUN) return;
		super.write();
	}
	
}
