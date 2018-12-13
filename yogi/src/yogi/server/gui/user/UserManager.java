package yogi.server.gui.user;

import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;
import yogi.base.relationship.index.IndexedManager;

/**
 * @author Vikram Vadavala
 *
 */
public class UserManager extends IndexedManager<User, String> {
	private static UserManager itsInstance = new UserManager();
	private static UserCreator userCreator = new UserCreator();
	public static User BLANK = new UserImpl("");

	protected UserManager() {
		super();
	}

	public static UserManager get() {
		return itsInstance;
	}
	
	public User getUser(String userId)
	{
		if (null == userId || userId.trim().isEmpty()) {
			return BLANK;
		}
		userId = userId.trim();
		try {
			return this.getObject(userId);
		} catch (ObjectNotFoundException e) {
			return createUser(userId);
		}
	}

	private synchronized User createUser(String userId)
	{
		try {
			return this.getObject(userId);
		} catch (ObjectNotFoundException e) {
			ErrorReporter.get().finest("Creating User ", userId);
			userCreator.setId(userId);
			return UserFactory.get().create(userCreator);
		}
	}


}
