package yogi.server.base.record;

import yogi.base.app.ErrorReporter;
import yogi.base.validation.ValidatorAdapter;
import yogi.server.base.key.IKey;
import yogi.server.base.version.IVersion;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class IRecordValidator<K extends IKey, V extends IVersion, T extends IRecord<K,V>> extends ValidatorAdapter<T> {
	@Override
	public boolean validate(T record) {
		if(record.getKey() == null) {
			ErrorReporter.get().warning("Key not found for "+record.getClass().getSimpleName(), record);
			return false;
		}
		return true;
	}
}
