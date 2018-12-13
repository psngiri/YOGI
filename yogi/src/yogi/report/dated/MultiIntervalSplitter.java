package yogi.report.dated;

import java.util.ArrayList;
import java.util.List;

import yogi.period.interval.Interval;

public class MultiIntervalSplitter implements IntervalSplitter {
	List<IntervalSplitter> splitters = new ArrayList<IntervalSplitter>();
	
	public void addSplitter(IntervalSplitter splitter)
	{
		splitters.add(splitter);
	}
	
	public List<Interval> split(Interval interval) {
		List<Interval> rtnValue = splitters.get(0).split(interval);
		
		for(int i = 1; i < splitters.size(); i ++){
			List<Interval> inputIntervals = rtnValue;
			rtnValue = new ArrayList<Interval>();
			for(Interval myInterval: inputIntervals)
			{
				rtnValue.addAll(splitters.get(i).split(myInterval));
			}
		}
		return rtnValue;
	}

}
