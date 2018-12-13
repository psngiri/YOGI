package yogi.report.client.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;

public class ListModel<T> extends AbstractListModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5303508004607883647L;
	private List<T> items= new ArrayList<T>();
	@Override
	public int getSize() {
		return items.size();
	}
	public void removeAll()
	{
		int index = items.size();
		items.clear();
		this.fireIntervalRemoved(this, 0, index);
	}
	public void remove(T item)
	{
		int index = items.size();
		if(items.contains(item))
			items.remove(item);
		this.fireIntervalRemoved(this, 0, index);
	}
	public void add(T item)
	{
		int index = items.size();
		items.add(item);
		fireContentsChanged(this, index, index);
	}
	public void addAll(Collection<T> items)
	{
		int index0 = items.size();
		this.items.addAll(items);
		this.fireContentsChanged(this, index0, index0+items.size());
	}
	
	public T getItemAt(int index)
	{
		return items.get(index);
	}
	
	@Override
	public Object getElementAt(int index) {
		return items.get(index);
	}
	
	public void clear()
	{
		int index = items.size();
		items.clear();
		this.fireContentsChanged(this, 0, index);
	}

}
