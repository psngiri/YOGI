package examples.server.sub.io.db;

import java.sql.SQLException;

import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;

import examples.server.sub.Sub;
import examples.server.sub.SubCreator;

public class SubDbScanner implements Scanner<Sub, DbRecord> {
	private SubCreator creator = new SubCreator();

	public SubCreator scan(DbRecord dbRecord) {
		try {
			creator.setSubNumber(dbRecord.getInt(1));
		} catch (SQLException e) {
			ErrorReporter.get().error((Object) "Error parsing dbRecord", e);
		}
		return creator;
	}
}
