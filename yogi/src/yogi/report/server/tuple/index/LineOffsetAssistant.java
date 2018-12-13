package yogi.report.server.tuple.index;

import java.util.List;

import yogi.base.io.resource.FileResource;
import yogi.base.util.store.Element;
import yogi.base.util.store.ElementMapStore;

public class LineOffsetAssistant {
	private ElementMapStore<FileResource, List<Long>> indexes = new ElementMapStore<FileResource, List<Long>>();
	
	public LineOffsetAssistant() {
		super();
	}

	public Long getValue(FileResource fileResource, int key){
		Element<FileResource, List<Long>> index = indexes.get(fileResource);
		if(index == null){
			synchronized (fileResource) {
				index = indexes.get(fileResource);
				if(index == null){
					LineOffsetReader lineOffsetReader = new LineOffsetReader(fileResource);
					List<Long> read = lineOffsetReader.read();
					index = indexes.add(fileResource, read);
				}
			}
		}
		return index.getValue().get(key);
	}
}
