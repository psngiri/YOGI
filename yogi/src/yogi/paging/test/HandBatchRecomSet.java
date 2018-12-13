package yogi.paging.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HandBatchRecomSet {
	
	private int size;
	List<Recom> data = new ArrayList<Recom>();
		
	public HandBatchRecomSet() {
		super();
	}

	public HandBatchRecomSet(int size) {
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
		setUpData("configuration/handbatch-limited.txt");
		return data;
	}
	
	public int getSize() {
		return size;
	}	

	public List<Recom> getDataForSort(int size){
		this.size = size;
		data = new ArrayList<Recom>(size);
		return data;
	}	
}
