package yogi.server.gui.record.base;

import yogi.base.app.ErrorReporter;
import yogi.base.relationship.RelationshipObject;
import yogi.base.validation.ValidatorAdapter;
import yogi.server.action.Action;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class BaseRecordValidator<K extends RelationshipObject, T extends BaseRecord<K>> extends ValidatorAdapter<T> {
	@Override
	public boolean validate(T record) {
		if(record.getKey() == null) {
			ErrorReporter.get().warning("Key not found for "+record.getClass().getSimpleName()+" Ignoring : ", record);
			return false;
		}
		if(record.getAction() == Action.Cancel) return true;
		return true;
	}

}
