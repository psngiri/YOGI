package yogi.server.gui.record;

import yogi.base.app.ErrorReporter;
import yogi.server.gui.record.base.BaseRecordValidator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class RecordValidator<K extends Key, D extends RecordData, T extends Record<K, D>> extends BaseRecordValidator<K,T> {
	
	public static int MAX_DESCRIPTION_LENGTH = 1000;
	public static int MAX_COMMENTS_LENGTH = 2000;

	@Override
	public boolean validate(T record) {
		if(super.validate(record) == false) {
			return false;
		}
		if(record.getData() == null) {
			ErrorReporter.get().warning("Data not Set for "+record.getClass().getSimpleName()+" Ignoring : ", record);
			return false;
		}
		if(record.getDescription() != null && record.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
			throw new RuntimeException("Description input exceeds max limit [" + MAX_DESCRIPTION_LENGTH + "] : " + record.getDescription());
		}
		if(record.getComments() != null && record.getComments().length() > MAX_COMMENTS_LENGTH) {
			throw new RuntimeException("Comments input exceeds max limit [" + MAX_COMMENTS_LENGTH + "] : " + record.getComments());
		}
		return validate(record.getData());
	}

	protected boolean validate(D data) {
		return true;
	}
}
