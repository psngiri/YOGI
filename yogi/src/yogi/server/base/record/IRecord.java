package yogi.server.base.record;

import yogi.server.base.key.IKey;
import yogi.server.base.purge.Purgeable;
import yogi.server.base.version.IVersion;

public interface IRecord<K extends IKey, V extends IVersion> extends Purgeable{
	K getKey();
	V getVersion();
}
