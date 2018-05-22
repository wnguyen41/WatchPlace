package edu.orangecoastcollege.watchplace.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.DBModel;

/**
 * The TestDBModel class tests methods for failures.
 * 
 * @author Winston
 *
 */
public class TestDBModel {
	private static final String DB_NAME = "testwatchplace.db";
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "password", "shipping_address",
			"billing_address" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT" };

	private static final String WATCH_TABLE_NAME = "watch";
	private static final String[] WATCH_FIELD_NAMES = { "id_", "reference", "brand", "name", "case_material",
			"case_glass", "case_back_type", "case_shape", "case_diameter", "case_height", "case_water_resistance",
			"dial_color", "dial_index", "dial_hands", "movement", "price" };
	private static final String[] WATCH_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT",
			"TEXT", "TEXT", "REAL", "REAL", "REAL", "TEXT", "TEXT", "TEXT", "TEXT", "REAL" };
	private static final String LISTING_TABLE_NAME = "listings";
	private static final String[] LISTING_FIELD_NAMES = { "id_", "watch_id", "user_id", "quantity" };
	private static final String[] LISTING_FIELD_TYPES = { "INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "INTEGER" };
	String[] fields;
	String[] values;
	String[] watchValues;
	String[] watchFields;

	private static DBModel mUserDB;
	private static DBModel mWatchDB;
	private static DBModel mListingDB;
	private static Controller controller = Controller.getInstance();

	/**
	 * Instantiates a new DBModel.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Make a connection to the database
		mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
		mWatchDB = new DBModel(DB_NAME, WATCH_TABLE_NAME, WATCH_FIELD_NAMES, WATCH_FIELD_TYPES);
		mListingDB = new DBModel(DB_NAME, LISTING_TABLE_NAME, LISTING_FIELD_NAMES, LISTING_FIELD_TYPES);

	}

	/**
	 * Closes the DBModel.
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	/**
	 * Clears the previous database.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		fields = new String[] { "name", "email", "password", "shipping_address", "billing_address" };
		values = new String[] { "Test Name", "test@email.com", "Password1@", "N/A", "N/A" };
		watchFields = new String[] { "reference", "brand", "name", "case_material", "case_glass", "case_back_type",
				"case_shape", "case_diameter", "case_height", "case_water_resistance", "dial_color", "dial_index",
				"dial_hands", "movement", "price" };
		watchValues = new String[] { "REF123", "NameBrand", "WatchName", "Steel", "Sapphire", "Closed", "Round", "36",
				"12", "100", "Black", "Stick", "Stick", "Automatic", "1000" };
		mUserDB.deleteAllRecords();
		mWatchDB.deleteAllRecords();
		mListingDB.deleteAllRecords();
		controller.clearDatabases();
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
	 * Creates records and test if records were created correctly.
	 */
	@Test
	public void testCreateRecord() {
		// Let's try to create a record
		try {
			int key = mUserDB.createRecord(fields, values);
			int recordCount = mUserDB.getRecordCount();
			// After we create a record, the following should be true:
			// 1) Key = 1
			// 2) Record Count = 1

			// All tests begin with assert...
			assertEquals("Testing key is 1.", 1, key);
			assertEquals("Testing record count is 1.", 1, recordCount);

			key = mUserDB.createRecord(fields, values);
			assertEquals("Testing key is 2.", 2, key);
			assertEquals("Testing record count is 2.", 2, mUserDB.getRecordCount());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Should not have generated a SQLException when creating record.");
		}
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
