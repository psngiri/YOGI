package yogi.paging.test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import yogi.base.app.testing.TestErrorReporter;
import yogi.paging.FilterConfig;
import yogi.paging.FilterCriteria;
import yogi.paging.TableDataMapper;
import yogi.paging.TablePage;
import yogi.paging.TableRecord;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.command.GetSpecifiedTableDataCommand;
import yogi.paging.command.GetTablePageCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;

public class GetSpecifiedTableDataCommandTestServer extends TestCase{

	public GetSpecifiedTableDataCommandTestServer() throws CommandException {
		super();
		CommandExecutor.commandServerAddressesColonPortNumbers = "localhost:9998";
		//CommandExecutor.commandServerAddressesColonPortNumbers = "10.97.85.81:5088";
		//CommandExecutor.commandServerAddressesColonPortNumbers = "rmprit01.qcorpaa.aa.com:50002";

		BaseCommandExecutor.Initialized = true;
		CommandExecutor.UsePull = true;
	}

	@Before
	public void setUp() throws Exception {
		TestErrorReporter.start();
	}

	@After
	public void tearDown() throws Exception {
		TestErrorReporter.end();
	}
		
	@SuppressWarnings("unused")
	public void testGetPage() throws CommandException {

		int startIndex = 0;
		int endIndex = 15;

		TablePage tp; 
		RecomMapper recomMapper = new RecomMapper();
		
		GetTablePageCommand<Recom> getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());			

		System.out.println("Dataset ID : " + 0);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));
		
		FilterConfig fc0 = new FilterConfig(0, "Equals", "I");
		FilterConfig fc1 = new FilterConfig(1, "Equals", "act");
		FilterConfig fc2 = new FilterConfig(2, "Equals", "aex");
		FilterConfig fc3 = new FilterConfig(3, "Equals", "FA2AA");
		FilterConfig fc4 = new FilterConfig(4, "Equals", "1");
		FilterConfig fc5 = new FilterConfig(5, "In", "52, 54");
		FilterConfig fc6 = new FilterConfig(6, "Equals", "3");
		FilterConfig fc8 = new FilterConfig(8, "LessThanOrEquals", "1000.0");
		FilterConfig fc11 = new FilterConfig(11, "LessThan", "1000");
				
		List<FilterConfig> filterData = new ArrayList<FilterConfig>();
		filterData.add(fc0);
		//filterData.add(fc1);
		//filterData.add(fc2);
		filterData.add(fc3);
		//filterData.add(fc4);
		filterData.add(fc5);
		filterData.add(fc8);
		filterData.add(fc11);
		
		FilterCriteria filterCriteria = new FilterCriteria(filterData);
		
		List<Integer> columnIndices = new ArrayList<Integer>();
		columnIndices.add(0);
		columnIndices.add(1);
		columnIndices.add(2);
		columnIndices.add(3);
		columnIndices.add(4);
		columnIndices.add(8);
		columnIndices.add(9);
		GetSpecifiedTableDataCommand<Recom> getSpecifiedTableDataCommand = new GetSpecifiedTableDataCommand<Recom>(0, filterCriteria, "AA123456", columnIndices);
		List<String[]> data = CommandExecutor.get().execute(getSpecifiedTableDataCommand);
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0 ; i < data.size(); i++) 
		{
			String[] row = data.get(i);
			for(int j = 0; j < row.length; j++) {
				System.out.print(" " + row[j] + "\t" + "|" );
			}
			System.out.println();
		}		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());	
	
	}

	private void printColumnHeaderNames(TableDataMapper<?, ?> mapper) {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < mapper.getColumnCount(); i++) {
			TableColumnConfig<?> columnConfigs = mapper.getColumnConfig(i);
			System.out.print("  " + columnConfigs.getName() + "\t" + "|" );
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private void printStringArrayList(List<TableRecord> list){

		for(int i = 0 ; i < list.size(); i++) {
			String[] row = list.get(i).getRow();
			for(int j = 0; j < row.length; j++) {
				System.out.print(" " + row[j] + "\t" + "|" );
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

}
