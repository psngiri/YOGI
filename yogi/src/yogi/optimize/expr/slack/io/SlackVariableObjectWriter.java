package yogi.optimize.expr.slack.io;

import java.util.Comparator;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.slack.SlackVariableObject;
import yogi.optimize.expr.slack.SlackVariableObjectFactory;

public class SlackVariableObjectWriter extends FactoryWriter<SlackVariableObject> {
	public static boolean RUN = true;
	public SlackVariableObjectWriter(String fileName, Selector<? super SlackVariableObject> selector) {
		super(SlackVariableObjectFactory.get(), new FileWriter<SlackVariableObject>(fileName, new SlackVariableObjectFormatter()),
				selector);
		this.setComparator(myComparator);
	}

	public SlackVariableObjectWriter(String fileName) {
		this(fileName, null);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	private static Comparator<SlackVariableObject> myComparator = new Comparator<SlackVariableObject>(){

		public int compare(SlackVariableObject slackVariableObject1, SlackVariableObject slackVariableObject2) {
			return VariableAssistant.get().getVariable(slackVariableObject1).getName().compareTo(VariableAssistant.get().getVariable(slackVariableObject2).getName());
		}};
}
