package examples.server.fare;

import yogi.base.app.ErrorReporter;
import yogi.base.relationship.RelationshipAssistant;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.action.Action;
import yogi.server.base.purge.PurgeableAssistant;

import examples.server.fare.farekey.FareKey;
import examples.server.fare.farekey.FareKeyManager;
import examples.server.sub.Sub;

public class FareAssistant extends RelationshipAssistant<Fare> {
	private static FareAssistant itsInstance = new FareAssistant();
	public static String PurgeDeleteDir="";
	
	public static FareAssistant get() {
		return itsInstance;
	}

	
	public void markForPurge(FareKeyManager manager, FareManager fareManager, Sub sub)
	{
		for(FareKey key: FareKeyManager.get().findAll())
		{
			ImmutableList<Fare> records = fareManager.getRecords(key);
			markObjectsForPurge(key, records, sub);
		}
	}
	
	private void markObjectsForPurge(FareKey key, ImmutableList<Fare> records, Sub sub)
	{
			int size = records.size();
			if(size == 0) {//Theoretically this case should never happen
				ErrorReporter.get().warning(key.getClass().getName() +" Keys without Records identified during Purge ", key);
				return;
			}
			boolean flag = false;
			for(int i = 0; i < size-1; i ++)
			{
				Fare record = records.get(i);
				if(record.getVersion().compareTo(sub)<0)
				{
					PurgeableAssistant.get().markForpurge(record);
				}else{
					flag = true;
					break;
				}
			}
			if(flag) return;
			Fare lastRecord = records.get(size-1);
			if(lastRecord.getVersion().compareTo(sub)<0 && lastRecord.getAction()==Action.Cancel)
			{
				PurgeableAssistant.get().markForpurge(lastRecord);
				PurgeableAssistant.get().markForpurge(key);
			}
	}
	
}
