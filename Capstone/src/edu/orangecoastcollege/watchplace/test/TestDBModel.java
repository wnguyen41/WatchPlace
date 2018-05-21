package edu.orangecoastcollege.watchplace.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.watchplace.model.DBModel;


/**
 * The TestDBModel class tests methods for failures.
 * @author Winston
 *
 */
public class TestDBModel
{
	private static final String DB_NAME = "watchplace.db";
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "password", "shipping_address",
			"billing_address" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT" };

	private static final String WATCH_TABLE_NAME = "watch";
	private static final String[] WATCH_FIELD_NAMES = { "id_", "reference", "brand", "name", "case_material",
			"case_glass", "case_back_type", "case_shape", "case_diameter", "case_height", "case_water_resistance",
			"dial_color", "dial_index", "dial_hands", "movement", "price" };
	private static final String[] WATCH_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT",
			"TEXT", "TEXT", "REAL", "REAL", "REAL", "TEXT","TEXT", "TEXT", "TEXT", "REAL" };
    String[] fields;
    String[] values;
    String[] watchValues;
    String[] watchFields;
    

    private static DBModel mUserDB;
    private static DBModel mWatchDB;
    /**
     * Instantiates a new DBModel.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        // Make a connection to the database
        mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
        mWatchDB = new DBModel(DB_NAME, WATCH_TABLE_NAME, WATCH_FIELD_NAMES, WATCH_FIELD_TYPES);

    }
    /**
     * Closes the DBModel.
     * @throws Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {

    }
    /**
     * Clears the previous database.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        fields = new String[] { "name","email","password","shipping_address","billing_address" };
        values = new String[] { "Test Name", "test@email.com", "Password1@", "N/A", "N/A"};
        mUserDB.deleteAllRecords();
    }
    /**
     * Not used
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception
    {
    }
    /**
     * Creates records and test if records were created correctly.
     */
    @Test
    public void testCreateRecord()
    {
        // Let's try to create a record
        try
        {
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
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
            fail("Should not have generated a SQLException when creating record.");
        }
    }
    @Test
    public void testAddListing()
    {
    	
    }

}
