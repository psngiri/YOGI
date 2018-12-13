package yogi.report.server.tuple.io;

import java.util.ArrayList;
import java.util.Map;

import yogi.base.io.Reader;
import yogi.base.io.resource.FileResource;
import yogi.base.io.resource.ResourceAssistant;
import yogi.report.server.ColumnAndSelector;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;



public abstract class TupleRowReader implements Reader<TupleRow>{
	private FileResource fileResource;
	private String userId;
	private Map<String, Integer> columnIndexMap;
	private Map<String, TupleRowBaseEvaluator<?>> filterColumnEvaluatorsMap;
	private Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap;
	private ColumnAndSelector<TupleRow> columnSelector;
	protected ArrayList<FileResource> fileResources = new ArrayList<FileResource>();
	
	public TupleRowReader() {
		super();
	}
	
	protected Map<String, Integer> getColumnIndexMap() {
		return columnIndexMap;
	}

	protected Map<String, TupleRowBaseEvaluator<?>> getColumnEvaluatorsMap() {
		return columnEvaluatorsMap;
	}

	protected Map<String, TupleRowBaseEvaluator<?>> getFilterColumnEvaluatorsMap() {
		return filterColumnEvaluatorsMap;
	}

	protected ColumnAndSelector<TupleRow> getColumnSelector() {
		return columnSelector;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void set(Map<String, Integer> columnIndexMap, Map<String, TupleRowBaseEvaluator<?>> filterColumnEvaluatorsMap,
			Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap,
			ColumnAndSelector<TupleRow> columnSelector) {
		this.columnIndexMap = columnIndexMap;
		this.filterColumnEvaluatorsMap = filterColumnEvaluatorsMap;
		this.columnEvaluatorsMap = columnEvaluatorsMap;
		this.columnSelector = columnSelector;
	}
	
	public FileResource getFileResource() {
		return fileResource;
	}

	public void setFileResource(FileResource fileResource) {
		this.fileResource = fileResource;
	}
	
	protected void setFileResource(int index, FileResource fileResource) {
		if(fileResources.size() == index) this.fileResources.add(fileResource);
		else if(fileResources.size() > index) this.fileResources.set(index, fileResource);
		else throw new RuntimeException("use successive indexes while setting fileResources");
	}

	public FileResource getFileResource(int index) {
		return fileResources.get(index);
	}
	
	public void setFileName(String fileName){
		setFileResource(ResourceAssistant.getFileResource(fileName));
	}
	
	protected void setFileName(int index, String fileName){
		setFileResource(index, ResourceAssistant.getFileResource(fileName));
	}
	
	public void setDataSet(String dataSet)
	{
		setFileResource(ResourceAssistant.getFileResource(dataSet));
	}
}
