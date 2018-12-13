package yogi.paging.test;

//import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import yogi.paging.TableData;
import yogi.paging.TableDataMapper;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.command.GetTablePageCommand;
import yogi.paging.command.SubmitTableDataCommand;
import yogi.paging.server.PagingServer;

public class PagingCacheTest extends TestCase {

	RecomSet recomSet;
	RecomMapper recomMapper;
	TableData<Recom> tableData;
	
	PagingServer pagingServer;
	SubmitTableDataCommand<Recom> submitTableData;
	GetTablePageCommand<Recom> getTablePage;
	//GetTablePageApplyChangesCommand<Recom> changedPage;
	
	Integer datasetId;
	
	ItemSet itemSet;
	ItemMapper itemMapper;
		
	@Before
	public void setUp() throws Exception {
		recomMapper = new RecomMapper();
		
		itemMapper = new ItemMapper();
		//tableData = new TableData<Recom>(recomSet.getData(), recomMapper);
		//TablePage tp = tableData.getPage(startIndex, endIndex);
				
	}

	@After
	public void tearDown() throws Exception {
	
	}

	//@Test
	public void testSubmit() {
		//fail("Not yet implemented");
			
		recomSet = new RecomSet(800);
		submitTableData = new SubmitTableDataCommand<Recom>(recomSet.getData(), recomMapper,-1,null);
		datasetId = submitTableData.execute();
		System.out.println("Dataset ID : " + datasetId);
	}

	/*//@Test
	public void testGetPage() {
		
		recomSet = new RecomSet(800);
		submitTableData = new SubmitTableDataCommand<Recom>(recomSet.getData(), recomMapper);
		datasetId = submitTableData.execute();

		int startIndex = 795;
		int endIndex = 800;
		
		getTablePage = new GetTablePageCommand<Recom>(datasetId, startIndex, endIndex);
		TablePage tp = getTablePage.execute();

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getRows());			
		
		//System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
			
	}
	
	//@Test
	public void testGetSubsequentPage() {
		int startIndex = 100;
		int endIndex = 200;
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp1 = getTablePage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());
		
		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
			
	}
	
	//@Test
	public void testAddRecords() {
		
		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();
		
		addedRowList.add(new AddedRow(100, 1, new String[]{"A","DAL","PHX","LANKT131","1","0002","91","USD","1226.00"}));
		addedRowList.add(new AddedRow(100, 2, new String[]{"A","DAL","PHX","LANKT131","1","0002","92","USD","1226.00"}));
		addedRowList.add(new AddedRow(100, 3, new String[]{"A","DAL","PHX","LANKT131","1","0002","93","USD","1226.00"}));
		
		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);
		
		int startIndex = 790;
		int endIndex = 803;
		
		changedPage = new GetTablePageApplyChangesCommand<Recom>(0, startIndex, endIndex, changes, 700);
		TablePage tp1 = changedPage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());
		
		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
		startIndex = 800;
		endIndex = tp1.getTableSize();
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = getTablePage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());
		
		System.out.println("Total Table Size : " + tp2.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
	}
	
	//@Test
	public void testDeleteRecords() {
		
		List<AddedRow> addedRowList = new ArrayList<AddedRow>();
		List<DeletedRow> deletedRowList = new ArrayList<DeletedRow>();
		List<UpdatedRow> updatedRowList = new ArrayList<UpdatedRow>();
		
		deletedRowList.add(new DeletedRow(1));
		deletedRowList.add(new DeletedRow(9));
		
		Changes changes = new Changes(addedRowList, deletedRowList, updatedRowList);
		
		int startIndex = 790;
		int endIndex = 798;
		
		changedPage = new GetTablePageApplyChangesCommand<Recom>(0, startIndex, endIndex, changes, 790);
		TablePage tp1 = changedPage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());
		
		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
		startIndex = 795;
		endIndex = tp1.getTableSize();
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = getTablePage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());
		
		System.out.println("Total Table Size : " + tp2.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
	}
	
	//@Test
	public void testUpdateRecords() {
		
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
		TablePage tp1 = changedPage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());
		
		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
		startIndex = 795;
		endIndex = tp1.getTableSize();
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		TablePage tp2 = getTablePage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());
		
		System.out.println("Total Table Size : " + tp2.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
	}
	
	//@Test
	public void testModifyRecords() {
		
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
		TablePage tp1 = changedPage.execute();
		
		System.out.println("Total Table Size : " + tp1.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp1.getRows());
		
		getTablePage = new GetTablePageCommand<Recom>(0, 799, 801);
		TablePage tp2 = getTablePage.execute();
		
		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp2.getRows());
	}
	
	@Test
	public void testDataset() {

		recomSet = new RecomSet(100);
		submitTableData = new SubmitTableDataCommand<Recom>(recomSet.getData(), recomMapper);
		datasetId = submitTableData.execute();

		int startIndex = 0;
		int endIndex = 10;

		getTablePage = new GetTablePageCommand<Recom>(datasetId, startIndex, endIndex);
		TablePage tp = getTablePage.execute();

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getRows());			

		System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));

		
		SubmitTableDataCommand<Item> submitTableDataForItem;
		GetTablePageCommand<Item> getTablePageForItem;
		
		itemSet = new ItemSet(100);
		submitTableDataForItem = new SubmitTableDataCommand<Item>(itemSet.getData(), itemMapper);
		datasetId = submitTableDataForItem.execute();

		startIndex = 0;
		endIndex = 10;

		getTablePageForItem = new GetTablePageCommand<Item>(datasetId, startIndex, endIndex);
		TablePage tpForItem = getTablePageForItem.execute();

		printColumnHeaderNames(itemMapper);
		printStringArrayList(tpForItem.getRows());			

		System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tpForItem.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));

		startIndex = 0;
		endIndex = 10;
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex);
		tp = getTablePage.execute();

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getRows());			

		System.out.println("Dataset ID : " + datasetId);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex));
	}*/
		
	@SuppressWarnings("unused")
	private void printColumnHeaderNames(TableDataMapper<?, ?> mapper) {
		System.out.println("----------------------------------------------------------------------------------------");
		for(int i = 0; i < mapper.getColumnCount(); i++) {
			TableColumnConfig<?> columnConfigs = mapper.getColumnConfig(i);
			System.out.print("  " + columnConfigs.getName() + "\t" + "|" );
		}
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------");
	}

	@SuppressWarnings("unused")
	private void printStringArrayList(List<String[]> list){
		
		for(int i = 0 ; i < list.size(); i++) {
			String[] row = list.get(i);
			for(int j = 0; j < row.length; j++) {
				System.out.print(" " + row[j] + "\t" + "|" );
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------------");
	}
		
}
