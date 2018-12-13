package yogi.report.server.command;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.applicationinfo.ApplicationInfoManager;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.partition.PartitionManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.userpartition.UserPartition;

public class GetApplicationsCommand extends CommandAdapter<List<String>> {

	private static final long serialVersionUID = 3121828568281563935L;

	public GetApplicationsCommand(String userId) {
		super(userId);
	}

	@Override
	public List<String> execute() {
		List<String> appNames = new ArrayList<String>();
		for(ApplicationInfo app :  ApplicationInfoManager.get().findAll()){
			User user = UserManager.get().getUser(getUserId());
			if(!validatePartition(user, app.getPartition())) continue;//checking if application is valid for the user partition
			if(ApplicationPermissionsAssistant.get().isAuthorized(app.getAppName(), user)){
				appNames.add(app.getAppName());
			}
		}
		return appNames;
	}

	
	public boolean validatePartition(User user, Partition appPartition) {
		UserPartition userPartition = PartitionAssistant.get().getUserPartition(user);
		Partition partition = userPartition.getPartition();
		if(appPartition==PartitionManager.ANY) return true;
		return appPartition==partition;
	}
	
}
