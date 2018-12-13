package yogi.base.io.resource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MultiFileResourceImpl implements MultiFileResource {

	private Iterator<String> fileIterator;
	private List<String> fileNames;

	public MultiFileResourceImpl(String... fileNames) {
		this(Arrays.asList(fileNames));
	}

	public MultiFileResourceImpl(List<String> fileNames) {
		super();
		this.fileNames=fileNames;
		this.fileIterator = this.fileNames.iterator();
	}

	@Override
	public boolean hasNext() {
		return fileIterator.hasNext();
	}

	@Override
	public String getName() {
		return fileNames.toString();
	}

	@Override
	public FileResource next() {
		return new FileResource(fileIterator.next());
	}

}
