package yogi.report.server.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.function.config.DummyFunctionConfig;
import yogi.report.split.Splitter;

public class ColumnConfig<R, C> implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private transient Evaluator<R, C> evaluator;
	private transient Formatter<C> formatter;
	private transient Comparator<C> comparator;
	private transient List<ConditionConfig<? super C>> conditions;
	private transient List<FunctionConfig<C>> functions;
	transient Class<? extends C> type;
	
	private Map<String, ConditionConfig<? super C>> conditionsMap;
	private Map<String, FunctionConfig<C>> functionsMap ;
	private Map<String, CompareFunctionConfig<C>> compareFunctionsMap ;
	private TableColumnConfig<?> tableColumnConfig;
	private boolean key;
	private Splitter splitter;
	private String defaultFunction;

	public ColumnConfig(String columnName,Evaluator<R,C> evaluator, Formatter<C> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public ColumnConfig(String columnName,Evaluator<R,C> evaluator, Formatter<C> formatter, Comparator<C> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super();
		this.name = columnName;
		this.evaluator = evaluator;
		this.formatter = formatter;
		this.comparator = comparator;
		this.conditionsMap = new LinkedHashMap<String,ConditionConfig<? super C>>();
		this.functionsMap = new LinkedHashMap<String,FunctionConfig<C>>();
		this.compareFunctionsMap = new LinkedHashMap<String, CompareFunctionConfig<C>>();
		this.tableColumnConfig = tableColumnConfig;
		this.key = key;
		this.addFunction(new DummyFunctionConfig<C>());
	}
	
	public boolean isKey() {
		return key;
	}

	public TableColumnConfig<?> getTableColumnConfig() {
		return tableColumnConfig;
	}
	public void addCondition(ConditionConfig<? super C> condition)
	{
		conditionsMap.put(condition.getName(), condition);
	}
	public void addFunction(FunctionConfig<C> function)
	{
		functionsMap.put(function.getName(), function);
	}
	public void addCompareFunction( CompareFunctionConfig<C> compareFunction)
	{
		compareFunctionsMap.put(compareFunction.getName(), compareFunction);
	}
	public String getName() {
		return name;
	}

	public Evaluator<R, C> getEvaluator() {
		return evaluator;
	}

	public Formatter<C> getFormatter() {
		return formatter;
	}
	
	public ConditionConfig<? super C> getCondition(String conditionName)
	{
		conditionName = conditionName.trim();
		if(conditionName.trim().isEmpty()){
			return getConditionsMap().values().iterator().next();
		}
		return getConditionsMap().get(conditionName);
	}

	public List<ConditionConfig<? super C>> getConditions() {
		if(conditions == null)
		{
			conditions = new ArrayList<ConditionConfig<? super C>>();
			for(Map.Entry<String, ConditionConfig<? super C>> entry: conditionsMap.entrySet())
			{
				conditions.add(entry.getValue());
			}
		}
		return conditions;
	}
	
	private Map<String, ConditionConfig<? super C>> getConditionsMap() {
		return conditionsMap;
	}
	
	public FunctionConfig<C> getFunction(String functionName){
		return getFunctionsMap().get(functionName);
	}

	
	public List<FunctionConfig<C>> getFunctions() {
		if(functions == null)
		{
			functions = new ArrayList<FunctionConfig<C>>();
			for(Map.Entry<String, FunctionConfig<C>> entry: functionsMap.entrySet())
			{
				functions.add(entry.getValue());
			}
		}
		return functions;
	}
	
	private Map<String, FunctionConfig<C>> getFunctionsMap() {
		return functionsMap;
	}
	
	public CompareFunctionConfig<C> getCompareFunction(String functionName){
		return getCompareFunctionsMap().get(functionName);
	}

	private Map<String, CompareFunctionConfig<C>> getCompareFunctionsMap() {
		return compareFunctionsMap;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public Comparator<C> getComparator() {
		return comparator;
	}
	
	public Class<?> getType() {
		return type;
	}

	public ColumnConfig<R, C> setType(Class<? extends C> type) {
		this.type = type;
		return this;
	}

	public Splitter getSplitter() {
		return splitter;
	}

	public void setSplitter(Splitter splitter) {
		this.splitter = splitter;
	}

	public String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultFunction() {
		return defaultFunction;
	}

	public void setDefaultFunction(String defaultFunction) {
		this.defaultFunction = defaultFunction;
	}

}