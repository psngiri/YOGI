package yogi.paging.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class TestSampleUtilities {

	public static void main(String[] args) {
		//readFromFileAndConstructObject();
		
		RecomSet recomSet = new RecomSet();
		List<Recom> set = recomSet.getRealData();
		System.out.println("Size : " + set.size());		
	}
	
	public static void readFromFileAndConstructObject() {
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream("configuration/sample.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int i = 0;
			@SuppressWarnings("unused")
			StringTokenizer stzr;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				// Print the content on the console
				//System.out.println(strLine);
				i++;
				stzr = new StringTokenizer(strLine, ",");
				String splits[] = strLine.split(",");
				for(int j = 0; j < splits.length; j++) {

					//while(stzr.hasMoreTokens()) {					
					if(i!=1 && (j==3 || j==9 || j==10 || j==11 || j==12))
						System.out.print(splits[j] + "\t\t");
					else 
						System.out.print(splits[j] + "\t");
				}
				System.out.println();
			}
			//Close the input stream
			in.close();
		} catch (Exception e){//Catch exception if any
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();			
		}
	}
}
