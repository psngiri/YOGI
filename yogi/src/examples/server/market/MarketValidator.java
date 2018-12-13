package examples.server.market;

import yogi.base.validation.ValidatorAdapter;

public class MarketValidator extends ValidatorAdapter<Market> {
	@Override
	public boolean validate(Market market) {
		return true;
	}
}
