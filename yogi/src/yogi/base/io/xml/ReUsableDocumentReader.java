package yogi.base.io.xml;

import yogi.base.io.resource.SystemResource;

public class ReUsableDocumentReader extends DocumentReader {
	private String document;
	public ReUsableDocumentReader(SystemResource resource) {
		super(resource);
	}
	
	@Override
	public String read() {
		if(document == null)
		{
			synchronized(this)
			{
				if(document == null)
				{
					document = super.read();
				}
			}
		}
		return document;
	}

}
