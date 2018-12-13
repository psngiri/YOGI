package yogi.base.relationship.index;

import yogi.base.app.BaseProcessor;
import yogi.base.relationship.RelationshipObject;

public class IndexProcessor<T extends RelationshipObject> extends BaseProcessor {
	
	private IndexAssistant<T> indexAssistant;

	public IndexProcessor(IndexAssistant<T> indexAssistant) {
		super();
		this.indexAssistant = indexAssistant;
	}

	@Override
	public void run() {
		indexAssistant.index();
	}

	@Override
	public boolean isActivated() {
		return true;
	}

}
