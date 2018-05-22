package edu.orangecoastcollege.watchplace.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.watchplace.controller.Controller;


/**
 * The TestController class tests methods for failures.
 * 
 * @author Winston
 *
 */
public class TestController {

	String[] watchValues;
	private static Controller controller;
	/**
	 * Instantiates a new DBModel.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = Controller.getInstance();
		
	}

	/**
	 * Closes the DBModel.
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		controller.clearDatabases();
		controller.close();
	}

	/**
	 * Clears the previous database.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		watchValues = new String[] { "REF123", "NameBrand", "WatchName", "Steel", "Sapphire", "Closed", "Round", "36",
				"12", "100", "Black", "Stick", "Stick", "Automatic", "1000" };

	}

	/**
	 * Not used
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/**
	 * Creates a Listing and tests if Listing was created correctly;
	 */
	@Test
	public void testCreateListing() {

		String name = "name", email = "test@email.com", password = "Password1@";
		System.out.println(controller.signUpUser(name, email, password));
		controller.signInUser(email, password);
		int key = controller.createListing(watchValues, 1);
		int watchCount = controller.getAllWatches().size();
		int listCount = controller.getAllListings().size();

		assertEquals("Testing key is 1.", 1, key);
		assertEquals("Testing WatchDB record count is 1.", 1, watchCount);
		assertEquals("Testing ListingDB record count is 1.", 1, listCount);


	}

}
