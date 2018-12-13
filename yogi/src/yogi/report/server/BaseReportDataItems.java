package yogi.report.server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import yogi.base.Selector;
import yogi.base.evaluator.Evaluator;
import yogi.base.indexing.ManyIndexer;
import yogi.base.io.Formatter;
import yogi.base.io.ObjectFormatter;
import yogi.base.selectors.AndSelector;
import yogi.base.util.immutable.ImmutableList;
import yogi.paging.column.TableColumnConfig;
import yogi.report.Header;
import yogi.report.LineWriter;
import yogi.report.ReportGenerator;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionBaseImpl;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.column.ColumnGroupBy;
import yogi.report.column.ColumnSelector;
import yogi.report.column.ColumnWorker;
import yogi.report.column.GroupColumnComparator;
import yogi.report.column.GroupColumnDefinitionImpl;
import yogi.report.column.GroupColumnSelector;
import yogi.report.column.alias.AliasColumnEvaluator;
import yogi.report.column.alias.condition.AliasCondition;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.CompareGroupGenerator;
import yogi.report.compare.CompareItem;
import yogi.report.compare.CompareItemGenerator;
import yogi.report.compare.CompareReportGenerator;
import yogi.report.compare.KeyColumnComparator;
import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.compare.template.CompareGroupValuesGenerator;
import yogi.report.compare.template.CompareHeaderValuesGenerator;
import yogi.report.compare.template.CompareItemValuesGenerator;
import yogi.report.compare.template.SimpleCompareReportTemplate;
import yogi.report.compare.template.TemplateCompareGroupFormatter;
import yogi.report.condition.Condition;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.Function;
import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;
import yogi.report.group.GroupGenerator;
import yogi.report.group.GroupImpl;
import yogi.report.server.config.ColumnConfig;
import yogi.report.server.config.ReportDataConfigurationProvider;
import yogi.report.template.TemplateGroupFormatter;


public class BaseReportDataItems<R> implements ReportDataItems<R> {
	public int maxNumberOfRows=1000;
	private int queryId;
	private List<ImmutableList<R>> filteredAndSortedItems;
	private GroupGenerator<R> groupGenerator;
	private CompareGroupGenerator<R> compareGroupGenerator;
	private BaseCompareReportTemplate<R> reportTemplate;
	private ReportDataConfigurationProvider<R> reportDataConfigurationProvider;
	private CompareGroupValuesGenerator<R> compareGroupValuesGenerator;
	private CompareItemValuesGenerator<R> compareItemValuesGenerator;
	private String userId;
	
	public BaseReportDataItems(int queryId, String userId, ReportDataConfigurationProvider<R> reportDataConfigurationProvider) {
		super();
		this.queryId = queryId;
		this.userId = userId;
		this.reportDataConfigurationProvider = reportDataConfigurationProvider;
	}
	
	public BaseReportDataItems(int queryId, String userId, ReportDataConfigurationProvider<R> reportDataConfigurationProvider, int maxNumberOfRows) {
		this(queryId, userId, reportDataConfigurationProvider);
		this.maxNumberOfRows = maxNumberOfRows;
	}
	
	public int getQueryId() {
		return queryId;
	}
	
	public String getUserId() {
		return userId;
	}

	public LineWriter generateReport(Query query, BaseCompareReportTemplate<R> reportTemplate, TemplateGroupFormatter<R> templateGroupFormatter, TemplateCompareGroupFormatter<R> templateCompareGroupFormatter, LineWriter lineWriter){
		this.reportTemplate = reportTemplate;
		populateReportTemplate(query, reportTemplate);
		groupGenerator = new GroupGenerator<R>();
		reportTemplate.apply(groupGenerator);
		try {
				if(reportDataConfigurationProvider.validate(query.getObject()))
				{
					List<List<R>> itemsList = reportDataConfigurationProvider.getReportDataItemsProvider().getItems(query, getUserId(), Integer.MAX_VALUE);//Don't apply Limits for Writing to Files
					boolean isCompareReport = !reportTemplate.getCompareFunctions().isEmpty();
					adjustItemsList(itemsList, isCompareReport);
					
					filteredAndSortedItems = new ArrayList<ImmutableList<R>>(itemsList.size());
					for(List<R> items: itemsList){
						filteredAndSortedItems.add(new ImmutableList<R>(groupGenerator.getFilteredAndSortedItems(items)));
					}
					if(filteredAndSortedItems.isEmpty()) return lineWriter;
					
					if(filteredAndSortedItems.size() == 1){
						ReportGenerator<R> reportGenerator = new ReportGenerator<R>(lineWriter,groupGenerator, filteredAndSortedItems.get(0));
						reportGenerator.setIgnoreConsecutiveSameLines(false);
						reportGenerator.getReportBuilder().setGroupFormatter(templateGroupFormatter);
						reportGenerator.setReportHeader(reportTemplate.getReportHeader());
						reportGenerator.setReportFooter(reportTemplate.getReportFooter());
						reportGenerator.generateReport();
					}else{
						reportTemplate.setNumberOfDataSets(query.getObject().length);
						@SuppressWarnings("unchecked")
						CompareReportGenerator<R> reportGenerator = new CompareReportGenerator<R>(lineWriter,groupGenerator, filteredAndSortedItems.toArray(new ImmutableList[0]));
						reportGenerator.setIgnoreConsecutiveSameLines(false);
						reportGenerator.getReportBuilder().setGroupFormatter(templateCompareGroupFormatter);
						reportGenerator.setReportHeader(getReportHeader());
						reportGenerator.generateReport();
					}
					return lineWriter;
				}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		throw new RuntimeException("Could not generate Report: check Query");
	}

	private Header getReportHeader() {
		return new Header(){

			@Override
			public List<String> getHeader() {
				List<String> columnNames = getColumnNames();
				StringBuilder sb = new StringBuilder();
				for(String columnName: columnNames){
					sb.append("\"").append(columnName).append("\"");
					sb.append(",");
				}
				List<String> rtnalue = new ArrayList<String>(1);
				rtnalue.add(sb.substring(0, sb.length()-1));
				return rtnalue;
			}
			
		};
	}

	private void adjustItemsList(List<List<R>> itemsList,
			boolean isCompareReport) {
		if(!isCompareReport && itemsList.size() > 1)
		{
			List<R> items = new ArrayList<R>();
			for(List<R> list: itemsList){
				items.addAll(list);
			}
			itemsList.clear();
			itemsList.add(items);
		}
	}
	
	public ReportTableData generateReport(Query query)
	{
		reportTemplate = new SimpleCompareReportTemplate<R>();
		
		populateReportTemplate(query, reportTemplate);
		groupGenerator = new GroupGenerator<R>();
		compareGroupGenerator = new CompareGroupGenerator<R>(groupGenerator);
		reportTemplate.apply(groupGenerator);
		try {
				if(reportDataConfigurationProvider.validate(query.getObject()))
				{
					int maxOutput = groupGenerator.getGroupBys().isEmpty() ? maxNumberOfRows : Integer.MAX_VALUE;
					List<List<R>> itemsList = reportDataConfigurationProvider.getReportDataItemsProvider().getItems(query, getUserId(), maxOutput);
					boolean isCompareReport = !reportTemplate.getCompareFunctions().isEmpty();
					adjustItemsList(itemsList, isCompareReport);
					filteredAndSortedItems = new ArrayList<ImmutableList<R>>(itemsList.size());
					for(List<R> items: itemsList){
						filteredAndSortedItems.add(new ImmutableList<R>(groupGenerator.getFilteredAndSortedItems(items)));
					}
					if(filteredAndSortedItems.size()>1){
						reportTemplate.setNumberOfDataSets(query.getObject().length);
					}
					List<ReportDataIndex> indexes = new ArrayList<ReportDataIndex>(itemsList.size());
					for(ImmutableList<R> items: filteredAndSortedItems){
						indexes.add(new ReportDataIndex(0, items.size()-1));
					}
					List<ReportData> reportData = expandGroup(0, indexes).getRows();
					List<String> columnNames = getColumnNames();
					if(reportData.size()>maxNumberOfRows){
						throw new RuntimeException("Report Output Greater Than The Limit"+maxNumberOfRows+",Please add filter to reduce the output size");
					}
					if(reportData.isEmpty()) throw new RuntimeException("Query returned empty rows, please check your query");
					List<TableColumnConfig<?>> tableColumnConfigs = getTableColumnConfigs();
					return new ReportTableData(queryId,columnNames, reportData, tableColumnConfigs, reportDataConfigurationProvider.getTableActionConfigs(), reportDataConfigurationProvider.getDefaultFindColumnName(), reportDataConfigurationProvider.isDefaultEditSelectAllValue());
				}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		throw new RuntimeException("Could not generate Report check Query");
	}
	
	private List<TableColumnConfig<?>> getTableColumnConfigs() {
		List<ColumnDefinition<R>> columns = reportTemplate.getColumns();
		List<TableColumnConfig<?>> tableColumnConfigs = new ArrayList<TableColumnConfig<?>>();
		if(filteredAndSortedItems.size() ==1){
			for(ColumnDefinition<R> column: columns)
			{
				tableColumnConfigs.add(reportDataConfigurationProvider.getTableColumnConfig(column.getName()));
			}
		}else{
			CompareHeaderValuesGenerator<R> compareHeaderValuesGenerator = new CompareHeaderValuesGenerator<R>(reportTemplate, false, false);
			List<List<String>> headerValues = compareHeaderValuesGenerator.getHeaderValues();
			List<String> columnNames = getColumnNames();
			for(int i=0;i<headerValues.get(0).size();i++){
				TableColumnConfig<?> actualTableColumnConfig = reportDataConfigurationProvider.getTableColumnConfig(headerValues.get(0).get(i));
				TableColumnConfig<?> newTableColumnConfig = (TableColumnConfig<?>) actualTableColumnConfig.clone();
				newTableColumnConfig.setName(columnNames.get(i));
				tableColumnConfigs.add(newTableColumnConfig);
			}
		}
		return tableColumnConfigs;
	}

	private List<String> getColumnNames() {
		List<ColumnDefinition<R>> columns = reportTemplate.getColumns();
		List<String> columnNames = new ArrayList<String>();
		if(filteredAndSortedItems.size() ==1){
			for(ColumnDefinition<R> column: columns)
			{
				columnNames.add(column.getName());
			}
		}else{
			CompareHeaderValuesGenerator<R> compareHeaderValuesGenerator = new CompareHeaderValuesGenerator<R>(reportTemplate, false, false);
			List<List<String>> headerValues = compareHeaderValuesGenerator.getHeaderValues();
			for(String value: headerValues.get(0)){
				columnNames.add(value);
			}
			for(int j = 1; j < headerValues.size(); j++){
				List<String> columHeaderRow = headerValues.get(j);
				for(int i = 0; i < columHeaderRow.size(); i ++){
					String value = columnNames.get(i);
					if(!columHeaderRow.get(i).trim().isEmpty()){
						value = value + columHeaderRow.get(i);
						columnNames.set(i, value);
					}
				}
			}
		}
		return columnNames;
	}
	
	public ReportDataRows expandGroup(int groupIndex, List<ReportDataIndex> indexes)
	{
		List<ReportData> rtnValue;
		if(indexes.size() == 1){
			ReportDataIndex reportDataIndex = indexes.get(0);
			GroupImpl<R> parentGroup = createGroup(reportDataIndex.getStartIndex(), reportDataIndex.getEndIndex(), filteredAndSortedItems.get(0));
			rtnValue = expandNormalGroup(groupIndex, parentGroup);
		}else{
			CompareGroup<R> compareGroup = new CompareGroup<R>(filteredAndSortedItems.size());
			for(int i = 0; i < filteredAndSortedItems.size(); i ++){
				ReportDataIndex reportDataIndex = indexes.get(i);
				compareGroup.setGroup(i, createGroup(reportDataIndex.getStartIndex(), reportDataIndex.getEndIndex(), filteredAndSortedItems.get(i)));
			}
			rtnValue = expandCompareGroup(groupIndex, compareGroup);
		}		
		if(rtnValue.isEmpty()) throw new RuntimeException("Query returned empty rows, please check your query");
		return new ReportDataRows(rtnValue);
	}
	
	private List<ReportData> expandCompareGroup(int groupIndex, CompareGroup<R> parentCompareGroup) {
		List<CompareGroup<R>> compareGroups = compareGroupGenerator.generateCompareGroups(groupIndex, parentCompareGroup);
		List<ReportData> rtnValue = new ArrayList<ReportData>();
		for(CompareGroup<R> compareGroup: compareGroups)
		{
			Group<R>[] groups = compareGroup.getGroups();
			List<ReportDataIndex> indexes = new ArrayList<ReportDataIndex>(groups.length);
			for(Group<R> group:groups){
				int startIndex = -1;
				int endIndex = -1;
				if(group != null){
					startIndex = group.getStartIndex();
					endIndex = group.getEndIndex();
				}
				indexes.add(new ReportDataIndex(startIndex, endIndex));
			}
			addToReportDataList(rtnValue, new ReportData(indexes, true, compareGroupValuesGenerator.generateValues(compareGroup).toArray()));
			
		}
		if(compareGroups.isEmpty())
		{
			CompareGroup<R> group = parentCompareGroup;
			CompareItemGenerator<R> compareItemGenerator = new CompareItemGenerator<R>(group, new KeyColumnComparator<R>(reportTemplate));
			List<CompareItem<R>> compareItems = compareItemGenerator.generate();
			for(CompareItem<R> item: compareItems)
			{
				for(int itemIndex = 0; itemIndex < item.getNumberOfItems(); itemIndex ++)
				{
					List<String> generateValues = compareItemValuesGenerator.generateValues(item, itemIndex);
					addToReportDataList(rtnValue, new ReportData(itemIndex, itemIndex, false, generateValues.toArray()));
				}
			}
		}
		return rtnValue;
	}
	
	private GroupImpl<R> createGroup(int startIndex, int endIndex, ImmutableList<R> items) {
		if(startIndex == -1) return null;
		GroupImpl<R> parentGroup = new GroupImpl<R>(items, startIndex, endIndex);
		return parentGroup;
	}
	
	private List<ReportData> expandNormalGroup(int groupIndex, GroupImpl<R> parentGroup)
	{
		List<ReportData> rtnValue = new ArrayList<ReportData>();
		if(groupGenerator.getGroupBys().size() == groupIndex)
		{
			int itemsInGroup = parentGroup.getEndIndex() - parentGroup.getStartIndex() +1;
			for(int indexInGroup = 0; indexInGroup < itemsInGroup; indexInGroup ++)
			{
				if(!parentGroup.isValid(indexInGroup)) continue;
				addToReportDataList(rtnValue, new ReportData(indexInGroup, indexInGroup, false, getValues(indexInGroup, parentGroup)));				
			}

		}else{
			List<Group<R>> groups = groupGenerator.generateGroups(groupIndex, parentGroup);
			for(Group<R> group: groups)
			{	
				addToReportDataList(rtnValue, new ReportData(group.getStartIndex(), group.getEndIndex(), true, getValues(group.getValues())));	
			}
		}
		return rtnValue;
	}
	
	private void addToReportDataList(List<ReportData> rtnValue, ReportData reportData) {
		if(rtnValue.size() > maxNumberOfRows) {
			throw new RuntimeException("Report Output Greater Than The Limit "+ maxNumberOfRows + ", Please add filter to reduce the output size");
		}
		rtnValue.add(reportData);
	}
	
	public Object[]getValues(int indexInGroup, GroupImpl<R> group)
	{
		List<ColumnDefinition<R>> columns = reportTemplate.getColumns();
		Object[] rtnValues = new Object[columns.size()];
		for(ColumnDefinition<R> columnDefinition: columns)
		{
			ColumnWorker<R> columnWorker = columnDefinition.getColumnWorker();
			Object value = null;
			if(columnWorker != null) value = columnDefinition.format(columnWorker.getValue(group, indexInGroup));
//			else value = "";
			rtnValues[columnDefinition.getIndex()] = value;

		}
		return rtnValues;
	}
	
	public Object[]getValues(Object[] values)
	{
		List<ColumnDefinition<R>> columns = reportTemplate.getColumns();
		Object[] rtnValues = new Object[columns.size()];
		for(ColumnDefinition<R> columnDefinition: columns)
		{
			Object value = values[columnDefinition.getIndex()];
			if(value != null) value = columnDefinition.format(value);
			rtnValues[columnDefinition.getIndex()] = value;

		}
		return rtnValues;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	protected  void populateReportTemplate(Query query, BaseCompareReportTemplate<R> reportTemplate){
		compareGroupValuesGenerator = new CompareGroupValuesGenerator<R>(reportTemplate, false);
		compareItemValuesGenerator = new CompareItemValuesGenerator<R>(reportTemplate, false);
		boolean flag = false;
		Map<String, ColumnDefinitionBaseImpl> columnsMap = new HashMap<String, ColumnDefinitionBaseImpl>();
		List<Column> columnsToSortOn = new ArrayList<Column>();
		List<Column> columnsToGroupSortOn = new ArrayList<Column>();
		ManyIndexer<String, Filter> columnFiltersMap = new ManyIndexer<String, Filter>();
		ManyIndexer<String, AliasCondition<Object>> columnAliasMap = new ManyIndexer<String, AliasCondition<Object>>();
		try {
			for(Filter filter: query.getValidFilters())
			{
				columnFiltersMap.index(filter.getColumnName(), filter);
			}
			for(Entry<String, List<Filter>> entry: columnFiltersMap.entrySet()){
	        	List<Filter> filters = entry.getValue();
	        	for (Filter aliasFilter : filters) {
	        		if(!aliasFilter.getAlias().trim().equals("")) {
	        				Condition<Object> newInstance = reportDataConfigurationProvider.getCondition(aliasFilter);
	         				if(newInstance != null)	{
	         					newInstance.setUserId(this.getUserId());
	         					columnAliasMap.index(aliasFilter.getColumnName(), new AliasCondition<Object>(aliasFilter.getAlias(),newInstance));
	         			}
	        		}
        		}
        	}

			List<ColumnDefinition<R>> groupColumns = new ArrayList<ColumnDefinition<R>>();
			for(Column column: query.getSelectedColumns()){
				
				Evaluator<R, Object> evaluator = reportDataConfigurationProvider.getEvaluator(column);
				
				Formatter<Object> formater = reportDataConfigurationProvider.getFormatter(column);
				
				Function<Object> functionObject = reportDataConfigurationProvider.getFunction(column);
				
				Comparator<Object> comparator = reportDataConfigurationProvider.getComparator(column);

				List<CompareFunction<?>> compareFunctions = reportDataConfigurationProvider.getCompareFunctions(column, query.getObject().length);

				ColumnDefinitionImpl aliasColumn=null;
				if(columnAliasMap.contains(column.getName())){
					AliasColumnEvaluator<R, Object> aliasEvaluator = new AliasColumnEvaluator<R, Object>(evaluator);
					aliasColumn= new ColumnDefinitionImpl(column.getName()+"Alias", 0, aliasEvaluator,  new ObjectFormatter(),null , null);
					aliasEvaluator.setAliasConditions(columnAliasMap.get(column.getName()), formater);
					reportTemplate.addColumn(aliasColumn);
					columnsMap.put(aliasColumn.getName(), aliasColumn);
				}

				ColumnDefinitionBaseImpl templateColumn= new ColumnDefinitionImpl(column.getName(), 0, evaluator, formater,functionObject , null, comparator);
				if(evaluator instanceof GroupEvaluator){
					templateColumn= new GroupColumnDefinitionImpl(column.getName(), 0, (GroupEvaluator)evaluator, formater,functionObject , null, comparator);
				}
				ColumnConfig<? super R, ?> columnConfig = reportDataConfigurationProvider.getColumnConfig(column);
				templateColumn.setType(columnConfig.getType());
				templateColumn.setSplitter(columnConfig.getSplitter());
				
				if(!column.isHidden()){
				reportTemplate.addColumn(templateColumn);
				}
				
				columnsMap.put(column.getName(), templateColumn);
				
				for(CompareFunction<?> compareFunction: compareFunctions){
					reportTemplate.setCompareFunction(reportTemplate.getColumns().size()-1, compareFunction);
				}

				
				if(column.isGroupBy()){
					if(query.isGroupReport()){
						if(!flag) {reportTemplate.addGroupBy(new ColumnGroupBy<R>());flag=true;}
					}
					if(query.isSingleGrouping()){
						if(!column.isHidden()){
							groupColumns.add(templateColumn);
						}
						if(aliasColumn != null) groupColumns.add(aliasColumn);
					}else{
						if(query.isGroupReport()){
							if(aliasColumn != null) reportTemplate.addGroupBy(new ColumnGroupBy<R>(reportTemplate.getLastGroupBy(), aliasColumn));
							if(!column.isHidden()){
								reportTemplate.addGroupBy(new ColumnGroupBy<R>(reportTemplate.getLastGroupBy(), templateColumn));
							}
						}
					}
				}
				if(column.getSortOrder() != 0){
					columnsToSortOn.add(column);
				}
				if(column.getGroupSortOrder() != 0){
					columnsToGroupSortOn.add(column);
				}
			}
			if(!groupColumns.isEmpty()){
				reportTemplate.addGroupBy(new ColumnGroupBy<R>(reportTemplate.getLastGroupBy()).addColumns(groupColumns));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} 
		Collections.sort(columnsToSortOn, new MySortComparator());
		ColumnComparator<R> columnComparator = new ColumnComparator<R>();
		for(Column column: columnsToSortOn){
			ColumnDefinition<R> columnDefinition = columnsMap.get(column.getName());
			columnComparator.addColumn(columnDefinition);
			if(column.getSortOrder() < 0){
				columnComparator.addDescendingColumn(columnDefinition);
			}
		}
		reportTemplate.setComparator(columnComparator);
		Collections.sort(columnsToGroupSortOn, new MyGroupSortComparator());
		GroupColumnComparator<R> groupColumnComparator = new GroupColumnComparator<R>();
		for(Column column: columnsToGroupSortOn){
			ColumnDefinition<R> columnDefinition = columnsMap.get(column.getName());
			groupColumnComparator.addColumn(columnDefinition);
			if(column.getGroupSortOrder() < 0){
				groupColumnComparator.addDescendingColumn(columnDefinition);
			}
		}
		reportTemplate.setGroupComparator(groupColumnComparator);
		ColumnAndSelector<R> columnAndSelector = new ColumnAndSelector<R>();
		try {
			for(Entry<String, List<Filter>> entry: columnFiltersMap.entrySet())	{
				ColumnDefinitionBaseImpl column = columnsMap.get(entry.getKey());
				if(column == null) throw new RuntimeException("Please select the column used in the filter : " + entry.getKey());
				ColumnSelector<R, Object> selector = new ColumnSelector<R, Object>(column);
				for(Filter filter: entry.getValue()){
					Condition<Object> newInstance = reportDataConfigurationProvider.getCondition(filter);
     				if(newInstance != null) {
     					newInstance.setUserId(this.getUserId());
     					selector.addCondition(newInstance);     					
     				}
				}
				columnAndSelector.addSelector(selector);
			}
			ColumnAndSelector<R> enhancedSelector = enhance(columnAndSelector);
			AndSelector<R> andSelector = new AndSelector<R>();
			andSelector.addSelector(enhancedSelector);
			if(query.getCompoundFilter() != null){
				andSelector.addSelector(query.getCompoundFilter().getSelector(reportDataConfigurationProvider, columnsMap, this.getUserId()));
			}
			reportTemplate.setSelector(andSelector);
			
			AndSelector<Group<R>> groupColumnSelector =  new AndSelector<Group<R>>();
			for(Filter filter: query.getGroupFilters()){
				if(!(filter.getCondition()==null && filter.getValue()==null)){
				Selector<? super Group<R>> selector = null;
				Condition<Object> newInstance = reportDataConfigurationProvider.getCondition(filter);
				if(newInstance != null) {
					newInstance.setUserId(this.getUserId());
					ColumnDefinitionBaseImpl column = columnsMap.get(filter.getColumnName());
					if(column == null) throw new RuntimeException("Please select the column used in the group filter : " + filter.getColumnName());
					selector =	 new GroupColumnSelector<R, Object>(column,newInstance, columnsMap.get(filter.getAlias()));
				}
				groupColumnSelector.addSelector(selector);
				}
			}
			reportTemplate.setGroupSelector(groupColumnSelector);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
			
	}
	
	
	protected ColumnAndSelector<R> enhance(ColumnAndSelector<R> columnSelector) {
		return reportDataConfigurationProvider.getReportDataItemsProvider().enhance(columnSelector);
	}

	static class MySortComparator implements Comparator<Column>
	{

		@Override
		public int compare(Column arg0, Column arg1) {
			return Math.abs(arg0.getSortOrder())-Math.abs(arg1.getSortOrder());
		}
		
	}
	static class MyGroupSortComparator implements Comparator<Column>
	{

		@Override
		public int compare(Column arg0, Column arg1) {
			return Math.abs(arg0.getGroupSortOrder())-Math.abs(arg1.getGroupSortOrder());
		}
		
	}
}