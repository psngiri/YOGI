package yogi.period.frequency.data;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public class FrequencyData<T> {
	private T data;
	private Frequency frequency;
	public FrequencyData(T data) {
		this(data, FrequencyManager.NoDayFrequency);
	}
	public FrequencyData(T data, Frequency frequency) {
		this.frequency = frequency;
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public Frequency getFrequency()
	{
		return frequency;
	}
	
	public void addFrequency(Frequency frequency)
	{
		this.frequency = Frequencies.add(this.frequency, frequency);
	}
}
