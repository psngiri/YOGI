package yogi.server.gui.record.converter;

import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.db.DbResource;
import yogi.base.util.loader.DbLoader;

public class RecordConverter extends DbLoader {
	public RecordConverter(DbResource resource) {
		super(resource, resource, new ConverterDbFormatter());
	}

	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("TableName",ConverterDbFormatter.TableName);
		queryReader.addVariable("Types",ConverterDbFormatter.Types );
		return queryReader;
	}

}
