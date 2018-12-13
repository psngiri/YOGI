package yogi.report.server.row.evaluators;



public abstract class RowComplexEvaluator<V> extends RowBaseEvaluator<V> {

	private String complexName;

	public RowComplexEvaluator(String columnName, String complexName) {
		super(columnName);
		this.complexName = complexName;
	}

	@Override
	public String getComplexName() {
		return complexName;
	}

}
