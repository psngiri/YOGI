package yogi.server.gui.record.key;

import yogi.base.validation.ValidatorAdapter;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <T>
 */
public abstract class KeyValidator<K extends Key> extends ValidatorAdapter<K> {
	
	public static int MAX_NAME_LENGTH = 200;

	@Override
	public boolean validate(K key) {
		if(key.getIdName() != null && key.getIdName().length() > MAX_NAME_LENGTH) {
			throw new RuntimeException("Name input exceeds max limit [" + MAX_NAME_LENGTH + "] : " + key.getIdName());
		}
		return true;
	}
}
