package examples.server.fare;

import yogi.base.Creator;
import yogi.server.action.Action;

import examples.server.fare.farekey.FareKey;
import examples.server.sub.Sub;

public class FareCreator implements Creator<Fare> {
	
	private int keyId;
	private FareKey key;
	private Sub version;
	private Action action;
	private int fareAmount;
	
	public Fare create() {
		return new FareImpl(key,version,action,fareAmount);
	}

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public Sub getVersion() {
		return version;
	}

	public void setVersion(Sub version) {
		this.version = version;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getFareAmount() {
		return fareAmount;
	}

	public void setFareAmount(int fareAmount) {
		this.fareAmount = fareAmount;
	}

	public FareKey getKey() {
		return key;
	}

	public void setKey(FareKey key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return keyId+"/"+version;
	}
	
}
