package yogi.base.stats.calculator;

import java.util.ArrayList;
import java.util.List;

public class MultiCalculator implements Calculator {
	private List<Calculator> calculators = new ArrayList<Calculator>();
	public MultiCalculator(Calculator... calculators) {
		super();
		for(Calculator calculator: calculators)
		{
			addCalculator(calculator);
		}
	}
	
	public void addCalculator(Calculator calculator)
	{
		calculators.add(calculator);
	}

	public String compute() {
		StringBuilder sb = new StringBuilder();
		for(Calculator calculator: calculators)
		{
			String value = calculator.compute();
			if(value != null) sb.append(value);
		}
		return sb.toString();
	}

	public void collect() {
		for(Calculator calculator: calculators)
		{
			calculator.collect();
		}
	}

}
