package yogi.versioning.version;

import yogi.base.validation.ValidatorAdapter;

public class VersionValidator extends ValidatorAdapter<Version> {
	@Override
	public boolean validate(Version version) {
		return true;
	}
}
