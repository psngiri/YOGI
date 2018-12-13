package yogi.base.util.immutable.test;

import java.util.List;

import static java.util.Arrays.*;

import yogi.base.util.immutable.ImmutableList;
import junit.framework.TestCase;

public class ImmutableListEqualsHashTest extends TestCase {
	private List<String> regularList;
	private ImmutableList<String> items;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		regularList = asList("a", "b");
		items = new ImmutableList<String>(regularList);
	}

	public void testImmutableListShouldNeverEqualNull() {
		assertFalse(items.equals(null));
	}

	public void testImmutableListEqualsShouldBeReflexive() {
		assertEquals(items, items);
	}

	public void testImmutableListEqualsShouldBeSymmetric() {
		ImmutableList<String> otherItems = new ImmutableList<String>(items);

		assertEquals(items, otherItems);
		assertEquals(otherItems, items);
	}

	public void testImmutableListEqualsShouldReportNotEqualForNonEquivalentContentsSymmetrically() {
		ImmutableList<String> otherItems = new ImmutableList<String>(asList("a", "c"));

		assertFalse(items.equals(otherItems));
		assertFalse(otherItems.equals(items));
	}

	public void testAnImmutableListShouldEqualARegularListWithEquivalentContentsSymmetrically() {
		assertEquals(regularList, items);
		assertEquals(items, regularList);
	}

	public void testAnImmutableListShouldNotEqualARegularListWithNonEquivalentContentsSymmetrically() {
		List<String> otherRegularList = asList("a", "c");

		assertFalse(items.equals(otherRegularList));
		assertFalse(otherRegularList.equals(items));
	}

	public void testEqualImmutableListsAnswerSameHashCode() {
		ImmutableList<String> otherItems = new ImmutableList<String>(items);

		assertEquals(items.hashCode(), regularList.hashCode());
		assertEquals(items.hashCode(), otherItems.hashCode());
	}
}
