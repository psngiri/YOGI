package yogi.server.gui.record;

import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;


/**
 * @author Vikram Vadavala
 *
 */

public interface Record<K extends Key, D extends RecordData> extends BaseRecord<K>, Key {
	D getData();
	String getDescription();
	String getComments();
}
