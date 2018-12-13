package yogi.base.io;

import yogi.base.io.resource.MultiFileResource;

public class MultiFileReader extends MultiResourceReader<String> {
	private int headerLineCount;

	public MultiFileReader(MultiFileResource multiFileResource) {
		super(multiFileResource);
	}

	public MultiFileReader(MultiFileResource multiFileResource, int headerLineCount) {
		super(multiFileResource);
		this.headerLineCount = headerLineCount;
	}

	@Override
	protected void resetCurrentReader() {
		setCurrentReader(new FileRecordReader(getResource().next(), headerLineCount));
	}

	@Override
	public MultiFileResource getResource() {
		return (MultiFileResource) super.getResource();
	}

}
