package yogi.server.gui.user;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;

public class UserAssistant extends RelationshipAssistant<User>{

	private static UserAssistant itsInstance = new UserAssistant();
	private static OneToOneSimpleRelationship<User, String> employeeIdRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, String.class, "EmployeeId");
	private static OneToOneSimpleRelationship<User, String> carrierRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, String.class, "Carrier");

	public static UserAssistant get() {
		return itsInstance;
	}

	public String getEmployeeId(User user)
	{
		String employeeId = this.getRelationship(user, employeeIdRel);
		if(employeeId == null){
			if(!user.getId().isEmpty()) {
				employeeId = user.getId().substring(2);
			} else {
				employeeId = "";
			}
			this.setRelationship(user, employeeIdRel, employeeId);
		}
		return employeeId;
	}
	
	public boolean compare(String carrierUserId, String userId)
	{
		userId=String.format("%6s", userId).replace(' ', '0');
		carrierUserId = carrierUserId.substring(2);
		return userId.equals(carrierUserId);
	}
	
	public String getCarrier(User user)
	{
		String carrier = this.getRelationship(user, carrierRel);
		if(carrier == null){
			if(!user.getId().isEmpty()) {
				carrier = user.getId().substring(0, 2);
			} else {
				carrier = "";
			}
			this.setRelationship(user, carrierRel, carrier);
		}
		return carrier;
	}


}
