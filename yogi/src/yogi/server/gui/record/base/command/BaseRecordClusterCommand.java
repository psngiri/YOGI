package yogi.server.gui.record.base.command;

import java.util.List;

import yogi.base.relationship.RelationshipObject;
import yogi.cluster.FactoryClusterCommand;
import yogi.server.gui.record.base.BaseRecord;

public abstract class BaseRecordClusterCommand<K extends RelationshipObject, T extends BaseRecord<K>> extends FactoryClusterCommand<T> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 635081447518599392L;

	public BaseRecordClusterCommand(List<T> objects) {
		super(objects);
	}

	public BaseRecordClusterCommand(T object) {
		super(object);
	}

//	@Override
//	protected ObjectValidator<T> getValidator() {
//		return new BaseRecordStartValidator<K, T>(false);
//	}

	@Override
	protected long getValidationTimestamp() {
		return getFactory().getLatestObject().getTimeStamp();
	}

}
