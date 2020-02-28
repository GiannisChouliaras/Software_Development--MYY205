package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.Dataset;
import server.Register;
import server.RegisterManager;
import utils.SimpleCSVReader;

import java.util.ArrayList;

public class DatasetTest {

	private Dataset dataset;
	private int value;
	private int filtered;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		dataset = new Dataset();
		String canonicalPath = "/Users/mac/Documents/JAVA/workspace/CsvReader/src/NBA_team_stats.csv";
		value = dataset.registerDataset("NBAStats", canonicalPath);
		filtered = dataset.filterDataset("NBAStats", "Boston", "Team", "Boston Celtics");
	}

	@Test
	public final void DatasetNotNull() {
		assertNotNull("Check if dataset is not null", dataset);
	}

	@Test
	public final void registerDatasetTest() {
		int expected = 0;
		assertEquals(expected, value);
	}

	@Test
	public final void retrieveDatasetTest() {
		String[] expectedHeader = {"SeasonEnd","Team","Playoffs","W","PTS","oppPTS","FG","FGA","2P","2PA","3P","3PA","FT","FTA","ORB","DRB","AST","STL","BLK","TOV"};
		String[] header;
		ArrayList<String[]> dataRe = new ArrayList<String[]>();
		header = dataset.retrieveDataset("NBAStats",dataRe);
		assertArrayEquals("lets check if the header is equal to expected", expectedHeader, header);
		assertNotNull("lets check if dataRe is not null", dataRe);
	}

	@Test
	public final void filterDatasetTest() {
		int expected = 0;
		assertEquals(expected, filtered);
	}

	@Test
	public final void getDatasetProjection() {
		ArrayList<String[]> getData = new ArrayList<String[]>();
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add("PTS");
		attributes.add("W");

		getData = dataset.getDatasetProjection("Boston", attributes);
		String pts  = getData.get(0)[0]; //get pts 9303
		String wins = getData.get(0)[1]; //get win 61

		assertNotNull(getData);
		assertEquals("9303", pts);
		assertEquals("61", wins);
	}

}
