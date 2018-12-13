package yogi.property.io;

import yogi.base.Creator;
import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.Scanner;
import yogi.base.validation.Validator;
import yogi.property.Property;
import yogi.property.PropertyManager;
import yogi.property.PropertyValidator;

public class PropertyRecordProcessor extends DefaultRecordProcessor<Property, String>{
	public PropertyRecordProcessor() {
		this(new PropertyValidator(), new PropertyScanner());
	}


	public PropertyRecordProcessor(Validator<Property> propertyValidator, Scanner<Property, String> propertyScanner) {
		super(propertyValidator, propertyScanner, null, new PropertyRecordValidator());
	}


	@Override
	protected Property create(Creator<Property> creator) {
		Property property = creator.create();
		if(!getValidator().validate(property)) return null;
		PropertyManager.assignProperty(property);
		return property;
	}

}
