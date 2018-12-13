package yogi.report.function.frequency;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.report.function.Function;

public class FrequencyUnionFunction implements Function<Frequency> {
	private Frequency frequency = FrequencyManager.NoDayFrequency;
	
	public Frequency getValue() {
		return frequency;
	}

	public void process(Frequency object, int multiplier) {
		frequency = Frequencies.add(frequency, object);
	}

	public void reset() {
		frequency = FrequencyManager.NoDayFrequency;
	}
}
