package yogi.period.frequency;

import yogi.base.Manager;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.frequency.io.MondayToSundayFrequencyScanner;

public class FrequencyManager extends Manager<Frequency> {
	private static MondayToSundayFrequencyScanner scanner = new MondayToSundayFrequencyScanner();
	private static FrequencyManager frequencyManager = new FrequencyManager();
	public static final int MinFrequency = 0;
	public static final int MaxFrequency = 127;
	public static final Frequency NoDayFrequency = frequencyManager.getFrequency(MinFrequency);
	public static final Frequency AllDayFrequency = frequencyManager.getFrequency(MaxFrequency);
	
	FrequencyFactory frequencyFactory;
	
	
	public static FrequencyManager get() {
		return frequencyManager;
	}

	protected FrequencyManager() {
		super();
		frequencyFactory = new FrequencyFactory(this);
	}

	public Frequency getFrequency(String frequency) {
		return scanner.scan(frequency).create();
	}
	
	public Frequency getFrequency(int frequency)
	{
		return getFrequency((byte) frequency);
	}
	
	public Frequency getFrequency(byte frequency)
	{
		return frequencyFactory.getFrequency(frequency);
	}
	
	public Frequency getFrequency(Date date)
	{
		int dayOfWeek = DateAssistant.get().getDayOfWeek(date);
		return getFrequencyFromDayOfWeek(dayOfWeek);
	}

	public Frequency getFrequencyFromDayOfWeek(int dayOfWeek) {
		if(dayOfWeek < 1 || dayOfWeek > 7) throw new RuntimeException("DayOfWeek must be between 1 and 7 :" + dayOfWeek);
		return getFrequency(Frequencies.convertToByteValue(dayOfWeek));
	}

}
