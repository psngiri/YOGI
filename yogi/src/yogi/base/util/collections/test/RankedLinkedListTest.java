package yogi.base.util.collections.test;

import junit.framework.TestCase;

import yogi.base.util.collections.RankedLinkedList;
import yogi.base.util.collections.RankedLinkedList.RankedLink;

public class RankedLinkedListTest  extends TestCase {

	public void test(){
		RankedLinkedList<Character> rllist = new RankedLinkedList<Character>();
		RankedLink<Character> a = rllist.get('A');
		RankedLink<Character> a1 = rllist.get('A');
		assertEquals(a, a1);
		assertEquals(1f, a.getRank());
		assertEquals(null, a.getNext());
		assertEquals(null, a.getPrevious());
		RankedLink<Character> b = rllist.get('B');
		RankedLink<Character> b1 = rllist.get('B');
		assertEquals(b, b1);
		assertEquals(2f, b.getRank());
		assertEquals(null, b.getNext());
		assertEquals(a, b.getPrevious());
		RankedLink<Character> c = rllist.get('C');
		RankedLink<Character> c1 = rllist.get('C');
		assertEquals(c, c1);
		assertEquals(3f, c.getRank());
		assertEquals(null, c.getNext());
		assertEquals(b, c.getPrevious());
		assertEquals(c, b.getNext());
		assertEquals(a, rllist.getFirst());
		assertEquals(c, rllist.getLast());
		RankedLink<Character> d = rllist.get('D');
		RankedLink<Character> d1 = rllist.get('D');
		assertEquals(d, d1);
		assertEquals(4f, d.getRank());
		assertEquals(null, d.getNext());
		assertEquals(c, d.getPrevious());
		assertEquals(d, c.getNext());
		assertEquals(a, rllist.getFirst());
		assertEquals(d, rllist.getLast());
		assertEquals("[A, B, C, D]", rllist.getItems().toString());
		
		rllist.clear();
		assertEquals(null, rllist.getFirst());
		assertEquals(null, rllist.getLast());
		a = rllist.get('A');
		a1 = rllist.get('A');
		assertEquals(a, a1);
		assertEquals(1f, a.getRank());
		assertEquals(null, a.getNext());
		assertEquals(null, a.getPrevious());
		b = rllist.get('B');
		b1 = rllist.get('B');
		assertEquals(b, b1);
		assertEquals(2f, b.getRank());
		assertEquals(null, b.getNext());
		assertEquals(a, b.getPrevious());
		c = rllist.get('C');
		c1 = rllist.get('C');
		assertEquals(c, c1);
		assertEquals(3f, c.getRank());
		assertEquals(null, c.getNext());
		assertEquals(b, c.getPrevious());
		assertEquals(c, b.getNext());		
		assertEquals(a, rllist.getFirst());
		assertEquals(c, rllist.getLast());
		assertEquals(a, rllist.getFirst());
		assertEquals(c, rllist.getLast());
		d = rllist.get('D');
		d1 = rllist.get('D');
		assertEquals(d, d1);
		assertEquals(4f, d.getRank());
		assertEquals(null, d.getNext());
		assertEquals(c, d.getPrevious());
		assertEquals(d, c.getNext());
		assertEquals(a, rllist.getFirst());
		assertEquals(d, rllist.getLast());		
		assertEquals("[A, B, C, D]", rllist.getItems().toString());

		rllist.addBefore(a, c);
		assertEquals(c, rllist.getFirst());
		assertEquals(d, rllist.getLast());
		assertEquals(null, c.getPrevious());
		assertEquals(a, c.getNext());
		assertEquals(c, a.getPrevious());
		assertEquals(b, a.getNext());
		assertEquals(a, b.getPrevious());
		assertEquals(d, b.getNext());
		assertEquals(b, d.getPrevious());
		assertEquals(null, d.getNext());
		assertEquals("[C, A, B, D]", rllist.getItems().toString());
		
		rllist.addAfter(a, c);
		assertEquals(a, rllist.getFirst());
		assertEquals(d, rllist.getLast());
		assertEquals(null, a.getPrevious());
		assertEquals(c, a.getNext());
		assertEquals(a, c.getPrevious());
		assertEquals(b, c.getNext());
		assertEquals(c, b.getPrevious());
		assertEquals(d, b.getNext());
		assertEquals(b, d.getPrevious());
		assertEquals(null, d.getNext());
		assertEquals("[A, C, B, D]", rllist.getItems().toString());
		
		rllist.addBefore(c, b);
		assertEquals(a, rllist.getFirst());
		assertEquals(d, rllist.getLast());
		assertEquals(null, a.getPrevious());
		assertEquals(b, a.getNext());
		assertEquals(a, b.getPrevious());
		assertEquals(c, b.getNext());
		assertEquals(b, c.getPrevious());
		assertEquals(d, c.getNext());
		assertEquals(c, d.getPrevious());
		assertEquals(null, d.getNext());		
		assertEquals("[A, B, C, D]", rllist.getItems().toString());
		
		rllist.addAfter(c, b);
		assertEquals(a, rllist.getFirst());
		assertEquals(d, rllist.getLast());
		assertEquals(null, a.getPrevious());
		assertEquals(c, a.getNext());
		assertEquals(a, c.getPrevious());
		assertEquals(b, c.getNext());
		assertEquals(c, b.getPrevious());
		assertEquals(d, b.getNext());
		assertEquals(b, d.getPrevious());
		assertEquals(null, d.getNext());
		assertEquals("[A, C, B, D]", rllist.getItems().toString());
		
		rllist.addAfter(a, d);
		assertEquals(a, rllist.getFirst());
		assertEquals(b, rllist.getLast());
		assertEquals(null, a.getPrevious());
		assertEquals(d, a.getNext());
		assertEquals(a, d.getPrevious());
		assertEquals(c, d.getNext());
		assertEquals(d, c.getPrevious());
		assertEquals(b, c.getNext());
		assertEquals(c, b.getPrevious());
		assertEquals(null, b.getNext());
		assertEquals("[A, D, C, B]", rllist.getItems().toString());

		rllist.addBefore(a, b);
		assertEquals(b, rllist.getFirst());
		assertEquals(c, rllist.getLast());
		assertEquals(null, b.getPrevious());
		assertEquals(a, b.getNext());
		assertEquals(b, a.getPrevious());
		assertEquals(d, a.getNext());
		assertEquals(a, d.getPrevious());
		assertEquals(c, d.getNext());
		assertEquals(d, c.getPrevious());
		assertEquals(null, c.getNext());
		assertEquals("[B, A, D, C]", rllist.getItems().toString());
		
		rllist.addAfter(c, b);
		assertEquals(a, rllist.getFirst());
		assertEquals(b, rllist.getLast());
		assertEquals(null, a.getPrevious());
		assertEquals(d, a.getNext());
		assertEquals(a, d.getPrevious());
		assertEquals(c, d.getNext());
		assertEquals(d, c.getPrevious());
		assertEquals(b, c.getNext());
		assertEquals(c, b.getPrevious());
		assertEquals(null, b.getNext());
		assertEquals("[A, D, C, B]", rllist.getItems().toString());
		
		rllist.addBefore(b, a);
		assertEquals(d, rllist.getFirst());
		assertEquals(b, rllist.getLast());
		assertEquals(null, d.getPrevious());
		assertEquals(c, d.getNext());
		assertEquals(d, c.getPrevious());
		assertEquals(a, c.getNext());
		assertEquals(c, a.getPrevious());
		assertEquals(b, a.getNext());
		assertEquals(a, b.getPrevious());
		assertEquals(null, b.getNext());
		assertEquals("[D, C, A, B]", rllist.getItems().toString());
		
	}
}
