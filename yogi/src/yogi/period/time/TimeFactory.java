package yogi.period.time;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Manager;
import yogi.base.validation.ObjectValidator;

class TimeFactory extends Factory<Time> {

	public TimeFactory(Manager<Time> manager) {
		super(manager);
		load();
	}

	private void load() {
		for(short i = TimeManager.MinTimeInMinutes; i <= TimeManager.MaxTimeInMinutes; i ++)
		{
			this.add(new TimeImpl(i));
		}
	}
	
	Time getTime(int minutes)
	{
		if (minutes < TimeManager.MinTimeInMinutes || minutes > TimeManager.MaxTimeInMinutes) throw new RuntimeException("Valid time is between 0 and 1440 both including, requested time: "+ minutes + "is not a valid time");
		return getObjects().get(minutes);
	}

	@Override
	public Time create(Creator<Time> creator, ObjectValidator<? super Time> validator) {
		return creator.create();
	}

	@Override
	public void clear() {
	}

	@Override
	protected void clearAll() {
	}

	@Override
	public void delete(Time object) {
	}

}
