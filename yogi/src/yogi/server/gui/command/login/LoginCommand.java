package yogi.server.gui.command.login;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import yogi.auth.AuthUser;
import yogi.auth.AuthUserCommandAdapter;
import yogi.base.Selector;
import yogi.base.app.Executor;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserAssistant;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.command.UserPreferencesResetSuperUserCommand;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoManager;


public class LoginCommand extends AuthUserCommandAdapter<UserDetails> {

	private static final long serialVersionUID = -6849748273507429406L;
	public static String IgnoreCarriers = "US";
	public LoginCommand(String userId) {
		super(userId);
	}
	
	@Override
	public UserDetails execute() {
		UserDetails userDetails;
		try{
			
			AuthUser authUser = getAuthUser();
			String userIdTemp;
			if(authUser == null && getUserId() ==null){
				return null;
			}
			else if(authUser == null){
				userIdTemp=getUserId();
			} 
			else {
				userIdTemp=authUser.getUserId();
				if(userIdTemp == null) return null;
				while(userIdTemp.length() > 0 && userIdTemp.charAt(0) == '0')
				{
					userIdTemp = userIdTemp.substring(1);
				}
				userIdTemp=String.format("%6s", userIdTemp).replace(' ', '0');
			}
			final String myUserId = userIdTemp;
			List<UserInfo> userInfos = UserInfoManager.get().find(new Selector<UserInfo>(){

				@Override
				public boolean select(UserInfo item) {
					if(IgnoreCarriers.contains(item.getUser().getId().substring(0,2))) return false;
					return UserAssistant.get().getEmployeeId(item.getUser()).equals(myUserId);
				}
				
			});
			if(userInfos.size()<1) throw new Exception();
			
			Collections.sort(userInfos, new Comparator<UserInfo>() {

				@Override
				public int compare(UserInfo userInfo1, UserInfo userInfo2) {
					UserPreferencesKey userInfo1key = UserPreferencesKeyManager.get().getKey(userInfo1.getUser());
					UserPreferencesKey userInfo2key = UserPreferencesKeyManager.get().getKey(userInfo2.getUser());
					if(userInfo1key==null && userInfo2key==null) return 0;
					if(userInfo1key==null) return 1;
					if(userInfo2key==null) return -1;
					
					UserPreferences userInfo1latestRecord = UserPreferencesManager.get().getLatestRecord(userInfo1key);
					UserPreferences userInfo2latestRecord = UserPreferencesManager.get().getLatestRecord(userInfo2key);
					if(userInfo1latestRecord.getTimeStamp() == userInfo2latestRecord.getTimeStamp()) return 0;
					if(userInfo1latestRecord.getTimeStamp() < userInfo2latestRecord.getTimeStamp()) return 1;
					else return -1;
				}
				
			});
			for(UserInfo userInfo : userInfos){
				User user = userInfo.getUser();
				UserPreferencesResetSuperUserCommand command = new UserPreferencesResetSuperUserCommand(user.getId());				
				Executor.get().execute(command);
			}
			LinkedHashMap<String,UserInfo> userInfosMap = new LinkedHashMap<String,UserInfo>(userInfos.size());
			for(UserInfo userInfo:userInfos){
				userInfosMap.put(userInfo.getUser().getId().substring(0,2), userInfo);
			}
			userDetails = new UserDetails(userIdTemp, userInfosMap ,authUser);
		}catch (Exception e){
			throw new RuntimeException("User / User Preferences not found");
		}
		return userDetails;
	}
	
}