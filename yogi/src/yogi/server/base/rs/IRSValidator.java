package yogi.server.base.rs;

import yogi.base.app.ErrorReporter;
import yogi.base.validation.ValidatorAdapter;
import yogi.server.base.key.IKey;
import yogi.server.base.record.IRecord;
import yogi.server.base.version.IVersion;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class IRSValidator<K extends IKey, V extends IVersion, T extends IRecord<K,V>, R extends IRS<K,V,T>> extends ValidatorAdapter<R> {
	@Override
	public boolean validate(R rs) {
		if(rs.getRecord() == null){
			ErrorReporter.get().warning("Record not found for Repeating Segment "+rs.getClass().getSimpleName(), rs);
			return false;
		}
		return true;
	}
}
