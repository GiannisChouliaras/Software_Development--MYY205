package utils;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SimpleCSVReader{

	public ArrayList<String[]> load(String fileName) {

		//FIELDS
		ArrayList <String[]> lines = new ArrayList<String[]>();
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";


		//EXCECUTION
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String [] data = line.split(csvSplitBy);
				for (int i = 0; i < data.length; i++) {
					data[i] = data[i].trim();
				}
				lines.add(data);
			}	
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lines;
	}//end load

}//end class
