package yogi.server.base.rs;

import yogi.server.base.key.IKey;
import yogi.server.base.purge.Purgeable;
import yogi.server.base.record.IRecord;
import yogi.server.base.version.IVersion;

public interface IRS<K extends IKey, V extends IVersion, R extends IRecord<K,V>> extends Purgeable{
	R getRecord();
}
