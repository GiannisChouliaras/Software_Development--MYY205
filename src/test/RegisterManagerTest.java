package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.Register;
import server.RegisterManager;
import utils.SimpleCSVReader;

public class RegisterManagerTest {

	private RegisterManager registerManager;
	private Register register;
	private SimpleCSVReader csvReader;
	private String filepath = "/Users/mac/Documents/JAVA/workspace/CsvReader/src/NBA_team_stats.csv";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		registerManager = new RegisterManager();
		csvReader = new SimpleCSVReader();
		register = new Register();
		register.setData(csvReader.load(filepath));
		register.setName("NBAStats");
		registerManager.addRegister(register);
	}

	@Test
	public final void containsNameTest() {
		boolean expected = true;
		boolean value = registerManager.containsName("NBAStats");
		assertEquals("We will check if the Register with name NBAStats is in database", expected, value);
	}



}
