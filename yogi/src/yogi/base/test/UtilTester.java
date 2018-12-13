package yogi.base.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Selector;
import yogi.base.Util;

public class UtilTester extends TestCase {
	public void testCheckAndCreateFileAlongWithParentDirsAsRequired() {
		// TODO: how come I'm not testing anything?
	}

	public void testCreateFile() {
		assertNotNull(Util.getTestDataDirectoryPath());
	}
	
	public void testPurge(){
		Selector<Integer> selector = new Selector<Integer>(){

			@Override
			public boolean select(Integer item) {
				return (item/2)*2==item;
			}
			
		};
		List<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++)
		{
			items.add(i);
		}
		System.out.println(items);
		Util.purge(items, selector);
		System.out.println(items);
		assertEquals(5, items.size());
		for(int i = 0; i < items.size(); i ++)
		{
			Integer j = i*2+1;
			assertEquals(j, items.get(i));
		}
	}
	
	public void testPad(){
		
		//String format
		
		
		assertEquals("test ", String.format("%-5s", "test"));
		assertEquals("test ",  Util.pad("test",5,false,' ').toString());
		assertEquals(" test", String.format("%5s", "test"));
		assertEquals(" test",  Util.pad("test",5,true,' ').toString());
		assertEquals("testtest", String.format("%-5s", "testtest"));
		assertEquals("testt",  Util.pad("testtest",5,false,' ').toString());
		
		//integer format
		assertEquals("00432", String.format("%05d", 432));
		assertEquals("00432",Util.pad("432",5,true,'0').toString());
		assertEquals("  432", String.format("%5d", 432));
		assertEquals("  432",Util.pad("432",5,true,' ').toString());
	
		//float formatter
		assertEquals("0432.50", String.format("%07.2f", 432.5f));
		assertEquals("0432.50", Util.pad(432.5f,2,7,true,'0').toString());
		assertEquals("0000433", String.format("%07.0f", 432.6f));
		assertEquals("0000433", Util.pad(432.6f,0,7,true,'0').toString());
		assertEquals("0000433", String.format("%07.0f", 432.8f));
		assertEquals("0000433", Util.pad(432.8f,0,7,true,'0').toString());
		assertEquals("020620620.00", String.format("%012.2f", 2.062062E7));
		assertEquals("020620620.00",  Util.pad(2.062062E7,2,12,true,'0').toString());
		 
		assertEquals("00000002.06", String.format("%011.2f", 2.059999f));
		assertEquals("00000002.06", Util.pad(2.059999f,2,11,true,'0').toString());
		assertEquals("00000432.50", String.format("%011.2f", 432.5f));
		assertEquals("00000432.50", Util.pad(432.5f,2,11,true,'0').toString());
		assertEquals("00000002.06", String.format("%011.2f", 2.059999f));
		assertEquals("00000002.06", Util.pad(2.059999f,2,11,true,'0').toString());
		assertEquals("432.57", String.format("%.2f", 432.567f));
		assertEquals("432.57", Util.pad(432.567f,2,0,true,'0').toString());
	
		
		
		assertEquals("432.50 ", String.format("%-7.2f", 432.5f));
		//assertEquals("432.5 0", Util.pad(432.5f,2,7,false,' ').toString());
	
		
		assertEquals("UA ", Util.pad("UA",3,false,' ').toString());
		assertEquals("UAS", Util.pad("UASA",3,false,' ').toString());
		assertEquals("005",Util.pad("5",3,true,'0').toString());
		assertEquals("500",Util.pad("5",3,false,'0').toString());
		assertEquals("567", Util.pad("5678",3,false,' ').toString());
		assertEquals("0000224.30", Util.pad(224.3f,2,10,true,'0').toString());
		assertEquals("    224.30", Util.pad(224.3f,2,10,true,' ').toString());
		
		assertEquals("  760.00", Util.pad(760.0,2,8,true,' ').toString());
		assertEquals("760.00  ", Util.pad(760.0,2,8,false,' ').toString());
	
		System.out.println("result: "+Util.pad(760.0,2,8,false,' '));
		System.out.println("result: "+Util.pad(760.0,2,8,true,' '));
		assertEquals("100.00",Util.pad(100f,2,0,true,'0').toString());
	}
	
	public void testGetSubLists()
	{
		List<Integer> objects = new ArrayList<Integer>();
		List<List<Integer>> subLists = Util.getSubLists(objects);
		assertEquals(2, subLists.size());
		assertEquals(0, subLists.get(0).size());
		assertEquals(0, subLists.get(1).size());
		objects.add(1);
		subLists = Util.getSubLists(objects);
		assertEquals(2, subLists.size());
		assertEquals(0, subLists.get(0).size());
		assertEquals(1, subLists.get(1).size());	
		objects.add(2);
		objects.add(3);
		objects.add(4);
		objects.add(5);
		subLists = Util.getSubLists(objects);
		assertEquals(2, subLists.size());
		assertEquals(2, subLists.get(0).size());
		assertEquals(3, subLists.get(1).size());	
	
	}
}
