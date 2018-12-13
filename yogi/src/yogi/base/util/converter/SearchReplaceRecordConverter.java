package yogi.base.util.converter;

public class SearchReplaceRecordConverter implements RecordConverter {

	private CharSequence target;
	private CharSequence replacement;

	public SearchReplaceRecordConverter(CharSequence target, CharSequence replacement) {
		super();
		this.target = target;
		this.replacement = replacement;
	}

	public String convert(String record) {
		return record.replace(target, replacement);
	}

}
