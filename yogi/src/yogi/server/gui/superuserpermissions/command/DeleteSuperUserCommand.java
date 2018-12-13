package yogi.server.gui.superuserpermissions.command;

import java.util.List;

import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.db.command.QueryExecutorDMLCommand;
import yogi.server.gui.superuserpermissions.SuperUserPermission;
import yogi.server.gui.superuserpermissions.SuperUserPermissionFactory;
import yogi.server.gui.superuserpermissions.SuperUserPermissionManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;

public class DeleteSuperUserCommand extends QueryExecutorDMLCommand<Boolean> {

	private static final long serialVersionUID = 7166513806743562911L;
	public static String deleteQuery="delete from SUPERUSER_PERMISSIONS where USERID=? and USERENTITY=?";
	private String superUser;
	private String userEntity;

	public DeleteSuperUserCommand(String userId,String superUser, String userEntity) {
		super(userId);
		this.superUser = superUser;
		this.userEntity = userEntity;
	}
	@Override
	public Boolean executeCommand() {
		try {
			int numOfRows = QueryExecutor.get().executeUpdate(getDbResource(), deleteQuery, getSuperUser(),getUserEntity());
			if(numOfRows>0){
				List<SuperUserPermission> superUserPermissions = SuperUserPermissionManager.get().getSuperUserPermissions(UserManager.get().getUser(getSuperUser()));
				User user = UserManager.get().getUser(getUserEntity());
				for(SuperUserPermission superUserPermission:superUserPermissions){
					if(superUserPermission.getUser() == user){
						SuperUserPermissionFactory.get().delete(superUserPermission);
					}
				}
				return true;
			}
		} catch (DBException e) {
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