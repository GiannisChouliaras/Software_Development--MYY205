/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.Register;
import server.RegisterFactory;
import utils.SimpleCSVReader;

import java.util.ArrayList;


public class RegisterTest {

	private RegisterFactory registerFactory;
	private Register register;
	private ArrayList<String[]> data;
	private SimpleCSVReader csvReader;
	private String filepath = "/Users/mac/Documents/JAVA/workspace/CsvReader/src/NBA_team_stats.csv";


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}


	@Before
	public void setUp() throws Exception {
		register = new Register();
		registerFactory = new RegisterFactory();
		csvReader = new SimpleCSVReader();
		data = new ArrayList<String[]>();

		data = csvReader.load(filepath);
		register.setData(data);
	}

	@Test
	public final void testRegisterFactoryNull(){
		assertNotNull("After setup, the register is not null", registerFactory);
	}

	@Test
	public final void testDataNull(){
		assertNotNull("after setup, the data is not null", data);
	}


	@Test
	public final void testGetHeader (){
		String[] header;
		String[] expectedHeader = {"SeasonEnd","Team","Playoffs","W","PTS","oppPTS","FG","FGA","2P","2PA","3P","3PA","FT","FTA","ORB","DRB","AST","STL","BLK","TOV"};
		header = register.getHeader();
		assertArrayEquals(expectedHeader,header);
	}

	@Test
	public final void testGetPotision(){
		int expectedPosition = 1;
		int potision = register.getPosition("Team");
		assertEquals("Getting position of attribute", expectedPosition,potision);
	}

	@Test
	public final void testGetProjectedData(){
		ArrayList<String[]> projectedData = new ArrayList<String[]>();
		projectedData = register.getProjectedData("PTS","W");
		String[] values = projectedData.get(0);
		String[] expected = {"8573","50","1","50","8573","8334","3261","7027","3248","6952","13","75","2038","2645","1369","2406","1913","782","539","1495"};
		assertArrayEquals("Getting the values of the first row", expected, values);
	}



}
