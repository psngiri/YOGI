package yogi.report.server.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;
import yogi.base.io.FileToStringListReader;
import yogi.paging.TableActionConfig;
import yogi.report.server.ReportDataItemsProvider;
import yogi.report.server.config.column.CountColumnConfig;

public class ReportConfig<R> implements Serializable{

	private static final long serialVersionUID = 1L;
	private String type;
	private String name;
	private transient ReportDataItemsProvider<R> reportDataItems;
	private transient List<ColumnConfig<?, ?>> columns;
	private Map<String, ColumnConfig<? super R, ?>> columnsMap;
	private Validator validator;
	private transient List<ActionConfig> actions;
	private Map<String, ActionConfig> actionsMap; 
	private transient List<ViewConfig> views;
	private Map<String, ViewConfig> viewsMap; 
	private List<TableActionConfig> tableActionConfigs = new ArrayList<TableActionConfig>();
	private String defaultFindColumnName;
	private String dataSetCommand;
	private String dataSetLabelName;
	private boolean defaultEditSelectAllValue = true;
	
	public ReportConfig(String reportType, String reportName,
			ReportDataItemsProvider<R> reportDataItems, Validator validator) {
		super();
		this.type = reportType;
		this.name = reportName;
		this.reportDataItems = reportDataItems;
		columnsMap= new LinkedHashMap<String, ColumnConfig<? super R, ?>>();
		this.validator= validator;		
		actionsMap= new LinkedHashMap<String, ActionConfig>();
		viewsMap = new LinkedHashMap<String, ViewConfig>();
		this.addColumn(new CountColumnConfig<Object>());
	}

	public List<TableActionConfig> getTableActionConfigs() {
		return tableActionConfigs;
	}
	
	public void addTableActionConfig(TableActionConfig tableActionConfig){
		tableActionConfigs.add(tableActionConfig);
	}
	
	public void addDefaultFindColumnName(String defaultFindColumnName){
		this.defaultFindColumnName = defaultFindColumnName;
	}
	
	public String getDefaultFindColumnName() {
		return defaultFindColumnName;
	}
	
	public void addDefaultEditSelectAllValue(boolean defaultEditSelectAllValue){
		this.defaultEditSelectAllValue = defaultEditSelectAllValue;
	}
	
	public boolean isDefaultEditSelectAllValue() {
		return defaultEditSelectAllValue;
	}

	public void addTableActionConfigs(List<TableActionConfig> tableActionConfigs) {
		this.tableActionConfigs.addAll(tableActionConfigs);
	}
	
	public void addColumn(ColumnConfig<? super R, ?> column)
	{
		columnsMap.put(column.getName(), column);
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public ReportDataItemsProvider<R> getReportDataItems() {
		return reportDataItems;
	}

	public List<ColumnConfig<?, ?>> getColumns() {
		if(columns == null)
		{
			columns = new ArrayList<ColumnConfig<?, ?>>();
			for(Map.Entry<String, ColumnConfig<? super R, ?>> entry: columnsMap.entrySet())
			{
				columns.add(entry.getValue());
			}
		}
		return columns;
	}
	
	public ColumnConfig<? super R, ?> getColumn(String columnName)
	{
		return getColumnsMap().get(columnName);
	}

	private Map<String, ColumnConfig<? super R, ?>> getColumnsMap() {
		return columnsMap;
	}

	
	public Validator getValidator() {
		return validator;
	}

	public List<ActionConfig> getActions() {
		if(actions == null)
		{
			actions = new ArrayList<ActionConfig>();
			for(Map.Entry<String, ActionConfig> entry: actionsMap.entrySet())
			{
				actions.add(entry.getValue());
			}
		}
		return actions;
	}	
	
	public void addAction(ActionConfig action) {
		actionsMap.put(action.getButtonName(), action);
	}	
	
	public List<ViewConfig> getViews() {
		if(views == null)
		{
			views = new ArrayList<ViewConfig>();
			for(Map.Entry<String, ViewConfig> entry: viewsMap.entrySet())
			{
				views.add(entry.getValue());
			}
		}
		return views;
	}	
	
	public void addView(ViewConfig view) {
		viewsMap.put(view.getTabName(), view);
	}

	public String getDataSetCommand() {
		return dataSetCommand;
	}

	public void setDataSetCommand(Class<? extends Command<?>> dataSetCommand) {
		this.dataSetCommand = dataSetCommand.getName();
	}

	public String getDataSetLabelName() {
		return dataSetLabelName;
	}

	public void setDataSetLabelName(String dataSetLabelName) {
		this.dataSetLabelName = dataSetLabelName;
	}	
	
	public void loadDescriptions(){
		try {
			String columnDescriptionsFileName="";
			if(columnDescriptionsFileName.isEmpty()) columnDescriptionsFileName = ApplicationProperties.InputDataLocation+"/data/"+name+".csv";
			FileToStringListReader reader = new FileToStringListReader(columnDescriptionsFileName,1);
			List<String> values = reader.read();
			values.stream().forEach(x->{
				String[] split = x.split(",",2);
				if(split.length==2){
					ColumnConfig<? super R, ?> column = getColumn(split[0].trim());
					if(column!=null){
						String desc = split[1].trim();
						if(desc.startsWith("\"")) desc=desc.substring(1, desc.length()-1);
						if(!desc.isEmpty())column.setDescription(desc);
					}else{
						ErrorReporter.get().warning("ColumnDefinitionNotFound ", split[0]);
					}
				}
				
			});
		} catch (Exception e) {
			ErrorReporter.get().info("Could not load column descriptions for "+name+" due to :"+ e.getMessage());
		}
		
	}
	
}
