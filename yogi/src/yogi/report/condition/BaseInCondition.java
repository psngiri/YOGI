package yogi.report.condition;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yogi.base.io.FileToStringListReader;
import yogi.base.io.resource.FileResource;
import yogi.base.util.JsonAssistant;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.remote.gson.GsonCommand;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportTableData;
import yogi.report.server.command.GenerateReportCommand;
import yogi.server.gui.report.ReportImpl;
import yogi.server.gui.report.command.ReportGetCommand;


public abstract class BaseInCondition<C> extends ConditionBaseImpl<C> {
	public static String filePatterString = "File:(.+?):(\\d+)";
	protected HashSet<C> values = new HashSet<C>();
	public static String UploadedDataFileLocation = "/tmp/";
	public static Pattern filePattern = Pattern.compile(filePatterString);
	public static String queryPatterString = "Query:(.+?):(.+?):(.+?)";
	public static Pattern queryPattern = Pattern.compile(queryPatterString);
	public static String serverType = "GuiServer";	
	
	public BaseInCondition(String value) {
		this(value, ',');
	}
	
	public BaseInCondition(String value, char separator) {
		super(value);
		if(processFromFile(value)) return;
		if(processFromQuery(value)) return;
		Scanner scanner = new Scanner(value);
		scanner.useDelimiter(String.valueOf(separator));
		while(scanner.hasNext())
		{
			scanItem(scanner.next().trim());
		}
	}

	private boolean processFromQuery(String value) {
		Matcher m = queryPattern.matcher(value);
		boolean flag = false;
		while (m.find()) {
			flag = true;
			String queryName =  m.group(1);
			String userId = m.group(2);
			String partitionCode = m.group(3);
			ReportGetCommand reportGetCommand = new ReportGetCommand(queryName, userId, partitionCode);
			String gsonValue = JsonAssistant.get().toJson(reportGetCommand);
			GsonCommand gsonCommand = new GsonCommand(ReportGetCommand.class.getName(), gsonValue , userId);
			Query query = null;
			try {
				String execute = MultiServerCommandExecutor.get().execute(serverType,gsonCommand);
				ReportImpl reportData = JsonAssistant.get().fromJson(execute, ReportImpl.class);
				query = reportData.getData().getQuery();
				query = new Query(query.getServerType(), query.getObject(), query.getReportName(), query.getSelectedColumns(), query.getFilters(), query.getGroupFilters(),query.isSingleGrouping(),!query.isGroupReport());

				GenerateReportCommand reportCommand = new GenerateReportCommand(query, 0, userId);
				ReportTableData reportTableData = MultiServerCommandExecutor.get().execute(query.getServerType(),reportCommand);
				int columnIndex = 0;
				
				for(ReportData row: reportTableData.getRows()){
					String item = row.getValue(columnIndex).toString();
					processItem(item);
				}
			} catch (CommandException e) {
				throw new RuntimeException(e.getMessage(),e);
			}
		}
		return flag;
	}

	private boolean processFromFile(String value) {
		Matcher m = filePattern.matcher(value);
		boolean flag = false;
		while (m.find()) {
			flag = true;
			FileResource resource =  new FileResource(UploadedDataFileLocation+ m.group(1));
			int numberOfHeaderlines = Integer.parseInt(m.group(2));
			FileToStringListReader fileToStringListReader = new FileToStringListReader(resource, numberOfHeaderlines);
			List<String> read = fileToStringListReader.read();
			for(String item: read){
				processItem(item);
			}
		}
		return flag;
	}

	private void processItem(String item) {
		String trim = item.trim();
		if(trim.isEmpty())
			return;
		scanItem(trim);
	}
	
	protected void scanItem(String token) {
		values.add(scan(token));
	}

	public abstract C scan(String token);

	public boolean satisfied(C data) {
		if(data == null) return false;
		return values.contains(data);
	}

	@Override
	public String getSqlValue(String columnName) {
		if(values.isEmpty())
			return "";
		StringBuilder rtnvalue = new StringBuilder();
		rtnvalue.append(' ');
		rtnvalue.append(columnName);
		if(getSqlCondition() == "!=")
			rtnvalue.append(" not");
		rtnvalue.append(" in (");
		for(C value: values){
			rtnvalue.append("'");
			rtnvalue.append(this.getFormatter().format(value));
			rtnvalue.append("',");
		}
		rtnvalue.deleteCharAt(rtnvalue.length()-1);
		rtnvalue.append(")");
		return rtnvalue.toString();
	}
	
	public HashSet<C> getValues() {
		return values;
	}
}
