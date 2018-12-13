package examples.airport.column.config;

import yogi.base.evaluator.Evaluator;
import yogi.paging.column.TableColumnConfig;
import yogi.report.server.config.ColumnConfig;

import examples.airport.Airport;
import examples.airport.column.condition.config.AirportInConditionConfig;
import examples.airport.column.condition.config.AirportNotInConditionConfig;
import examples.airport.column.formatter.AirportFormatter;

public class AirportColumnConfig<R> extends ColumnConfig<R, Airport> {

	private static final long serialVersionUID = -4413563134059440967L;
	
	
	public AirportColumnConfig(String name, Evaluator<R, Airport> evaluator, TableColumnConfig<?> tableColumnConfig) {
		super(name, evaluator, new AirportFormatter(), tableColumnConfig, true);
		this.setType(Airport.class);
		this.addCondition(new AirportInConditionConfig());
		this.addCondition(new AirportNotInConditionConfig());
	}
	
	public AirportColumnConfig(String name, Evaluator<R, Airport> evaluator) {
		this(name, evaluator, new AirportTableColumnConfig(name, 60));
	}

}
