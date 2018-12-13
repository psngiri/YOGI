package yogi.paging.test;

//import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import yogi.base.app.testing.TestErrorReporter;
import yogi.paging.TableDataMapper;
import yogi.paging.TablePage;
import yogi.paging.TableRecord;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.command.DeleteAllTableDataCommand;
import yogi.paging.command.GetTablePageCommand;
import yogi.paging.command.UndoTablePageCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;

public class DeleteAllTableDataCommandTestServer extends TestCase{

	public DeleteAllTableDataCommandTestServer() throws CommandException {
		super();
		CommandExecutor.commandServerAddressesColonPortNumbers = "localhost:9998";
		//(TablePage) CommandExecutor.commandServerAddressesColonPortNumbers = "10.97.85.81:5088";
		//(TablePage) CommandExecutor.commandServerAddressesColonPortNumbers = "rmprit01.qcorpaa.aa.com:50002";

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
		
		GetTablePageCommand<Recom> getTablePage = new GetTablePageCommand<Recom>(0, 0, 20,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());

		System.out.println("Dataset ID : " + 0);
		System.out.println("Total Table Size : " + tp.getTableSize());
		System.out.println("# of records requested : " + (endIndex - startIndex + 1));
		
		getTablePage = new GetTablePageCommand<Recom>(0, 4980, 4999,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());
		System.out.println("Total Table Size : " + tp.getTableSize());
		
		DeleteAllTableDataCommand<Recom> deleteAllTableDataCommand = new DeleteAllTableDataCommand<Recom>(0, null,"AA123456");
		System.out.println("DELETEALL command being executed........");
		CommandExecutor.get().execute(deleteAllTableDataCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(0, 0, 0,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());
		System.out.println("Total Table Size : " + tp.getTableSize());
		
		UndoTablePageCommand<Recom> undoTableDataCommand = new UndoTablePageCommand<Recom>(0, null,"AA123456");
		System.out.println("UNDO command being executed........");
		CommandExecutor.get().execute(undoTableDataCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(0, 0, 20,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());

		getTablePage = new GetTablePageCommand<Recom>(0, 4980, 4999,"AA123456");
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
