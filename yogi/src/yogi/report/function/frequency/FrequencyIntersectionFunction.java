package yogi.report.function.frequency;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.report.function.Function;

public class FrequencyIntersectionFunction implements Function<Frequency>{
	Frequency frequency  = FrequencyManager.NoDayFrequency;
	boolean isNew = true;
	
	public void reset() {
		frequency = FrequencyManager.NoDayFrequency;
		isNew = true;
	}

	public void process(Frequency object, int multiplier) {
		if(object == null) return;
		if(isNew && frequency == FrequencyManager.NoDayFrequency){
			frequency = object;
			isNew=false;
		}
		else frequency = Frequencies.intersection(frequency, object);
	}

	public Frequency getValue() {
		return frequency;
	}

}
