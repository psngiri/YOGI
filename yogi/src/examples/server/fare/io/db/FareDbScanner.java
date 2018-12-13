package examples.server.fare.io.db;

import java.sql.SQLException;

import yogi.base.app.ErrorReporter;
import yogi.base.io.CreatorScanner;
import yogi.base.io.db.DbRecord;
import yogi.server.action.ActionAssistant;

import examples.server.fare.Fare;
import examples.server.fare.FareCreator;
import examples.server.sub.SubManager;

public class FareDbScanner implements CreatorScanner<Fare, FareCreator, DbRecord> {

	public FareCreator scan(DbRecord dbRecord) {
		FareCreator creator = new FareCreator();
		try {
			creator.setKeyId(dbRecord.getInt(1));
			creator.setVersion(SubManager.get().getSub(dbRecord.getInt(2)));
			creator.setAction(ActionAssistant.get().getAction(dbRecord.getInt(3)));
			creator.setFareAmount(dbRecord.getInt(4));
		} catch (SQLException e) {
			ErrorReporter.get().error(dbRecord,e);
		}
		return creator;
	}
}
