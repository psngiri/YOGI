package yogi.server.gui.applicationinfo;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.partition.Partition;

public class ApplicationInfoImpl extends RelationshipObjectImpl<ApplicationInfo> implements ApplicationInfo
{
	private String applicationName;
	private Partition partition;

	protected ApplicationInfoImpl(String applicationName, Partition partition)
	{
		super();
		this.applicationName = applicationName;
		this.partition = partition;
	}
	
	public String getAppName()
	{
		return applicationName;
	}

	public String getName()
	{
		return applicationName +"/"+partition;
	}
	
	@Override
	public String getIndex() {
		return applicationName;
	}
	
	public Partition getPartition() {
		return partition;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}	
		
	public int compareTo(ApplicationInfo o)
	{
		int result = 0;
		if(this == o) return result;
		int i = applicationName.compareTo(o.getAppName()); 
		if(i != 0) result = partition.compareTo(o.getPartition());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		return this == obj;
	}
}
