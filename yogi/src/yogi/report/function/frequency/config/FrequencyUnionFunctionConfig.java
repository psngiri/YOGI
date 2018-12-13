package yogi.report.function.frequency.config;


import yogi.period.frequency.Frequency;
import yogi.report.function.Function;
import yogi.report.function.frequency.FrequencyUnionFunction;
import yogi.report.server.config.FunctionConfig;

public class FrequencyUnionFunctionConfig extends FunctionConfig<Frequency>{
	private static final long serialVersionUID = 1L;
	public FrequencyUnionFunctionConfig() {
		super("Union");
	}
	@Override
	public Function<Frequency> getFunction() {
		return new FrequencyUnionFunction();
	}

}
