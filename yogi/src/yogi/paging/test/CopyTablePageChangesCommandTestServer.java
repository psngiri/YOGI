package yogi.paging.test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import yogi.base.app.testing.TestErrorReporter;
import yogi.paging.ChangeType;
import yogi.paging.TableDataMapper;
import yogi.paging.TablePage;
import yogi.paging.TableRecord;
import yogi.paging.changes.Changes;
import yogi.paging.changes.Change;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.command.ApplyTablePageChangesCommand;
import yogi.paging.command.GetTablePageCommand;
import yogi.paging.command.RedoTablePageCommand;
import yogi.paging.command.UndoTablePageCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;

public class CopyTablePageChangesCommandTestServer extends TestCase{

	public CopyTablePageChangesCommandTestServer() throws CommandException {
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
		
		List<Change> changesHistory = new ArrayList<Change>();
		//ChangesHistory changesHistory1 = new ChangesHistory("MOD", 1, 1, "ACT", "DEF", false, false);
		//ChangesHistory changesHistory2 = new ChangesHistory("MOD", 1, 2, "BDL", "IJK", false, false);
		String[] row1 = new String[]{"R", "ACT", "AEX", "F", "1", "52", "", "USD", "1000.00", "1379.00", "-379.00", "164.63", "AA", "100.00", "UA", "0.00", "F", "", "ACTAEX", "", "",};
		Change changesHistory1 = new Change(ChangeType.ADD, 2, -1, "", "", row1, false, false);
		String[] row2 = new String[]{"I", "ACT", "BDL", "F", "1", "55", "", "USD", "2285.00", "2218.00", "67.00", "176.73", "AA", "100.00", "UA", "0.00", "F", "", "ACTBDL", "", "",};
		Change changesHistory2 = new Change(ChangeType.ADD, 12, -1, "", "", row2, false, false);
		changesHistory.add(changesHistory1);
		changesHistory.add(changesHistory2);
		Changes changes = new Changes(changesHistory);
		
		ApplyTablePageChangesCommand<Recom> applyTablePageChangesCommand = new ApplyTablePageChangesCommand<Recom>(0, changes,"AA123456");
		CommandExecutor.get().execute(applyTablePageChangesCommand);
		
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

		System.out.println("UNDO command being executed........");
		CommandExecutor.get().execute(undoTableDataCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());
		
		RedoTablePageCommand<Recom> redoTableDataCommand = new RedoTablePageCommand<Recom>(0, null,"AA123456");
		System.out.println("REDO command being executed........");
		CommandExecutor.get().execute(redoTableDataCommand);
		
		getTablePage = new GetTablePageCommand<Recom>(0, startIndex, endIndex,"AA123456");
		tp = (TablePage) CommandExecutor.get().execute(getTablePage);

		printColumnHeaderNames(recomMapper);
		printStringArrayList(tp.getData());
		
		System.out.println("REDO command being executed........");
		CommandExecutor.get().execute(redoTableDataCommand);
		
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
