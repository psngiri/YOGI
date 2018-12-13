package yogi.filecommand;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;

/**
 * @author Vikram Vadavala
 *
 */
public interface FileCommand extends RelationshipObject,Index<String> {
	String getFileName();
	String getCommand();
	String getArguments();
	boolean isRegularExpression();
}
