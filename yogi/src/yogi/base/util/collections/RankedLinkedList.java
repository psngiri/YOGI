package yogi.base.util.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankedLinkedList<E> {

	private Map<E, RankedLink<E>> map = new HashMap<E, RankedLink<E>>();
	private RankedLink<E> current;
	
	public RankedLinkedList() {
		super();
	}
	
	public RankedLink<E> get(E data){
		RankedLink<E> rtnValue = map.get(data);
		if(rtnValue != null && rtnValue.rank != 0){ return rtnValue;
		
		}
		float rank = 1;
		if(current != null) rank = current.getRank()+1;		
		rtnValue = new RankedLink<E>(data, rank, null, current);
		if(current != null){
			current.next = rtnValue;
			current = rtnValue;
		}else{
			current = rtnValue;
		}
		map.put(data, rtnValue);
		return rtnValue;
	}
	
	public List<E> getItems()
	{
		List<E> rtnValue = new ArrayList<E>();
		RankedLink<E> link = getFirst();
		rtnValue.add(link.getData());
		while(link.next != null)
		{
			link = link.next;
			rtnValue.add(link.getData());
		}
		return rtnValue;
	}
	
	public void clear(){
		current = null;
		for(RankedLink<E> link: map.values()){
			link.rank = 0;
			link.next = null;
			link.previous = null;
		}
	}
	
	public RankedLink<E> getFirst()
	{
		if(current == null) return null;
		RankedLink<E> rtnValue = current;
		while(rtnValue.previous != null){
			rtnValue = rtnValue.previous;
		}
		return rtnValue;
	}
	
	public RankedLink<E> getLast()
	{
		if(current == null) return null;
		RankedLink<E> rtnValue = current;
		while(rtnValue.next != null){
			rtnValue = rtnValue.next;
		}
		return rtnValue;
	}
	
	public void addBefore(RankedLink<E> link, RankedLink<E> newLink)
	{
		float previousRank = 0;
		if(link.previous != null) previousRank =link.previous.rank;
		newLink.rank = (link.rank+ previousRank)/2;
		if(newLink.previous != null) newLink.previous.next = newLink.next;
		if(link.previous != null) link.previous.next = newLink;
		if(newLink.next != null) newLink.next.previous = newLink.previous;
		newLink.next = link;
		newLink.previous = link.previous;
		link.previous = newLink;
	}
	
	public void addAfter(RankedLink<E> link, RankedLink<E> newLink)
	{
		float nextRank = link.rank + 1;
		if(link.next != null) nextRank =link.next.rank;
		newLink.rank = (link.rank+ nextRank)/2;
		if(newLink.next != null) newLink.next.previous = newLink.previous;
		if(link.next != null) link.next.previous = newLink;
		if(newLink.previous != null) newLink.previous.next = newLink.next;
		newLink.previous = link;
		newLink.next = link.next;
		link.next = newLink;
		
	}
	
	public static class RankedLink<E>
	{
		private E data;
		private float rank;
		private RankedLink<E> next;
		private RankedLink<E> previous;
		public RankedLink(E data, float rank, RankedLink<E> next,
				RankedLink<E> previous) {
			super();
			this.data = data;
			this.rank = rank;
			this.next = next;
			this.previous = previous;
		}
		public E getData() {
			return data;
		}
		public float getRank() {
			return rank;
		}
		public RankedLink<E> getNext() {
			return next;
		}
		public RankedLink<E> getPrevious() {
			return previous;
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}
}
