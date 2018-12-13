package yogi.server.gui.superuserpermissions.command;

import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.db.command.QueryExecutorDMLCommand;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;
import yogi.server.gui.superuserpermissions.SuperUserPermissionCreator;
import yogi.server.gui.superuserpermissions.SuperUserPermissionFactory;
import yogi.server.gui.superuserpermissions.SuperUserPermissionValidator;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;

public class AddSuperUserCommand extends QueryExecutorDMLCommand<Boolean> {

	private static final long serialVersionUID = 7166513806743562911L;
	public static String query="Insert into SUPERUSER_PERMISSIONS (USERID,USERENTITY,ISGROUP) values (?,?,?)";
	private String superUser;

	private String userEntity;

	public AddSuperUserCommand(String userId,String superUser, String userEntity) {
		super(userId);
		this.superUser = superUser;
		this.userEntity = userEntity;
	}
	@Override
	public Boolean executeCommand() {
		try {
			int numOfRows = QueryExecutor.get().executeUpdate(getDbResource(), query,  getSuperUser(),getUserEntity(), "N");
			if(numOfRows>0){
				
				SuperUserPermissionCreator creator = new SuperUserPermissionCreator();
				User user = UserManager.get().getUser(getSuperUser());
				creator.setSuperUser(user);
				User user2 = UserManager.get().getUser(getUserEntity());
				creator.setUser(user2);
				creator.setUserGroup(null);
				SuperUserPermissionFactory.get().create(creator, new SuperUserPermissionValidator());
				return SuperUserPermissionAssistant.get().setSuperUser(user, user2);
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return false;
	}
	public String getSuperUser() {
		return "AA"+superUser;
	}
	public String getUserEntity() {
		return "AA"+userEntity;
	}
}