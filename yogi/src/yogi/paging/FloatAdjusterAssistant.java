package yogi.paging;

public class FloatAdjusterAssistant {
	private static FloatAdjusterAssistant itsInstnace = new FloatAdjusterAssistant();

	public static FloatAdjusterAssistant get() {
		return itsInstnace;
	}
	
	public FloatAdjuster getFloatAdjuster(String adjustmentFactor) {
		String differentialAmount, changeFactor, changePercentage;
		boolean emptyAdjustmentFactor = false;
		if(adjustmentFactor == null || adjustmentFactor.trim().isEmpty()) {
			adjustmentFactor = "0";
			emptyAdjustmentFactor = true;
		} else {
			adjustmentFactor = adjustmentFactor.trim();
		}
				
		String[] splits = adjustmentFactor.split("&");
		if(splits.length > 1) {
			differentialAmount = splits[1];
			String percentStr = splits[0];
			if(percentStr.contains("%")) {
				if(percentStr.startsWith("p") || percentStr.startsWith("P") || percentStr.startsWith("-") || percentStr.startsWith("+")) {
					changeFactor = percentStr.substring(0, 1);				
					changePercentage = percentStr.substring(1, percentStr.length() - 1);
				} else {
					changeFactor = "+";
					changePercentage = percentStr.substring(0, percentStr.length() - 1);
				} 
			} else {
				changeFactor = " ";
				changePercentage = " ";
			}
		} else {
			if(adjustmentFactor.endsWith("&")) {
				differentialAmount = "";
				if(adjustmentFactor.contains("%")) {
					adjustmentFactor = adjustmentFactor.substring(0, adjustmentFactor.length() - 2);
					if(adjustmentFactor.startsWith("p") || adjustmentFactor.startsWith("P") || adjustmentFactor.startsWith("-") || adjustmentFactor.startsWith("+")) {
						changeFactor = adjustmentFactor.substring(0, 1);
						changePercentage = adjustmentFactor.substring(1);
					} else {
						changeFactor = "+";
						changePercentage = adjustmentFactor;
					} 
				} else {
					changeFactor = " ";
					changePercentage = " ";
				}
			} else if(adjustmentFactor.endsWith("%")) {
				differentialAmount = "";
				adjustmentFactor = adjustmentFactor.substring(0, adjustmentFactor.length() - 1);
				if(adjustmentFactor.startsWith("p") || adjustmentFactor.startsWith("P") || adjustmentFactor.startsWith("-") || adjustmentFactor.startsWith("+")) {
					changeFactor = adjustmentFactor.substring(0, 1);
					changePercentage = adjustmentFactor.substring(1);
				} else {
					changeFactor = "+";
					changePercentage = adjustmentFactor;
				}
			} else {
				differentialAmount = adjustmentFactor;
				changeFactor = "";
				changePercentage = "";
			}
		}
		
		Float changePercentageValue = 0f;
		Float differentialAmountValue = 0f;
		try {
			if(changePercentage != null && !changePercentage.isEmpty()) {
				changePercentageValue = Float.parseFloat(changePercentage);
			}
			if(differentialAmount != null && !differentialAmount.isEmpty()) {
				differentialAmountValue = Float.parseFloat(differentialAmount);
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("Please use the correct pattern [P]m%&[+/-]n where 'm' and 'n' can be any number : " + adjustmentFactor);
		}
		return new FloatAdjuster(changeFactor, changePercentageValue, differentialAmountValue, emptyAdjustmentFactor);
	}
	
	public float applyFactor(float inputValue, FloatAdjuster floatAdjuster, float multiplyFactor) {
		return floatAdjuster.calculateAdjustments(inputValue, multiplyFactor);
	}

	public float applyFactor(float inputValue, FloatAdjuster floatAdjuster) {
		return applyFactor(inputValue, floatAdjuster, 1);
	}

}
