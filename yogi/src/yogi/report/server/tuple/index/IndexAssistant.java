package yogi.report.server.tuple.index;

import java.util.Map;

import yogi.base.io.resource.FileResource;
import yogi.base.util.range.Range;
import yogi.base.util.store.Element;
import yogi.base.util.store.ElementMapStore;

public class IndexAssistant {
	private ElementMapStore<FileResource, Map<Long,Long>> indexes = new ElementMapStore<FileResource, Map<Long,Long>>();
	private int recordLength;
	private Range<Integer> range;
	
	public IndexAssistant(int recordLength, Range<Integer> range) {
		super();
		this.recordLength = recordLength;
		this.range = range;
	}

	public Long getValue(FileResource fileResource, Long key){
		Element<FileResource, Map<Long, Long>> index = indexes.get(fileResource);
		if(index == null){
			synchronized (fileResource) {
				index = indexes.get(fileResource);
				if(index == null){
					IndexReader indexReader = new IndexReader(recordLength, fileResource, range);
					Map<Long, Long> read = indexReader.read();
					index = indexes.add(fileResource, read);
				}
			}
		}
		return index.getValue().get(key);
	}
}
