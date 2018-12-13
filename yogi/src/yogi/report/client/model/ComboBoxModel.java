package yogi.report.client.model;


public class ComboBoxModel<T> extends ListModel<T> implements javax.swing.ComboBoxModel {
	
	private static final long serialVersionUID = 5066344356801752821L;
	private Object selectedItem;
	
	@Override
	public void setSelectedItem(Object anItem) {
		selectedItem = anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	
}
