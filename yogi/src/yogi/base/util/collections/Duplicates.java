package yogi.base.util.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Duplicates {
	
	public static <T> List<List<IndexItem<T>>> checkDuplicates(List<T> items, final Comparator<T> comparator){
		List<List<IndexItem<T>>> rtnValue = new ArrayList<List<IndexItem<T>>>();
		List<IndexItem<T>> indexItems = new ArrayList<IndexItem<T>>(items.size());
		for(int i = 0; i < items.size(); i ++){
			indexItems.add(new IndexItem<T>(items.get(i), i));
		}
		IndexItemComparator<T> indexComparator = new IndexItemComparator<T>(comparator);
		Collections.sort(indexItems, indexComparator);
		if(indexComparator.isDuplicatesFound()){
			int i = 0;
			List<IndexItem<T>> duplicates = null;
			while(i < items.size()-1){
				while( i<items.size()-1 && indexComparator.compare(indexItems.get(i), indexItems.get(i+1)) == 0){
					if(duplicates == null){
						duplicates = new ArrayList<IndexItem<T>>();
						duplicates.add(indexItems.get(i));
					}
					duplicates.add(indexItems.get(i+1));
					i++;
				}
				if(duplicates != null){
					rtnValue.add(duplicates);
					duplicates = null;
				}
				i++;
			}		
		}
		return rtnValue;
	}

	static class IndexItemComparator<T> implements Comparator<IndexItem<T>>{
		private Comparator<T> comparator;
		private boolean duplicatesFound = false;
		public IndexItemComparator(Comparator<T> comparator) {
			super();
			this.comparator = comparator;
		}

		@Override
		public int compare(IndexItem<T> object1, IndexItem<T> object2) {
			int compare = comparator.compare(object1.getItem(), object2.getItem());
			if(compare == 0) duplicatesFound = true;
			return compare;
		}

		public boolean isDuplicatesFound() {
			return duplicatesFound;
		}
		
	}
}
