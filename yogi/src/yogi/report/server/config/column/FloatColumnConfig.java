package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.condition.config.FloatGreaterThanConditionConfig;
import yogi.report.condition.config.FloatGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.FloatInConditionConfig;
import yogi.report.condition.config.FloatLessThanConditionConfig;
import yogi.report.condition.config.FloatLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.FloatNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.average.config.FloatAverageFunctionConfig;
import yogi.report.function.max.config.MaxFloatFunctionConfig;
import yogi.report.function.min.config.MinFloatFunctionConfig;
import yogi.report.function.sum.config.FloatSumFunctionConfig;
import yogi.report.function.sumhalf.config.FloatSumHalfFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public abstract class FloatColumnConfig<R> extends ColumnConfig<R, Float> {

	private static final long serialVersionUID = -9178897657992397711L;

	public FloatColumnConfig(String columnName, Evaluator<R, Float> evaluator, Formatter<Float> formatter, Comparator<Float> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new FloatInConditionConfig());
		this.addCondition(new FloatNotInConditionConfig());
		this.addCondition(new FloatGreaterThanConditionConfig());
		this.addCondition(new FloatGreaterThanOrEqualsConditionConfig());
		this.addCondition(new FloatLessThanConditionConfig());
		this.addCondition(new FloatLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new MinFloatFunctionConfig());
		this.addFunction(new MaxFloatFunctionConfig());
		this.addFunction(new FloatSumFunctionConfig());
		this.addFunction(new FloatSumHalfFunctionConfig());
		this.addFunction(new FloatAverageFunctionConfig());
	}

	public FloatColumnConfig(String columnName, Evaluator<R, Float> evaluator, Formatter<Float> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}

	public FloatColumnConfig(String columnName, Evaluator<R, Float> evaluator, TableColumnConfig<?> tableColumnConfig) {
		this(columnName, evaluator, null, tableColumnConfig, false);
	}
}
