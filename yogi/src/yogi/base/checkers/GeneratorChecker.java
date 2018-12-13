package yogi.base.checkers;

import yogi.base.app.Checker;
import yogi.base.app.Generator;

public class GeneratorChecker implements Checker {

	private Generator generator;
			
	public GeneratorChecker(Generator generator) {
		super();		
		this.generator = generator;
	}
	
	public boolean check() {
		return generator.isGenerated();
	}

}
