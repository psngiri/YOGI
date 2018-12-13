package yogi.paging.test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import yogi.base.app.testing.TestErrorReporter;
import yogi.paging.EditConfig;
import yogi.paging.EditCriteria;
import yogi.paging.TableDataMapper;
import yogi.paging.TablePage;
import yogi.paging.TableRecord;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.command.EditTableDataCommand;
import yogi.paging.command.GetTablePageCommand;
import yogi.paging.command.UndoTablePageCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;

public class EditTableDataCommandTestServer extends TestCase{

	public EditTableDataCommandTestServer() throws CommandException {
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
	
	public void testGetPage() throws CommandException {

		int startIndex = 0;
		int endIndex = 15;

		TablePage tp; 
		RecomMapper recomMapper = new RecomMapper();
		
		GetTablePageCommand<Recom> getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());			

		System.out.println("Dataset ID : " + 0);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));
		
		EditConfig fc1 = new EditConfig(1, "XYZ");
		EditConfig fc2 = new EditConfig(2, "PQR");
		EditConfig fc8 = new EditConfig(8, "P90%&+20");
		EditConfig fc19 = new EditConfig(19, "99");
				
		List<EditConfig> editData = new ArrayList<EditConfig>();
		editData.add(fc1);
		editData.add(fc2);
		editData.add(fc8);
		editData.add(fc19);
		
		List<Integer> rows = new ArrayList<Integer>();
		rows.add(2);
		rows.add(4);
		//rows.add(8);
				
		EditCriteria editCriteria = new EditCriteria(rows, editData, false);
		EditTableDataCommand<Recom> editTableDataCommand = new EditTableDataCommand<Recom>(0, null, editCriteria,"AA123456");
		System.out.println("EDIT command being executed........");
		CommandExecutor.get().execute(editTableDataCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());
		
		UndoTablePageCommand<Recom> undoTableDataCommand = new UndoTablePageCommand<Recom>(0, null,"AA123456");
		System.out.println("UNDO command being executed........");
		CommandExecutor.get().execute(undoTableDataCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

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
