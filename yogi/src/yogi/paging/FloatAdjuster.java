package yogi.paging;

public class FloatAdjuster {

	private String changeFactor;
	private Float changePercentage;
	private Float differentialAmount;
	private boolean emptyAdjustmentFactor;
	
	public FloatAdjuster(String changeFactor, Float changePercentage, Float differentialAmount, boolean emptyAdjustmentFactor) {
		super();
		this.changeFactor = changeFactor;
		this.changePercentage = changePercentage;
		this.differentialAmount = differentialAmount;
		this.emptyAdjustmentFactor = emptyAdjustmentFactor;				
	}
	
	public String getChangeFactor() {
		return changeFactor;
	}
	
	public Float getChangePercentage() {
		return changePercentage;
	}

	public Float getDifferentialAmount() {
		return differentialAmount;
	}
		
	public boolean isEmptyAdjustmentFactor() {
		return emptyAdjustmentFactor;
	}
	
	public Float calculateAdjustments(float inputValue, float multiplyFactor) {
		Float adjustedValue = calculateAdjustedFare(inputValue);
		Float adjustedPremium = calculateAdjustedPremium(multiplyFactor);
		Float outputValue = adjustedValue + adjustedPremium;
		return outputValue;
	}
	
	private Float calculateAdjustedFare(float fareAmount){
		Float rtnValue = fareAmount;
		if(changeFactor != null) {
			Float percentageAdjustment = new Float(0);
			percentageAdjustment = ((fareAmount * changePercentage) / 100);
			if (changeFactor.equals("+")){
				rtnValue = fareAmount + percentageAdjustment;
			} else if(changeFactor.equals("-")){
				rtnValue = fareAmount - percentageAdjustment;
			} else if(changeFactor.equalsIgnoreCase("p")) {
				rtnValue = percentageAdjustment;
			} else { // default if no factor is provided
				rtnValue = fareAmount + percentageAdjustment;
			}
		}
		return rtnValue;
	}
	
	private Float calculateAdjustedPremium(float multiplyFactor){
		Float rtnValue = differentialAmount * multiplyFactor;
		return rtnValue;		
	}
	
	public String toString() {
		return "[ " + changeFactor + changePercentage.toString() + "%" + " ] " + differentialAmount.toString();
	}
}
