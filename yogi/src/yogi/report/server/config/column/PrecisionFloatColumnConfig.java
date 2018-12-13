package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.base.util.PrecisionFloat;
import yogi.paging.column.TableColumnConfig;
import yogi.report.compare.function.average.config.PrecisionFloatAverageCompareFunctionConfig;
import yogi.report.compare.function.diff.config.PrecisionFloatDiffCompareFunctionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.PrecisionFloatGreaterThanConditionConfig;
import yogi.report.condition.config.PrecisionFloatGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.PrecisionFloatInConditionConfig;
import yogi.report.condition.config.PrecisionFloatLessThanConditionConfig;
import yogi.report.condition.config.PrecisionFloatLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.PrecisionFloatNotInConditionConfig;
import yogi.report.function.absolute.config.PrecisionFloatAbsoluteFunctionConfig;
import yogi.report.function.average.config.PrecisionFloatAverageFunctionConfig;
import yogi.report.function.max.config.PrecisionFloatMaxFunctionConfig;
import yogi.report.function.min.config.PrecisionFloatMinFunctionConfig;
import yogi.report.function.sum.config.PrecisionFloatSumFunctionConfig;
import yogi.report.function.sumhalf.config.PrecisionFloatSumHalfFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public abstract class PrecisionFloatColumnConfig<R> extends ColumnConfig<R, PrecisionFloat> {

	private static final long serialVersionUID = 3468019700919515136L;

	public PrecisionFloatColumnConfig(String columnName, Evaluator<R, PrecisionFloat> evaluator, Formatter<PrecisionFloat> formatter, Comparator<PrecisionFloat> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new PrecisionFloatInConditionConfig());
		this.addCondition(new PrecisionFloatNotInConditionConfig());
		this.addCondition(new PrecisionFloatGreaterThanConditionConfig());
		this.addCondition(new PrecisionFloatGreaterThanOrEqualsConditionConfig());
		this.addCondition(new PrecisionFloatLessThanConditionConfig());
		this.addCondition(new PrecisionFloatLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new PrecisionFloatAbsoluteFunctionConfig());
		this.addFunction(new PrecisionFloatAverageFunctionConfig());
		this.addFunction(new PrecisionFloatMaxFunctionConfig());
		this.addFunction(new PrecisionFloatMinFunctionConfig());
		this.addFunction(new PrecisionFloatSumFunctionConfig());
		this.addFunction(new PrecisionFloatSumHalfFunctionConfig());
		this.addCompareFunction(new PrecisionFloatDiffCompareFunctionConfig());
		this.addCompareFunction(new PrecisionFloatAverageCompareFunctionConfig());
	}

	public PrecisionFloatColumnConfig(String columnName, Evaluator<R, PrecisionFloat> evaluator, Formatter<PrecisionFloat> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
}