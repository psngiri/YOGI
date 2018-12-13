package examples.server.fare.farekey.io.db;

import java.sql.SQLException;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;

import examples.server.carrier.CarrierManager;
import examples.server.fare.farekey.FareKey;
import examples.server.fare.farekey.FareKeyCreator;
import examples.server.market.MarketManager;
/**
 * @author Vikram Vadavala
 *
 */

public class FareKeyDbScanner implements Scanner<FareKey, DbRecord> {
	private FareKeyCreator creator = new FareKeyCreator();

	public FareKeyCreator scan(DbRecord dbRecord) {
		try {
			creator.setId(dbRecord.getInt(1));
			creator.setCarrier(CarrierManager.get().getCarrier(dbRecord.getString(2)));
			creator.setMarket(MarketManager.get().getMarket(dbRecord.getString(3)));
		} catch (SQLException e) {
			ErrorReporter.get().error(dbRecord,e);
		}
		return creator;	
	}
}
