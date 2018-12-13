package yogi.period.frequency;

import java.util.ArrayList;
import java.util.List;

import yogi.period.date.Date;
import yogi.period.date.range.DateRange;
import yogi.period.time.Time;
import yogi.period.time.range.TimeRange;


public class Frequencies{
	
	static int convertToByteValue(int dayOfWeek) {
		return (int)Math.pow(2, 7-dayOfWeek);
	}

	public static Frequency add(Frequency f1, Frequency f2)
	{
		return FrequencyManager.get().getFrequency(f1.getValue() | f2.getValue());
	}
	
	public static Frequency subtract(Frequency f1, Frequency f2)
	{
		return FrequencyManager.get().getFrequency(f1.getValue() &~f2.getValue());
	}
	
	public static Frequency intersection(Frequency f1, Frequency f2)
	{
		return FrequencyManager.get().getFrequency(f1.getValue() & f2.getValue());
	}
	
	public static boolean intersects(Frequency f1, Frequency f2)
	{
		return (f1.getValue() & f2.getValue()) != 0;
	}
	
	public static boolean isSubSet(Frequency superSet, Frequency subSet)
	{
		return(superSet.getValue() & subSet.getValue()) == subSet.getValue();
	}
	
	public static Frequency shiftForward(Frequency frequency, int numberOfDays)
	{
		return shift(frequency, numberOfDays);
	}
	
	public static Frequency shiftBackward(Frequency frequency, int numberOfDays)
	{
		return shift(frequency, -numberOfDays);
	}
	
	public static Frequency shift(Frequency frequency, int numberOfDays)
	{
		int shift = numberOfDays%7;
		if(shift == 0) return frequency;
		byte myFrequency = frequency.getValue();
		int mask = Frequency.SUNDAY;
		int returnValue = 0;
		for(int i = 0; i < 7; i ++)
		{
			int value = (i+7+shift)%7 + 1;
			if((myFrequency & mask) != 0 ) returnValue = returnValue | convertToByteValue(value);
			mask = mask >> 1;
		}
		return FrequencyManager.get().getFrequency(returnValue);
	}
	
	public static List<Frequency> shift(List<Frequency> frequencies, int numberOfDays)
	{
		List<Frequency> rtnValue = new ArrayList<Frequency>(frequencies.size());
		for(Frequency frequency: frequencies)
		{
			rtnValue.add(shift(frequency, numberOfDays));
		}
		return rtnValue;
	}
	
	public static Frequency getOperationalFrequency(DateRange dateRange, Frequency frequency)
	{
		Date start = dateRange.getStart();
		
		long frequencyCounter = dateRange.getNumberOfDays();
		if(frequencyCounter > 7)
			frequencyCounter = 7;
		Frequency myFrequency = FrequencyManager.get().getFrequency(start);
		Frequency returnValue = FrequencyManager.NoDayFrequency;
		
		for(long i = 0; i < frequencyCounter; i++)
		{
			if(Frequencies.intersects(frequency, myFrequency))
				returnValue = Frequencies.add(returnValue, myFrequency);
			myFrequency = Frequencies.shift(myFrequency, 1);
		}
		return returnValue;
	}
	
	public static int getNumberOfDays(Frequency frequency)
	{
		byte myFrequency = frequency.getValue();
		int mask = Frequency.SUNDAY;
		int returnValue = 0;
		for(int i = 0; i < 7; i ++)
		{
			if((myFrequency & mask) != 0 ) returnValue++; 
			mask = mask >> 1;
		}
		return returnValue;
	}
	
	public static List<Frequency> split(Frequency frequency)
	{
		byte myFrequency = frequency.getValue();
		int mask = Frequency.SUNDAY;
		List<Frequency> returnValue = new ArrayList<Frequency>();
		for(int i = 0; i < 7; i ++)
		{
			if((myFrequency & mask) != 0 ) returnValue.add(FrequencyManager.get().getFrequency(mask)); 
			mask = mask >> 1;
		}
		return returnValue;
	}
	
	public static List<Integer> getOperationalDays(Frequency frequency)
	{
		byte myFrequency = frequency.getValue();
		int mask = Frequency.SUNDAY;
		List<Integer> returnValue = new ArrayList<Integer>();
		for(int i = 0; i < 7; i ++)
		{
			if((myFrequency & mask) != 0 ) returnValue.add(i+1); 
			mask = mask >> 1;
		}
		return returnValue;
	}
	
	public static boolean contains(Frequency frequency, int dayOfWeek)
	{
		Frequency dayOfWeekFrequency = FrequencyManager.get().getFrequencyFromDayOfWeek(dayOfWeek);
		return intersects(frequency, dayOfWeekFrequency);
	}
	
	public static int getDifferenceOfDays(Frequency frequency, int dayOfWeek) {
		if(contains(frequency, dayOfWeek)) {
			return 0;
		}
		List<Integer> operationalDays = getOperationalDays(frequency);
		int lastOperationalDayOfWeek = operationalDays.get(operationalDays.size() - 1);
		int diff = dayOfWeek - lastOperationalDayOfWeek;
		if(diff < 0 ) {
			return 7 + diff;
		}
		return diff;
	}
	
	public static boolean contains(Frequency frequency, TimeRange timeRange, Frequency containsFrequency, Time containsTime, boolean inRange)
    {
		Frequency intersection = Frequencies.intersection(frequency, containsFrequency);
          if(intersection != containsFrequency) return false;
          if(inRange){
                Frequency shiftBackward = Frequencies.shiftBackward(containsFrequency, 1);
                intersection = Frequencies.intersection(frequency, shiftBackward);
                if(intersection != shiftBackward && containsTime.compareTo(timeRange.getStartTime()) < 0) return false;
                Frequency shiftForward = Frequencies.shiftForward(containsFrequency, 1);
                intersection = Frequencies.intersection(frequency, shiftForward);
                if(intersection != shiftForward  &&  containsTime.compareTo(timeRange.getEndTime()) > 0) return false;
           }else{
                return timeRange.contains(containsTime);
          }
          return true;
    }

  	
}
