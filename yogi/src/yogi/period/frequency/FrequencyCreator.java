package yogi.period.frequency;

import java.io.Serializable;

import yogi.base.Creator;

public class FrequencyCreator implements Creator<Frequency>, Serializable {
	byte frequency;
	
	public FrequencyCreator() {
		super();
	}

	public Frequency create() {
		return FrequencyManager.get().getFrequency(frequency);
	}

	public byte getFrequency() {
		return frequency;
	}

	public void setFrequency(byte frequency) {
		this.frequency = frequency;
	}


}
