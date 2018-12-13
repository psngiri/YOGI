package yogi.server.gui.user.preferences;

import java.util.ArrayList;
import java.util.List;

import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;


public class UserPreferencesFieldsAssistant {
	public static final String ApplicationName = "UserPreferences";
	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String LOW_FARE_RT = "lowFareRT";
	public static final String LOW_FARE_OW = "lowFareOW";
	public static final String LOW_YIELD = "lowYield";
	public static final String HOST_CARRIER = "hostCarrier";
	public static String DefaultHostCarrier = "AA";
	private static UserPreferencesFieldsAssistant itsInstance = new UserPreferencesFieldsAssistant();
	
	public static UserPreferencesFieldsAssistant get() {
		return itsInstance;
	}
	
	public List<String> getDisabledFields(String userId){
		User user = UserManager.get().getUser(userId);
		List<String> fields = new ArrayList<String>();
		if(!isUserAuthorised(user)) fields.add(USER_ID);
		if(!ApplicationPermissionsAssistant.get().isAuthorized(ApplicationName, USER_TYPE, user)) fields.add(USER_TYPE);
		if(!ApplicationPermissionsAssistant.get().isAuthorized(ApplicationName, LOW_FARE_OW, user)) fields.add(LOW_FARE_OW);
		if(!ApplicationPermissionsAssistant.get().isAuthorized(ApplicationName, LOW_FARE_RT, user)) fields.add(LOW_FARE_RT);
		if(!ApplicationPermissionsAssistant.get().isAuthorized(ApplicationName, LOW_YIELD, user)) fields.add(LOW_YIELD);
		if(!ApplicationPermissionsAssistant.get().isAuthorized(ApplicationName, HOST_CARRIER, user)) fields.add(HOST_CARRIER);
		return fields;
	}

	public boolean isUserAuthorised(User user) {
		return ApplicationPermissionsAssistant.get().isAuthorized(ApplicationName, USER_ID, user);
	}
	

}
