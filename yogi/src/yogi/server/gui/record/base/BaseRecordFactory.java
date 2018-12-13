package yogi.server.gui.record.base;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Manager;
import yogi.base.relationship.RelationshipObject;
import yogi.base.validation.ObjectValidator;
import yogi.cluster.ClusterFactory;
import yogi.server.gui.user.User;

public abstract class BaseRecordFactory<K extends RelationshipObject, T extends BaseRecord<K>> extends ClusterFactory<T> {
	
	protected BaseRecordFactory(Manager<T> manager) {
		this(manager, false);
	}

	protected BaseRecordFactory(Manager<T> manager, boolean addForClustering) {
		super(manager, addForClustering);
	}

	public synchronized List<Integer> create(List<BaseRecordCreator<K, T>> creators, ObjectValidator<T> validator, User user) {
		List<Integer> errors = new ArrayList<Integer>();
		List<T> objects = new ArrayList<T>(creators.size());
		long timeStamp = System.currentTimeMillis();
		for(int i = 0; i < creators.size(); i++){
			BaseRecordCreator<K,T> creator = creators.get(i);
			creator.setTimeStamp(timeStamp);
			creator.setModifiedByUser(user);
			T object = creator.create();
			if(object == null) errors.add(i);
			if(validator == null || validator.validate(object)) {
				objects.add(object);
			} else {
				errors.add(i);
			}			
		}
		if(errors.isEmpty()){
			add(objects);
		}
		return errors;
	}

}