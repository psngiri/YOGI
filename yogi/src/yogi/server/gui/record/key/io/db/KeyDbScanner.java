package yogi.server.gui.record.key.io.db;

import java.sql.SQLException;

import yogi.base.app.ErrorReporter;
import yogi.base.io.CreatorScanner;
import yogi.base.io.db.DbRecord;
import yogi.server.gui.partition.PartitionManager;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyCreator;
import yogi.server.gui.user.UserManager;


/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <C>
 */
public abstract class KeyDbScanner<K extends Key, C extends KeyCreator<K>> implements CreatorScanner<K, C, DbRecord> {
 	
	public C scan(DbRecord dbRecord) {
		C creator = getCreator();
		try {
			scan(dbRecord, creator);			
		} catch (Exception e) {
			ErrorReporter.get().error(dbRecord,e);
		}
		return creator;
	}

	protected abstract C getCreator();

	protected void scan(DbRecord dbRecord, C creator) throws SQLException,Exception {
		creator.setUser(UserManager.get().getUser(dbRecord.getString(1).trim()));
		creator.setIdName(dbRecord.getString(2).trim());
		creator.setCreateDate(dbRecord.getLong(3));
		creator.setPartition(PartitionManager.get().getPartition(dbRecord.getString(4)));
	}

}