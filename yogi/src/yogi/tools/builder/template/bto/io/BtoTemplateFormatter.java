package yogi.tools.builder.template.bto.io;

import yogi.base.io.Formatter;
import yogi.tools.builder.template.bto.BtoTemplate;

public class BtoTemplateFormatter implements Formatter<BtoTemplate> {
	public String format(BtoTemplate btoTemplate) {
		return btoTemplate.toString();
	}
}
