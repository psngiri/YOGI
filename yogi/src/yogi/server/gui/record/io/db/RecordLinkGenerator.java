package yogi.server.gui.record.io.db;

import java.util.List;

import yogi.base.io.link.LinkGeneratorImpl;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.io.RecordLinkComparator;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;

public class RecordLinkGenerator<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> extends LinkGeneratorImpl<C, K>{
	private KeyManager<K> keyManager;
	public RecordLinkGenerator(KeyManager<K> keyManager) {
		super(new RecordLinkComparator<K,D, T, C>());
		this.keyManager = keyManager;
	}

	@Override
	protected void buildLink(C from, K to) {
		from.setKey(to);
	}

	@Override
	public List<K> getToObjects() {
		return keyManager.findAll();
	}
}