package yogi.base.app.testing;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.Util;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.relationship.types.capacity.io.CapacityReader;
import yogi.base.relationship.types.capacity.io.CapacityWriter;
import yogi.base.relationship.types.io.RelationshipTypeWriter;
import yogi.base.util.FileToErrorReporter;
import yogi.base.util.LockManager;
import yogi.base.util.diff.FileCompare;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.optimize.app.OptimizeModule;
import yogi.property.alias.io.PropertyAliasReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public abstract class ApplicationAcceptenceTestCase {
	@Rule
	public final MethodRule globalTimeout = new Timeout(maxRunTimeInMillis());

	private final String workset;
	private final Map<String, Selector<String>> fromRecordSelectors;
	private final Map<String, Selector<String>> toRecordSelectors;
	private final Set<String> fileNamesToCompare = new HashSet<String>();

	protected ApplicationAcceptenceTestCase(String workset) {
		this.workset = workset;
		this.fromRecordSelectors = new HashMap<String, Selector<String>>();
		this.toRecordSelectors = new HashMap<String, Selector<String>>();
	}

	// 24 hours by default
	protected int maxRunTimeInMillis() {
		return 24 * 60 * 60 * 1000;
	}

	public String getWorkset() {
		return workset;
	}

	public Set<String> getFileNamesToCompare() {
		return fileNamesToCompare;
	}

	public void addFileNameToCompare(String fileName) {
		fileNamesToCompare.add(fileName);
	}

	public void clearFileNamesToCompare() {
		fileNamesToCompare.clear();
	}

	@Before
	public final void initializeApplicationAcceptance() {
		Factory.clearAllFactories();
		DumpProperties.clearFileIndexer();
		setProperties();
	}

	protected void setProperties() {
		PropertyAliasReader.RUN = true;
		CapacityReader.RUN = true;
		CapacityWriter.RUN = true;
		RelationshipTypeWriter.RUN = true;
		LoggingPropertiesFileReader.RUN = true;
		LockManager.Disable = true;
		OptimizeModule.IPSolutionFilePathFromMatrixLocation = "../output/IPSolution";
		DumpProperties.ReadFromDbDump = true;
		FileToErrorReporter.RUN = false;
	}

	@After
	public void tearDownApplicationAcceptance() {
		Factory.clearAllFactories();
	}

	@Test
	public void test() {
		assertNotNull("Workset is null", workset);
		runWorkset();
	}

	private void runWorkset() {
		String[] args = new String[2];
		args[0] = "data/" + workset;
		args[1] = "data/" + workset +"/testOutput";
		runAndCompare(args);
	}

	private void runAndCompare(String[] args) {
		int exitCode = executeApplication(args);
		if (exitCode < 0) {
			fail("Error Executing Application exitCode:" + exitCode);
			return;
		}

		HashMap<String, String> exceptions = new HashMap<String, String>();
		for (String fileName : fileNamesToCompare) {
			try {
				compareFile(args, fileName);
			} catch (Exception e) {
				exceptions.put(fileName, e.getMessage());
			}
		}
		if (!exceptions.isEmpty())
			fail("Errors by fileName: " + exceptions);
		else
			Util.deleteFile(new File(args[1]));
	}

	protected abstract int executeApplication(String[] args);

	private void compareFile(String[] args, String fileName) throws Exception {
		FileCompare fileCompare = new FileCompare(args[0] + "/output/" + fileName, args[1] + '/' + fileName);
		fileCompare.setFromRecordSelector(fromRecordSelectors.get(fileName));
		fileCompare.setToRecordSelector(toRecordSelectors.get(fileName));
		assertTrue(fileCompare.compare());
	}

	public void setFromRecordSelectorFor(String fileName, Selector<String> selector) {
		fromRecordSelectors.put(fileName, selector);
	}

	public void setToRecordSelectorFor(String fileName, Selector<String> selector) {
		toRecordSelectors.put(fileName, selector);
	}
}
