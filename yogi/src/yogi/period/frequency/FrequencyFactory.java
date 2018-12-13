package yogi.period.frequency;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Manager;
import yogi.base.validation.ObjectValidator;

class FrequencyFactory extends Factory<Frequency> {

	public FrequencyFactory(Manager<Frequency> manager) {
		super(manager);
		load();
	}

	private void load() {
		for(int i = FrequencyManager.MinFrequency; i <= FrequencyManager.MaxFrequency; i ++)
		{
			this.add(new FrequencyImpl((byte) i));
		}
	}
	
	Frequency getFrequency(byte frequency)
	{
		if (frequency < FrequencyManager.MinFrequency || frequency > FrequencyManager.MaxFrequency) throw new RuntimeException("Valid frequency is between 0 and 127 both including, requested frequency: "+ frequency + "is not a valid frequecy");
		return getObjects().get(frequency);
	}

	@Override
	public Frequency create(Creator<Frequency> creator, ObjectValidator<? super Frequency> validator) {
		return creator.create();
	}

	@Override
	public void clear() {
	}

	@Override
	protected void clearAll() {
	}

	@Override
	public void delete(Frequency object) {
	}

}
