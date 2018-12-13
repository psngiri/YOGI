package yogi.base.util.immutable.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

import yogi.base.util.immutable.ImmutableList;

public class ImmutableListIteratorTest extends TestCase {

	public void testCheckConcurentModification1()
	{
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(0);
		ints.add(1);
		ImmutableList<Integer> iints = new ImmutableList<Integer>(ints);
		Iterator<Integer> iterator = iints.iterator();
		assertEquals(0, iterator.next().intValue());
		ints.add(2);
		try {
			assertEquals(1, iterator.next().intValue());
			assertEquals(2, iterator.next().intValue());
		} catch (RuntimeException e) {
			fail();
		}
	}
	public void testCheckConcurentModification2()
	{
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(0);
		ints.add(1);
		ImmutableList<Integer> iints = new ImmutableList<Integer>(ints);
		for(int i = 0; i < iints.size(); i ++)
		{
			assertEquals(i, ints.get(i).intValue());
			ints.add(i+2);
			if(i == 10) break;
		}
	}
	public void testCheckConcurentModification3()
	{
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(0);
		ints.add(1);
		ImmutableList<Integer> iints = new ImmutableList<Integer>(ints);
		try {
			int i = 0;
			for(Integer value: iints)
			{
				assertEquals(i, value.intValue());
				ints.add(2);
					assertEquals(i, value.intValue());
					i++;
			}
		} catch (RuntimeException e) {
			fail();
		}
	}
	
	public void testCheckConcurentModification4()
	{
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(0);
		ints.add(1);
		ints.add(2);
		ImmutableList<Integer> iints = new ImmutableList<Integer>(ints);
		ListIterator<Integer> iterator = iints.listIterator();
		assertFalse(iterator.hasPrevious());
		assertEquals(0, iterator.next().intValue());
		ints.add(3);
		try {
			assertTrue(iterator.hasNext());
			assertTrue(iterator.hasPrevious());
			assertEquals(1, iterator.nextIndex());
			assertEquals(0, iterator.previousIndex());
			assertEquals(1, iterator.next().intValue());
			assertTrue(iterator.hasNext());
			assertTrue(iterator.hasPrevious());
			assertEquals(2, iterator.nextIndex());
			assertEquals(1, iterator.previousIndex());
			assertEquals(2, iterator.next().intValue());
			assertFalse(iterator.hasNext());
			assertTrue(iterator.hasPrevious());
			assertEquals(3, iterator.nextIndex());
			assertEquals(2, iterator.previousIndex());
			assertEquals(2, iterator.previous().intValue());
			assertTrue(iterator.hasNext());
			assertTrue(iterator.hasPrevious());
			assertEquals(2, iterator.nextIndex());
			assertEquals(1, iterator.previousIndex());
			assertEquals(1, iterator.previous().intValue());
			assertTrue(iterator.hasNext());
			assertTrue(iterator.hasPrevious());
			assertEquals(1, iterator.nextIndex());
			assertEquals(0, iterator.previousIndex());
			assertEquals(0, iterator.previous().intValue());
			assertFalse(iterator.hasPrevious());
		} catch (RuntimeException e) {
			fail();
		}
		
	}
	
}
