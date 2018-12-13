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
import yogi.paging.command.FilterTablePageCommand;
import yogi.paging.command.GetTablePageCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;

public class PagingCacheTestServer extends TestCase{

	public PagingCacheTestServer() throws CommandException {
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
		
	//@Test for testing sort functionality
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

		//Changes changes = getChanges();
				
		//applyChanges();
		//ApplyTablePageChangesCommand<Recom> applyTablePageChangesCommand = new ApplyTablePageChangesCommand<Recom>(0, changes, 0);
		//CommandExecutor.get().execute(applyTablePageChangesCommand);		
				
		System.out.println("changes applied");
		
		//SortTablePageCommand<Recom> sortTablePageCommand = new SortTablePageCommand<Recom>(0, changes, 0, 8, true);
		//CommandExecutor.get().execute(sortTablePageCommand);

		FilterConfig fc0 = new FilterConfig(0, "In", "I");
		FilterConfig fc1 = new FilterConfig(1, "In", "AEX");
		FilterConfig fc2 = new FilterConfig(2, "Like", "ae%");
		FilterConfig fc3 = new FilterConfig(3, "In", "FA2AA");
		FilterConfig fc4 = new FilterConfig(4, "In", "1");
		FilterConfig fc5 = new FilterConfig(5, "In", "52, 55");
		FilterConfig fc6 = new FilterConfig(6, "In", "3");
		FilterConfig fc8 = new FilterConfig(8, "LessThanOrEquals", "1150.0");
		FilterConfig fc11 = new FilterConfig(11, "LessThan", "1000");
				
		List<FilterConfig> filterData = new ArrayList<FilterConfig>();
		filterData.add(fc0);
		//filterData.add(fc1);
		filterData.add(fc2);
		//filterData.add(fc3);
		//filterData.add(fc4);
		//filterData.add(fc5);
		//filterData.add(fc8);
		//filterData.add(fc11);
		
		FilterCriteria filterCriteria = new FilterCriteria(filterData);
		
		// Clear the previous filters, if any
		FilterTablePageCommand<Recom> filterTablePageCommand = new FilterTablePageCommand<Recom>(0, null, true, filterCriteria,"AA123456");
		CommandExecutor.get().execute(filterTablePageCommand);
		
		filterData.add(fc3);
		FilterCriteria filterCriteria1 = new FilterCriteria(filterData);
		
		// Don't clear the previous filter
		FilterTablePageCommand<Recom> filterTablePageCommand1 = new FilterTablePageCommand<Recom>(0, null, false, filterCriteria1,"AA123456");
		CommandExecutor.get().execute(filterTablePageCommand1);
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = CommandExecutor.get().execute(getTablePage);
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());

		System.out.println("Dataset ID - printing filtered data : " + 0);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));		
	}
	
	/*
	@SuppressWarnings("unused")
	public void testGetPage() throws CommandException {

		int startIndex = 0;
		int endIndex = 15;

		TablePage tp; 
		ItemMapper itemMapper = new ItemMapper();
		
		GetTablePageCommand<Recom> getTablePage = new GetTablePageCommand<Recom>(1, startIndex, endIndex);
		tp = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(itemMapper);
		printStringArrayList(tp.getData());			

		System.out.println("Dataset ID : " + 1);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

		//Changes changes = getChanges();
				
		//applyChanges();
		//ApplyTablePageChangesCommand<Recom> applyTablePageChangesCommand = new ApplyTablePageChangesCommand<Recom>(0, changes, 0);
		//CommandExecutor.get().execute(applyTablePageChangesCommand);		
				
		System.out.println("changes applied");
		
		//SortTablePageCommand<Recom> sortTablePageCommand = new SortTablePageCommand<Recom>(0, changes, 0, 8, true);
		//CommandExecutor.get().execute(sortTablePageCommand);

		FilterConfig fc0 = new FilterConfig(0, "Equals", "Switch");
		FilterConfig fc1 = new FilterConfig(1, "Equals", "Yellow");
		FilterConfig fc2 = new FilterConfig(2, "Equals", "1");
		FilterConfig fc3 = new FilterConfig(3, "Equals", "1226.00");
		FilterConfig fc4 = new FilterConfig(4, "Equals", "High");
		FilterConfig fc5 = new FilterConfig(5, "Equals", "true");
		
				
		List<FilterConfig> filterData = new ArrayList<FilterConfig>();
		//filterData.add(fc0);
		//filterData.add(fc1);
		//filterData.add(fc2);
		//filterData.add(fc3);
		//filterData.add(fc4);
		filterData.add(fc5);
		
		FilterCriteria filterCriteria = new FilterCriteria(filterData);
		
		FilterTablePageCommand<Recom> filterTablePageCommand = new FilterTablePageCommand<Recom>(1, null, 0, filterCriteria);
		CommandExecutor.get().execute(filterTablePageCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(1, startIndex, endIndex);
		tp = CommandExecutor.get().execute(getTablePage);
		
		printColumnHeaderNames(itemMapper);
		printStringArrayList(tp.getData());

		System.out.println("Dataset ID : " + 1);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));		
	}
	*/
	
	/*
	public Changes getChanges() throws CommandException {
				
		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();

		addedRowList.add(new AddedRow(10, 1, new String[]{"A","MAA","PHX","LANKT131","1","0002","92","USD","1226.00"}));
		//addedRowList.add(new AddedRow(10, 2, new String[]{"A","DAL","PHX","LANKT131","1","0002","91","USD","1226.00"}));
		//addedRowList.add(new AddedRow(25, 1, new String[]{"A","DAL","PHX","LANKT131","1","0002","241","USD","1226.00"}));

		deletedRowList.add(new DeletedRow(6));
		//deletedRowList.add(new DeletedRow(18));

		Map<Integer, String> modifiedEntries;
		modifiedEntries = new HashMap<Integer, String>();
		modifiedEntries.put(new Integer(2), "DEF");
		modifiedEntries.put(new Integer(1), "ABC");
		updatedRowList.add(new UpdatedRow(12, modifiedEntries));

		modifiedEntries = new HashMap<Integer, String>();
		modifiedEntries.put(new Integer(1), "XYZ");
		modifiedEntries.put(new Integer(0), "C");
		updatedRowList.add(new UpdatedRow(4, modifiedEntries));

		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);
		
		return changes;
	}
	*/
	/*
	//@Test
	public void testGetSubsequentPage() throws CommandException {
		int startIndex = 0;
		int endIndex = 809;

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp1 = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());

		System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

	}


	//@Test
	public void testGetSubsequentPage1() throws CommandException {
		int startIndex = 795;
		int endIndex = 800;

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp1 = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());

		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

	}

	 */

	/*
	//@Test
	public void testAddRecords() throws CommandException {

		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();

		addedRowList.add(new AddedRow(95, 1, new String[]{"A","DAL","PHX","LANKT131","1","0002","111","USD","1226.00"}));
		addedRowList.add(new AddedRow(95, 2, new String[]{"A","DAL","PHX","LANKT131","1","0002","222","USD","1226.00"}));
		addedRowList.add(new AddedRow(95, 3, new String[]{"A","DAL","PHX","LANKT131","1","0002","333","USD","1226.00"}));

		addedRowList.add(new AddedRow(92, 1, new String[]{"A","DAL","PHX","LANKT131","1","0002","555","USD","1226.00"}));

		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);

		int startIndex = 790;
		int endIndex = 803;

		changedPage = new GetTablePageApplyChangesCommand<Recom>(0, startIndex, endIndex, changes, 700);
		TablePage tp1 = CommandExecutor.get().execute(changedPage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());

		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

		startIndex = 800;
		endIndex = tp1.getTableSize();

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());

		System.out.println("Total Table Size : " + tp2.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

	}


	//@Test
	public void testDeleteRecords() throws CommandException {

		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();

		deletedRowList.add(new DeletedRow(95));
		deletedRowList.add(new DeletedRow(96));

		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);

		int startIndex = 690;
		int endIndex = 710;

		changedPage = new GetTablePageApplyChangesCommand<Recom>(0, startIndex, endIndex, changes, 700);
		TablePage tp1 = CommandExecutor.get().execute(changedPage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());

		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

		startIndex = 0;
		endIndex = tp1.getTableSize();

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());

		System.out.println("Total Table Size : " + tp2.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

	}




	//@Test
	public void testUpdateRecords() throws CommandException {

		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();

		Map<Integer, String> modifiedEntries;
		modifiedEntries = new HashMap<Integer, String>();
		modifiedEntries.put(new Integer(1), "XYZ");
		modifiedEntries.put(new Integer(0), "M");
		updatedRowList.add(new UpdatedRow(99, modifiedEntries));

		modifiedEntries = new HashMap<Integer, String>();
		modifiedEntries.put(new Integer(1), "DFW");
		modifiedEntries.put(new Integer(0), "C");
		updatedRowList.add(new UpdatedRow(98, modifiedEntries));

		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);

		int startIndex = 790;
		int endIndex = 800;

		changedPage = new GetTablePageApplyChangesCommand<Recom>(0, startIndex, endIndex, changes, 700);
		TablePage tp1 = CommandExecutor.get().execute(changedPage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());

		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

		startIndex = 0;
		endIndex = tp1.getTableSize();

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());

		System.out.println("Total Table Size : " + tp2.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

	}
	 */

	/*
	//@Test
	public void testModifyRecords() throws CommandException {

		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();

		addedRowList.add(new AddedRow(10, 1, new String[]{"A","DAL","PHX","LANKT131","1","0002","92","USD","1226.00"}));
		addedRowList.add(new AddedRow(10, 2, new String[]{"A","DAL","PHX","LANKT131","1","0002","91","USD","1226.00"}));
		addedRowList.add(new AddedRow(25, 1, new String[]{"A","DAL","PHX","LANKT131","1","0002","241","USD","1226.00"}));

		deletedRowList.add(new DeletedRow(15));
		deletedRowList.add(new DeletedRow(18));

		Map<Integer, String> modifiedEntries;
		modifiedEntries = new HashMap<Integer, String>();
		modifiedEntries.put(new Integer(1), "XYZ");
		modifiedEntries.put(new Integer(0), "M");
		updatedRowList.add(new UpdatedRow(20, modifiedEntries));

		modifiedEntries = new HashMap<Integer, String>();
		modifiedEntries.put(new Integer(1), "DFW");
		modifiedEntries.put(new Integer(0), "C");
		updatedRowList.add(new UpdatedRow(23, modifiedEntries));

		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);

		int startIndex = 100;
		int endIndex = 130;

		changedPage = new GetTablePageApplyChangesCommand<Recom>(0, startIndex, endIndex, changes, 100);
		TablePage tp1 = CommandExecutor.get().execute(changedPage);

		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());

		startIndex = 0;
		endIndex = tp1.getTableSize();

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());
	}



	@Test
	public void testDataset() throws CommandException {


		//recomSet = new RecomSet(100);
		//submitTableData = new SubmitTableDataCommand<Recom>(recomSet.getData(), recomMapper);
		//datasetId = CommandExecutor.get().execute(submitTableData);


		int startIndex = 100;
		int endIndex = 130;

		getTablePage = new GetTablePageCommand<Recom>(datasetId, startIndex, endIndex);
		TablePage tp = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getRows());			

		System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));


		SubmitTableDataCommand<Item> submitTableDataForItem;
		GetTablePageCommand<Item> getTablePageForItem;

		itemSet = new ItemSet(100);
		submitTableDataForItem = new SubmitTableDataCommand<Item>(itemSet.getData(), itemMapper);
		datasetId = CommandExecutor.get().execute(submitTableDataForItem);

		startIndex = 0;
		endIndex = 10;

		getTablePageForItem = new GetTablePageCommand<Item>(datasetId, startIndex, endIndex);
		TablePage tpForItem = CommandExecutor.get().execute(getTablePageForItem);

		printColumnHeaderNames(itemMapper);
		printStringArrayList(tpForItem.getRows());			

		System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tpForItem.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));

		startIndex = 100;
		endIndex = 130;

		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		tp = CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getRows());			

		System.out.println("Dataset ID : " + 0);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));
	}
	 */

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
