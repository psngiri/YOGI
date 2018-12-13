package yogi.report.server.config;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.FileToStringListReader;
import yogi.base.io.Formatter;
import yogi.base.io.ObjectFormatter;
import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;
import yogi.paging.TableActionConfig;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.StringTableColumnConfig;
import yogi.report.compare.function.CompareFunction;
import yogi.report.condition.Condition;
import yogi.report.function.DummyFunction;
import yogi.report.function.Function;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.ReportDataItemsProvider;

public class ReportConfigProvider<R> implements ReportDataConfigurationProvider<R>
{
	private ReportConfig<R> reportConfig;
	public static String ReportDirectory = "*/config/ReportConfig.properties";
	private static Map<String, ReportConfig<?>> reportConfigs;
	private static Logger logger = Logging.getLogger(ReportConfigProvider.class);
	private ReportDataItemsProvider<R> reportDataItemsProvider = null;
	@SuppressWarnings("unchecked")
	public ReportConfigProvider(String reportName)
	{
		super();
		reportConfig = (ReportConfig<R>) getReportConfig(reportName);
	}

	private static Map<String, ReportConfig<?>> getReportConfigs()
	{
		try
		{
			if ((reportConfigs == null) || reportConfigs.isEmpty())
			{
				reportConfigs = new LinkedHashMap<String, ReportConfig<?>>();
				SystemResource resource = ResourceAssistant.getResource(ReportDirectory);
				FileToStringListReader reader = new FileToStringListReader(resource,0);
				List<String> values = reader.read();
				for(String x: values){
					ReportConfig<?> reportconfig = null;					
					if(!x.trim().isEmpty() && !x.startsWith("#")){
						String[] split = x.split(",");
						try {
						Class<?> klass = Class.forName(split[0].trim());
							if(split.length==1)	{
								reportconfig = (ReportConfig<?>) klass.newInstance();							
							}else{
								Class<?>[] parameterTypes = new Class<?>[split.length-1];
								Object[] initargs = new Object[parameterTypes.length];
								for(int i = 0; i < parameterTypes.length; i++)
								{
									parameterTypes[i] = String.class;
									initargs[i] = split[i+1].trim();
								}
								Constructor<?> declaredConstructor = klass.getDeclaredConstructor(parameterTypes);
								reportconfig = (ReportConfig<?>) declaredConstructor.newInstance(initargs);
							}
							reportconfig.loadDescriptions();
							reportConfigs.put(reportconfig.getName(), reportconfig);
						} catch (Exception e) {
							logger.info("Error creating the Report Config for :" + x);
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Could not find ReportConfig.properties at location:" + ReportDirectory, e);
		}
		return reportConfigs;
	}

	public List<TableActionConfig> getTableActionConfigs()
	{
		return reportConfig.getTableActionConfigs();
	}
	
	public String getDefaultFindColumnName()
	{
		return reportConfig.getDefaultFindColumnName();
	}
	
	public boolean isDefaultEditSelectAllValue()
	{
		return reportConfig.isDefaultEditSelectAllValue();
	}
	
	public static List<String> getReports()
	{
		List<String> list = new ArrayList<String>(getReportConfigs().keySet());
		return list;
	}

	public static ReportConfig<?> getReportConfig(String reportName)
	{
		return getReportConfigs().get(reportName);
	}

	public TableColumnConfig<?> getTableColumnConfig(String columnName){
        ColumnConfig<? super R, ?> columnConfig = reportConfig.getColumn(columnName);
        if(columnConfig == null) return new StringTableColumnConfig(columnName, 50);
        return (TableColumnConfig<?>) columnConfig.getTableColumnConfig();
    }
	
	@SuppressWarnings("unchecked")
	public Function<Object> getFunction(Column column)
	{
		try
		{
            ColumnConfig<? super R, ?> columnConfig = getColumnConfig(column);
            if (!column.getFunction().trim().equals(""))
            {
                  FunctionConfig<?> functionConfig = columnConfig.getFunction(column.getFunction());
                  return (Function<Object>) functionConfig.getFunction();
            }else{
                  if(!columnConfig.isKey()) {
                	  String defaultFunction = columnConfig.getDefaultFunction();
                	  if(defaultFunction!=null)
                	  {
                		  FunctionConfig<?> functionConfig = columnConfig.getFunction(defaultFunction);
						return (Function<Object>) functionConfig.getFunction();
                	  }
                	  return new DummyFunction<Object>();
                  }
            }

		} catch (Exception e)
		{
			throw new RuntimeException("Could not get function for Column:" + column.getName(), e);
		}
		return null;
	}

	public ColumnConfig<? super R, ?> getColumnConfig(Column column) {
		String columnName = column.getName();
		return getColumnConfig(columnName);
	}

	public ColumnConfig<? super R, ?> getColumnConfig(String columnName) {
		ColumnConfig<? super R, ?> columnConfig = reportConfig.getColumn(columnName);
		return columnConfig;
	}

	@SuppressWarnings("unchecked")
	public List<CompareFunction<?>> getCompareFunctions(Column column, int numberOfDataSets)
	{
		List<CompareFunction<?>> rtnValue = new ArrayList<CompareFunction<?>>();
		try
		{
			String[] compareFunctionNames = column.getCompareFunctionNames();
			if(compareFunctionNames == null || compareFunctionNames.length == 0) return rtnValue;
	        ColumnConfig<? super R, ?> columnConfig = getColumnConfig(column);
			for(String compareFunctionName: compareFunctionNames){
				if (!compareFunctionName.trim().equals("")){
					CompareFunctionConfig<?> compareFunctionConfig = columnConfig.getCompareFunction(compareFunctionName);
					for(int i = 1; i < numberOfDataSets; i ++)
					{
						rtnValue.add((CompareFunction<Object>) compareFunctionConfig.getCompareFunction(0, i));
					}
				}
			}
		} catch (Exception e)
		{
			throw new RuntimeException("Could not get compare function for Column:" + column.getName(), e);
		}
		return rtnValue;
	}

	@SuppressWarnings("unchecked")
	public Formatter<Object> getFormatter(Column column)
	{
		Formatter<Object> formater = new ObjectFormatter();
		try
		{
			ColumnConfig<? super R, ?> columnConfig = getColumnConfig(column);
			return (Formatter<Object>) columnConfig.getFormatter();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return formater;
	}

	public Evaluator<R, Object> getEvaluator(Column column)
	{
		return getEvaluator(column.getName());
	}

	@SuppressWarnings("unchecked")
	public Evaluator<R, Object> getEvaluator(String columnName) {
		try
		{
			ColumnConfig<? super R, ?> columnConfig = getColumnConfig(columnName);
			return (Evaluator<R, Object>) columnConfig.getEvaluator();
		} catch (Exception e)
		{
			throw new RuntimeException("Could not get evaluator for Column:" + columnName, e);
		}
	}

	
	public Condition<Object> getCondition(Filter aliasFilter)
	{
		ColumnConfig<? super R, ?> columnConfig = reportConfig.getColumn(aliasFilter.getColumnName());
		return getCondition(aliasFilter, columnConfig);
	}

	@SuppressWarnings("unchecked")
	public static Condition<Object> getCondition(Filter aliasFilter, ColumnConfig<?, ?> columnConfig) {
		try {
			if (!aliasFilter.getColumnName().trim().isEmpty())
			{
				ConditionConfig<?> conditionConfig = columnConfig.getCondition(aliasFilter.getCondition());
				Validator validator = conditionConfig.getValidator();
				if (validator.validate(aliasFilter.getValue()))
				{
					return (Condition<Object>) conditionConfig.getCondition(aliasFilter.getValue());
				} else
				{
					throw new RuntimeException(aliasFilter.getColumnName() + "" + aliasFilter.getCondition() + " condition " + aliasFilter.getValue() + validator.getMessage());
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Not a valid condition for filter: "+aliasFilter.getColumnName(), e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReportDataItemsProvider<R> getReportDataItemsProvider()
	{
		if(reportDataItemsProvider == null){
			ReportDataItemsProvider<R> reportDataItems = reportConfig.getReportDataItems();
			try {//this is being done to make sure that every query gets its one ReportDataItems Provider only for
				// providers having no args constructor;
				reportDataItemsProvider = reportDataItems.getClass().newInstance();
			} catch (Throwable e) {
				logger.fine(e.getMessage());
//				e.printStackTrace();
				reportDataItemsProvider = reportDataItems;
			}
		}
		return reportDataItemsProvider;
	}

	@Override
	public boolean validate(String[] values)
	{
		for (String value : values)
		{
			Validator validator = reportConfig.getValidator();
			if (!validator.validate(value)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Comparator<Object> getComparator(Column column) {
		try {
			ColumnConfig<? super R, ?> columnConfig = getColumnConfig(column);
			return (Comparator<Object>) columnConfig.getComparator();
		} catch (Exception e) {
			throw new RuntimeException("Could not get evaluator for Column:" + column.getName(), e);
		}
	}
	
	static class OrderedProperties extends Properties
	{
		private static final long serialVersionUID = -8050470708126366819L;

		private Vector<Object> propertyNames;

		public OrderedProperties()
		{
			super();
			propertyNames = new Vector<Object>();
		}

		public Enumeration<?> propertyNames()
		{
			return propertyNames.elements();
		}

		public Object put(Object key, Object value)
		{
			if (propertyNames.contains(key))
			{
				propertyNames.remove(key);
			}
			propertyNames.add(key);
			return super.put(key, value);
		}

		public Object remove(Object key)
		{
			propertyNames.remove(key);
			return super.remove(key);
		}
	}

}