package yogi.paging.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecomSet {
	
	private int size;
	List<Recom> data = new ArrayList<Recom>();
		
	public RecomSet() {
		super();
	}

	public RecomSet(int size) {
		this.size = size;
		data = new ArrayList<Recom>(size); 
	}

	public void setUp() {
		for(int i = 0; i < size; i++) {
			data.add(new Recom("N", "ABQ", "PBI", "LANKT131", 1, "0002", i + "", "USD", (float)1226.00));
		}		
	}
	
	public void setUpData(String filePath) {
		FileInputStream fstream;
		DataInputStream in;
		BufferedReader br;		
		try{
			fstream = new FileInputStream(filePath);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			String splits[] ;
			
			while ((strLine = br.readLine()) != null)   {
				
				System.out.println(strLine);
				splits = strLine.split(",", 21);				
				Recom recom = null;
				
				for(int j = 0; j < splits.length; j++) {
					recom = new Recom(splits[0], splits[1], splits[2], splits[3], splits[4], splits[5], splits[6], splits[7], splits[8], splits[9], splits[10], splits[11], splits[12], splits[13], splits[14], splits[15], splits[16], splits[17], splits[18], splits[19], splits[20]);
				}
				data.add(recom);
			}			
			this.size = data.size();
			br.close();
			in.close();
			fstream.close();
			
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();		
		}
	}
	
	public List<Recom> getData() {
		setUp();
		return data;
	}
	
	public List<Recom> getRealData() {
		setUpData("configuration/sample.txt");
		return data;
	}
	
	public List<Recom> getLimitedData() {
		setUpData("configuration/sample-limited.txt");
		return data;
	}
	
	public int getSize() {
		return size;
	}	

	public List<Recom> getDataForSort(int size){
		this.size = size;
		data = new ArrayList<Recom>(size);
		/*data.add(new Recom("N", "ABQ", "ATL", "LANKT130", 21, "0002", 5, "USD", 1234.00));
		data.add(new Recom("N", "DFW", "DEN", "ALNKT121", 22,  "0002", 2, "USD", 2226.00));
		data.add(new Recom("N", "ATL", "PBI", "ABCDE132", 67,  "0002", 3, "USD", 1000.00));
		data.add(new Recom("N", "DEN", "ATL", "PQRST198", 16, "0002", 4, "USD", 1226.00));
		data.add(new Recom("N", "ABQ", "DAL", "LANKT131", 91,  "0002", 1, "USD", 1200.00));
		data.add(new Recom("N", "DFW", "PBI", "NLAKT111", 73,  "0002", 6, "USD", 2224.00));
		data.add(new Recom("N", "DAL", "DEN", "NKLAT131", 12,  "0002", 7, "USD", 1226.00));
		data.add(new Recom("N", "DAL", "ATL", "LANKT187", 11, "0002", 3, "USD", 1500.00));
		data.add(new Recom("N", "DFW", "DAL", "PQRST131", 32,  "0002", 5, "USD", 1226.00));
		data.add(new Recom("N", "AUS", "DEN", "PQRST122", 46,  "0002", 3, "USD", 1100.00));
		data.add(new Recom("N", "ABQ", "ATL", "LANKT131", 14, "0002", 9, "USD", 1226.00));
		data.add(new Recom("N", "IAH", "DEN", "LANKT141", 35, "0002", 1, "USD", 1600.00));
		data.add(new Recom("N", "AUS", "DEN", "EFGHI145", 23, "0002", 5, "USD", 1227.00));
		data.add(new Recom("N", "SFO", "ATL", "PQRST122", 78, "0002", 9, "USD", 1800.00));
		data.add(new Recom("N", "DFW", "DAL", "QWERT131", 13, "0002", 8, "USD", 1400.00));
		data.add(new Recom("N", "IAH", "PBI", "ABCDE165", 51, "0002", 7, "USD", 1234.00));*/
		return data;
	}	
}
