package yogi.tools.builder.template.bto.io.db;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;
import yogi.tools.builder.template.bto.BtoTemplate;
import yogi.tools.builder.template.bto.BtoTemplateFactory;




public class BtoTemplateDbWriter extends FactoryWriter<BtoTemplate> {

	public BtoTemplateDbWriter(DbResource resource, Selector<? super BtoTemplate> selector)
	{
		super(BtoTemplateFactory.get(), new DbWriter<BtoTemplate>(resource, new BtoTemplateDbFormatter()), selector);
	}
	
	public BtoTemplateDbWriter(DbResource resource)
	{
		this(resource, null);
	}
}
