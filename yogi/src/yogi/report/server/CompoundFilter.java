package yogi.report.server;

import java.io.Serializable;
import java.util.Map;

import yogi.base.Selector;
import yogi.base.selectors.BaseAndSelector;
import yogi.base.selectors.BaseOrSelector;
import yogi.report.column.ColumnDefinitionBaseImpl;
import yogi.report.column.ColumnSelector;
import yogi.report.condition.Condition;
import yogi.report.server.config.ReportDataConfigurationProvider;

public class CompoundFilter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3771841913058230207L;
	private Filter filter;
	private boolean and;
	private CompoundFilter[] compoundfilters;
	
	public CompoundFilter(Filter filter) {
		super();
		this.filter = filter;
	}

	public CompoundFilter(boolean and, CompoundFilter... compoundfilter) {
		super();
		this.compoundfilters = compoundfilter;
		this.and = and;
	}
	
	@SuppressWarnings("rawtypes")
	public <R> Selector<R> getSelector(ReportDataConfigurationProvider<R> configProvider,
			Map<String, ColumnDefinitionBaseImpl> columnsMap, String userId) {
		if(filter != null){
			return getSelector(configProvider, columnsMap, userId, filter);
		}
		if(and){
			BaseAndSelector<R, Selector<R>> columnAndSelector = new BaseAndSelector<R, Selector<R>>();
			for(CompoundFilter compoundfilter: compoundfilters){
				columnAndSelector.addSelector(compoundfilter.getSelector(configProvider, columnsMap, userId));
			}
			return columnAndSelector;
		}else{
			BaseOrSelector<R, Selector<R>> columnOrSelector = new BaseOrSelector<R, Selector<R>>();
			for(CompoundFilter compoundfilter: compoundfilters){
				columnOrSelector.addSelector(compoundfilter.getSelector(configProvider, columnsMap, userId));
			}
			return columnOrSelector;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <R> Selector<R> getSelector(ReportDataConfigurationProvider<R> configProvider,
			Map<String, ColumnDefinitionBaseImpl> columnsMap, String userId, Filter filter) {
		ColumnDefinitionBaseImpl column = columnsMap.get(filter.getColumnName());
		ColumnSelector<R, Object> selector = new ColumnSelector<R, Object>(column);
			Condition<Object> newInstance = configProvider.getCondition(filter);
				if(newInstance != null) {
					newInstance.setUserId(userId);
					selector.addCondition(newInstance);     					
				}
		return selector;
	}

}
