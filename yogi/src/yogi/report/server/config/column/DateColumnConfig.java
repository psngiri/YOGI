package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.base.io.Scanner;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.DateTableColumnConfig;
import yogi.period.date.Date;
import yogi.report.condition.config.DateGreaterThanConditionConfig;
import yogi.report.condition.config.DateGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.DateInConditionConfig;
import yogi.report.condition.config.DateLessThanConditionConfig;
import yogi.report.condition.config.DateLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.DateNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.server.config.ColumnConfig;


public class DateColumnConfig<R> extends ColumnConfig<R, Date> {
	
	private static final long serialVersionUID = 6054707400303559604L;
	
	public DateColumnConfig(String columnName, Evaluator<R, Date> evaluator, Formatter<Date> formatter, Comparator<Date> comparator,TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new DateInConditionConfig());
		this.addCondition(new DateNotInConditionConfig());
		this.addCondition(new DateGreaterThanConditionConfig());
		this.addCondition(new DateGreaterThanOrEqualsConditionConfig());
		this.addCondition(new DateLessThanConditionConfig());
		this.addCondition(new DateLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
	}
	
	public DateColumnConfig(String columnName, Evaluator<R, Date> evaluator, Formatter<Date> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public DateColumnConfig(String columnName, Evaluator<R, Date> evaluator, Formatter<Date> formatter, Scanner<Date, String> scanner, int width) {
		this(columnName,evaluator,formatter, new DateTableColumnConfig(columnName, width, false, formatter, scanner), false);			
	}
		
	public DateColumnConfig(String columnName, Evaluator<R, Date> evaluator, Formatter<Date> formatter, Scanner<Date, String> scanner) {
		this(columnName,evaluator,formatter, scanner, 120);		
	}
	
	public DateColumnConfig(String columnName, Evaluator<R,Date> evaluator) {
		this(columnName, evaluator, null, new DateTableColumnConfig(columnName,120), false);
	}
}