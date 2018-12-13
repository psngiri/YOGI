

package yogi.paging.test;

//import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import yogi.base.app.testing.TestErrorReporter;
import yogi.paging.TableData;
import yogi.paging.TableDataMapper;
import yogi.paging.TablePage;
import yogi.paging.TableRecord;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.command.GetTableColumnHeaderCommand;
import yogi.paging.command.GetTablePageCommand;
import yogi.paging.command.SubmitTableDataCommand;
import yogi.paging.command.TableConfig;
import yogi.paging.server.PagingServer;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;

public class PagingCacheServerSetupTestServer extends TestCase{

	RecomSet recomSet;
	RecomMapper recomMapper;
	TableData<Recom> tableData;

	ItemSet itemSet;
	ItemMapper itemMapper;
	RecomMapper recomMapper1;
	
	RecomSet recomSet1;
	
	PagingServer pagingServer;
	SubmitTableDataCommand<Recom> submitTableData;
	GetTablePageCommand<Recom> getTablePage;
	SubmitTableDataCommand<Recom> submitTableData1;
	SubmitTableDataCommand<Item> submitTableDataForItem;
	

	public void init() {

		recomMapper = new RecomMapper();
		recomSet = new RecomSet();
		submitTableData = new SubmitTableDataCommand<Recom>(recomSet.getLimitedData(), recomMapper,-1,null);
		
		itemMapper = new ItemMapper();		
		itemSet = new ItemSet(10000);
		submitTableDataForItem = new SubmitTableDataCommand<Item>(itemSet.getData(), itemMapper,-1,null);

		recomMapper1 = new RecomMapper();		
		recomSet1 = new RecomSet();
		submitTableData1 = new SubmitTableDataCommand<Recom>(recomSet1.getLimitedData(), recomMapper1,-1,null);
		
		TestErrorReporter.start();
		try {
			System.out.println("init done....");
			CommandExecutor.get().execute(submitTableData);
			CommandExecutor.get().execute(submitTableDataForItem);
			CommandExecutor.get().execute(submitTableData1);
		} catch (CommandException e) {			
			e.printStackTrace();
		}
		TestErrorReporter.end();
	}

	
	public PagingCacheServerSetupTestServer() throws CommandException {
		super();
		CommandExecutor.commandServerAddressesColonPortNumbers = "localhost:9998";
		//CommandExecutor.commandServerAddressesColonPortNumbers = "10.97.85.81:5088";
		//CommandExecutor.commandServerAddressesColonPortNumbers = "rmprit01.qcorpaa.aa.com:50002";

		BaseCommandExecutor.Initialized = true;
		CommandExecutor.UsePull = true;

		init();
		recomMapper = new RecomMapper();
		itemMapper = new ItemMapper();
	}

	@Before
	public void setUp() throws Exception {
		TestErrorReporter.start();
	}

	@After
	public void tearDown() throws Exception {
		TestErrorReporter.end();
	}

	//@Test
	public void testGetPage() throws CommandException {

		GetTableColumnHeaderCommand<Recom> getHeaderCommand = new GetTableColumnHeaderCommand<Recom>(0,"AA123456");
		TableConfig tableConfig = CommandExecutor.get().execute(getHeaderCommand);
		TableColumnConfig<?>[] header = tableConfig.getTableColumnConfigs();
		
		for(TableColumnConfig<?> config : header) {
			System.out.println("Name : " + config.getName());
			System.out.println("Type : " + config.getFieldType());
			System.out.println("Width : " + config.getWidth());
			System.out.println("ReadOnly : " + config.isReadOnly());
			System.out.println("Validator : " + config.getValidator());
			System.out.println("TypeConfig : " + config.getConfig());
		}
		
		int startIndex = 0;
		int endIndex = 49;

		GetTablePageCommand<Recom> getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		TablePage tp = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());			

		System.out.println("Dataset ID : " + 0);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));
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
				
				if(j==3 || j==8 || j==9 || j==10 || j==11 || j==16 || j==17 || j==18 || j==19 || j==20) {
					if(row[j].length() <= 4)
						System.out.print("  " + row[j] + "\t\t" + "|" );
					else 
						System.out.print("  " + row[j] + "\t" + "|" );
				}
				else
					System.out.print(" " + row[j] + "\t" + "|" );
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
}
