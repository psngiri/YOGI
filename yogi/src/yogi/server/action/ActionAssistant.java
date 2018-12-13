package yogi.server.action;

import yogi.base.util.Pair;
import yogi.period.date.Date;
import yogi.period.date.range.DateRange;

/**
 * @author Vikram Vadavala
 *
 */

public class ActionAssistant {
	
	private static ActionAssistant itsInstance = new ActionAssistant();

	public static ActionAssistant get() {
		return itsInstance;
	}
		
	public Action getAction(int actionCode) {
		switch (actionCode)
		{
			case 1: return Action.Cancel;
			case 2: return Action.Add;
			case 3: return Action.Update;
		}
		throw new RuntimeException("Not a Valid Action Code" + actionCode);
    }
	
	public String getActionCode(Action action) {
		return String.valueOf(action.getValue());
    }
	
	public int getActionNumber(String action, float fareAmt) {
		if(fareAmt==0){return 1;}
		if(action.equalsIgnoreCase("r")){return 3;}
		if(action.equalsIgnoreCase("i")){return 2;}
		if(Integer.parseInt(action.trim())==1){return 1;}
		if(Integer.parseInt(action.trim())==2){return 2;}
		if(Integer.parseInt(action.trim())==3){return 3;}
		throw new RuntimeException("Not a Valid Action" + action);
	}
	
	public int getActionNumber(String action) {
		if(action.equalsIgnoreCase("r")){return 3;}
		if(action.equalsIgnoreCase("i")){return 2;}
		if(Integer.parseInt(action.trim())==1){return 1;}
		if(Integer.parseInt(action.trim())==2){return 2;}
		if(Integer.parseInt(action.trim())==3){return 3;}
		return 1;
	}
	
	public Action getAction(String action, float fareAmt) {
		if(fareAmt==0){
			return Action.Cancel;
		}
		if(action.equalsIgnoreCase("r")){return Action.Update;}
		if(action.equalsIgnoreCase("i")){return Action.Add;}		
		else return itsInstance.getAction(Integer.parseInt(action));		
    }	
	
	// TO DO: RE-VISIT THIS LATER! 
	public Pair<DateRange, Action> getDateRangeAction(Date eff_date, Date disc_date, Action action) {
		DateRange dateRange = null;
		Action action_code = null;
		//Taking off '0' considering EffectiveDate & Discontinue Date may be same and it's a valid date range.
		//if (eff_date.compareTo(disc_date) >= 0)
		if (eff_date.compareTo(disc_date) > 0)
		{
			dateRange = new DateRange(eff_date, eff_date);
			action_code = ActionAssistant.get().getAction(1); //set action = cancel
		}else{
			dateRange = new DateRange(eff_date, disc_date);
			action_code = action;
		}
		return new Pair<DateRange, Action>(dateRange,action_code);
    }	
	
	
	
	/**
	 * @param Action Code
	 * @return Int value for action code. 
	 * Is been used or loading gfs pending data to database.
	 */
	public int getActionValue(String code){
		if(code.equals("X")) return 1;
		if(code.equals("N")) return 2;
		return 3;
	}
	
}
