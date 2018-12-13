package yogi.base.io.link;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import yogi.base.app.ErrorReporter;

public abstract class LinkGeneratorImpl<F, T> implements LinkGenerator<F> {
	public static Level DefaultLevel = Level.INFO;
	
	private LinkComparator<F, T> linkComparator;

	protected boolean logging=true;
	
	
	public LinkGeneratorImpl(LinkComparator<F, T> linkComparator) {
		super();
		this.linkComparator = linkComparator;
	}
	
	public abstract Collection<T> getToObjects();
	
	@SuppressWarnings("unchecked")
	public void generateLinks(List<F> fromObjects) {
		if(fromObjects.isEmpty()) return;
		Collections.sort(fromObjects, new FromComparator());
		Collection<T> toObjects = getToObjects();
		T[] toArray = (T[]) toObjects.toArray();
		Arrays.sort(toArray, new ToComparator());
		Iterator<F> fromIterator = fromObjects.iterator();
		generateLinks(toArray, fromIterator);
	}
	
	private void generateLinks(T[] toArray, Iterator<F> fromIterator) {
		F from = fromIterator.next();
		for(T to: toArray)
		{
			int compareFromTo = linkComparator.compareFromTo(from, to);
			while(compareFromTo <= 0)
			{
				if(compareFromTo == 0) buildLink(from, to);
				if(compareFromTo < 0){
					if(logging && DefaultLevel == Level.INFO){ErrorReporter.get().info("Could not find " + to.getClass().getSimpleName()+ " for "+from.getClass().getSimpleName(), from);}
				}
				if(!fromIterator.hasNext()) return;
				from = fromIterator.next();
				compareFromTo = linkComparator.compareFromTo(from, to);
			}
		}
	}
	
	protected abstract void buildLink(F from, T to);

	private final class FromComparator implements Comparator<F> {
		public int compare(F o1, F o2) {
			return linkComparator.compareFrom(o1, o2);
		}
	}

	private final class ToComparator implements Comparator<T> {
		public int compare(T o1, T o2) {
			return linkComparator.compareTo(o1, o2);
		}
	}
	
}
