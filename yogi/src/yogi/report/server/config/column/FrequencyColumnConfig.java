package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.base.io.Scanner;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.FrequencyTableColumnConfig;
import yogi.period.frequency.Frequency;
import yogi.report.condition.config.FrequencyContainsConditionConfig;
import yogi.report.condition.config.FrequencyEqualsConditionConfig;
import yogi.report.condition.config.FrequencyNotContainsConditionConfig;
import yogi.report.condition.config.FrequencyNotEqualsConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.frequency.FrequencyFormatter;
import yogi.report.condition.frequency.FrequencyScanner;
import yogi.report.function.frequency.config.FrequencyDiffCompareFunctionConfig;
import yogi.report.function.frequency.config.FrequencyIntersectionCompareFunctionConfig;
import yogi.report.function.frequency.config.FrequencyIntersectionFunctionConfig;
import yogi.report.function.frequency.config.FrequencyReverseDiffCompareFunctionConfig;
import yogi.report.function.frequency.config.FrequencyUnionFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public class FrequencyColumnConfig <R> extends ColumnConfig<R, Frequency> {

	private static final long serialVersionUID = 6481656663321078872L;

	public FrequencyColumnConfig(String columnName, Evaluator<R, Frequency> evaluator, Formatter<Frequency> formatter, Comparator<Frequency> comparator, TableColumnConfig<?> tableColumnConfig, boolean key, Scanner<Frequency, String> scanner) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new FrequencyContainsConditionConfig(scanner));
		this.addCondition(new FrequencyEqualsConditionConfig(scanner));
		this.addCondition(new FrequencyNotContainsConditionConfig(scanner));
		this.addCondition(new FrequencyNotEqualsConditionConfig(scanner));
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new FrequencyUnionFunctionConfig());
		this.addFunction(new FrequencyIntersectionFunctionConfig());
		this.addCompareFunction(new FrequencyDiffCompareFunctionConfig());
		this.addCompareFunction(new FrequencyReverseDiffCompareFunctionConfig());
		this.addCompareFunction(new FrequencyIntersectionCompareFunctionConfig());
	}

	public FrequencyColumnConfig(String columnName, Evaluator<R, Frequency> evaluator, Formatter<Frequency> formatter, TableColumnConfig<?> tableColumnConfig, boolean key, Scanner<Frequency, String> scanner) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key, scanner);
	}
	
	public FrequencyColumnConfig(String columnName, Evaluator<R, Frequency> evaluator, Formatter<Frequency> formatter, Scanner<Frequency, String> scanner,int width) {
		this(columnName, evaluator, formatter, new FrequencyTableColumnConfig(columnName,width,formatter, scanner), false, scanner);
	}
	
	public FrequencyColumnConfig(String columnName, Evaluator<R, Frequency> evaluator, Formatter<Frequency> formatter, Scanner<Frequency, String> scanner) {
		this(columnName, evaluator, formatter, scanner, 120);
	}
	
	public FrequencyColumnConfig(String columnName, Evaluator<R, Frequency> evaluator, Formatter<Frequency> formatter) {
		this(columnName, evaluator, formatter, new FrequencyScanner());
	}
	
	public FrequencyColumnConfig(String columnName, Evaluator<R, Frequency> evaluator) {
		this(columnName, evaluator, new FrequencyFormatter());
	}
}