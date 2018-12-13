package yogi.period.frequency;

import yogi.period.frequency.io.MondayToSundayFrequencyFormatter;

class FrequencyImpl implements Frequency{
	private static MondayToSundayFrequencyFormatter formatter = new MondayToSundayFrequencyFormatter('.', new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
	private byte value;
	
	protected FrequencyImpl(byte value) {
		super();
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	@Override
	public String toString() {
		return formatter.format(this);
	}

	public int compareTo(Frequency frequency) {
		return (int)(this.getValue() - frequency.getValue());
	}

}
