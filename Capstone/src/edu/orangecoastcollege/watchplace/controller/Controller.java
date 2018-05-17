package edu.orangecoastcollege.watchplace.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

import edu.orangecoastcollege.watchplace.model.DBModel;
import edu.orangecoastcollege.watchplace.model.User;
import edu.orangecoastcollege.watchplace.model.Watch;
import edu.orangecoastcollege.watchplace.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "watchplace.db";
	//Winston
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "password", "shipping_address",
			"billing_address" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT" };
	//Winston
	private static final String WATCH_TABLE_NAME = "watch";
	private static final String[] WATCH_FIELD_NAMES = { "id_", "reference", "brand", "name", "case_material",
			"case_glass", "case_back_type", "case_shape", "case_diameter", "case_diameter", "case_diameter",
			"case_height", "case_water_resistance", "dial_color", "dial_index", "movement", "price" };
	private static final String[] WATCH_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT",
			"TEXT", "TEXT", "TEXT", "TEXT", "REAL", "REAL", "REAL", "TEXT", "TEXT", "TEXT", "REAL" };
	//Winston
	private static final String LISTING_TABLE_NAME = "listings";
	private static final String[] LISTING_FIELD_NAMES = {"id_","watch_id","user_id","quantity"};
	private static final String[] LISTING_FIELD_TYPES = {"INTEGER PRIMARY KEY","INTEGER","INTEGER","INTEGER"};
	
	
	private static final String COMPARE_TABLE_NAME = "compare";
	private static final String[] COMPARE_FIELD_NAME = { "user_id", "watch_id" };
	private static final String[] COMPARE_FIELD_TYPES = { "INTEGER", "INTEGER" };

	private static final String VIDEO_GAME_TABLE_NAME = "video_game";
	private static final String[] VIDEO_GAME_FIELD_NAMES = { "_id", "name", "platform", "year", "genre", "publisher" };
	private static final String[] VIDEO_GAME_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER", "TEXT",
			"TEXT" };
	private static final String VIDEO_GAME_DATA_FILE = "videogames_lite.csv";

	// Below is the relationship table "user_games" which associates users with the
	// video games in their inventory
	private static final String USER_GAMES_TABLE_NAME = "user_games";
	private static final String[] USER_GAMES_FIELD_NAMES = { "user_id", "game_id" };
	private static final String[] USER_GAMES_FIELD_TYPES = { "INTEGER", "INTEGER" };

	private User mCurrentUser;
	private DBModel mUserDB;
	private DBModel mWatchDB;
	//private DBModel mVideoGameDB;
	//private DBModel mUserGamesDB;

	private ObservableList<User> mAllUsersList;
	private ObservableList<Watch> mAllWatchesList;
	private ObservableList<Watch> mFilteredWatchesList;

	private Controller() {
	}

	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllUsersList = FXCollections.observableArrayList();
			theOne.mAllWatchesList = FXCollections.observableArrayList();
			theOne.mFilteredWatchesList = FXCollections.observableArrayList();
			// theOne.mAllGamesList = FXCollections.observableArrayList();

			 try {
			// Create the user table in the database
			 theOne.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES,
			 USER_FIELD_TYPES);
			 ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
			 for (ArrayList<String> values : resultsList)
			 {
			 int id = Integer.parseInt(values.get(0));
			 String name = values.get(1);
			 String email = values.get(2);
			 String password = values.get(3);
			 theOne.mAllUsersList.add(new User(id, name, email, password, "N/A", "N/A"));
			 }

			// // Create the video game table in the database, loading games from the CSV
			// file
			// theOne.mVideoGameDB = new DBModel(DB_NAME, VIDEO_GAME_TABLE_NAME,
			// VIDEO_GAME_FIELD_NAMES, VIDEO_GAME_FIELD_TYPES);
			// theOne.initializeVideoGameDBFromFile();
			// resultsList = theOne.mVideoGameDB.getAllRecords();
			// for (ArrayList<String> values : resultsList)
			// {
			// int id = Integer.parseInt(values.get(0));
			// String name = values.get(1);
			// String platform = values.get(2);
			// int year = Integer.parseInt(values.get(3));
			// String genre = values.get(4);
			// String publisher = values.get(5);
			// theOne.mAllGamesList.add(new VideoGame(id, name, platform, year, genre,
			// publisher));
			// }

			// Create the relationship table between users and the video games they own
			// theOne.mUserGamesDB= new DBModel(DB_NAME, USER_GAMES_TABLE_NAME,
			// USER_GAMES_FIELD_NAMES, USER_GAMES_FIELD_TYPES);

			 } catch (SQLException e) {
			 e.printStackTrace();
			 }
		}
		return theOne;
	}

	public boolean isValidPassword(String password) {
		// Valid password must contain (see regex below):
		// At least one lower case letter
		// At least one digit
		// At least one special character (@, #, $, %, !)
		// At least one upper case letter
		// At least 8 characters long, but no more than 16
		return password.matches(
				"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\\\|,.<>\\/?]).{8,16}$");
	}

	public boolean isValidEmail(String email) {
		return email.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	/**
	 * Winston
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public String signUpUser(String name, String email, String password) {
		// Check email to see if its valid
		if (!isValidEmail(email))
			return "Email address not valid.; Please try different address.";

		// Check to see if email is already used
		// Loop through all users list
		for (User u : theOne.mAllUsersList)
			if (email.equalsIgnoreCase(u.getEmail()))
				return "Email address already used. Please sign in or use different address.";

		// Check password to see if valid
		if (!isValidPassword(password))
			return "Password must be at least 8 characters, including 1 upper case letter, 1 number, and 1 symbol.";

		// Made it through all the checks, create the new user in the database
		String[] values = { name, email, "user", password };
		// Insert the new user database
		try {

			System.out.println(name+email+password);

			// Store the new id
			int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length), values);
			// Save the new user as the current user
			theOne.mCurrentUser = new User(id, name, email, password,"N/A","N/A");
			// Add the new user to the observable list
			theOne.mAllUsersList.add(theOne.mCurrentUser);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error creating user, please try again.";
		}

		return "SUCCESS";
	}
	/**
	 * Winston
	 * @param email
	 * @param password
	 * @return
	 */
	public String signInUser(String email, String password) {
		// Loop through the list of all users
		for (User u : theOne.mAllUsersList) {
			if (u.getEmail().equalsIgnoreCase(email)) {
				// Go into database to retrieve password:
				try {
					ArrayList<ArrayList<String>> userResults = theOne.mUserDB.getRecord(String.valueOf(u.getId()));
					String storedPassword = userResults.get(0).get(3);
					// Check the passwords
					if (password.equals(storedPassword)) {
						mCurrentUser = u;
						return "SUCCESS";
					} else
						break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Incorrect email and/or password. Please try again.";
	}

	// public ObservableList<VideoGame> getGamesForCurrentUser()
	// {
	// ObservableList<VideoGame> userGamesList =
	// FXCollections.observableArrayList();
	// try
	// {
	// ArrayList<ArrayList<String>> resultsList =
	// mUserGamesDB.getRecord(String.valueOf(theOne.mCurrentUser.getId()));
	// // loop through the results
	// int gameId;
	// for( ArrayList<String> values : resultsList)
	// {
	// gameId = Integer.parseInt(values.get(1));
	// // Loop through all the games, try to find a match
	// for(VideoGame vg: theOne.mAllGamesList)
	// {
	// if(gameId == vg.getId())
	// {
	// userGamesList.add(vg);
	// break;
	// }
	// }
	// }
	// }
	// catch (SQLException e)
	// {
	// e.printStackTrace();
	// }
	// return userGamesList;
	// }

	// public boolean addGameToUsersInventory(VideoGame selectedGame) {
	// ObservableList<VideoGame> gamesOwnedByCurrentUser = getGamesForCurrentUser();
	// if(gamesOwnedByCurrentUser.contains(selectedGame))
	// return false;
	// String[] values = {String.valueOf(theOne.mCurrentUser.getId()),
	// String.valueOf(selectedGame.getId())};
	// try
	// {
	// mUserGamesDB.createRecord(USER_GAMES_FIELD_NAMES, values);
	// }
	// catch (SQLException e)
	// {
	// e.printStackTrace();
	// return false;
	// }
	// return true;
	// }

	public User getCurrentUser() {
		return mCurrentUser;
	}

	public ObservableList<User> getAllUsers() {
		return theOne.mAllUsersList;
	}

	public ObservableList<Watch> getAllWatches() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ObservableList<String> getDistinctMovements() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<String> getDistinctDialColors() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<String> getDistinctBrands() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<String> getDistinctCaseShape() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<String> getDistinctCaseMaterial() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ObservableList<String> getDistinctIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<String> getDistinctGlass() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<String> getDistinctBackTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	// public ObservableList<VideoGame> getAllVideoGames() {
	// return theOne.mAllGamesList;
	// }

	// public ObservableList<String> getDistinctPlatforms() {
	// ObservableList<String> platforms = FXCollections.observableArrayList();
	// for (VideoGame vg : theOne.mAllGamesList)
	// if (!platforms.contains(vg.getPlatform()))
	// platforms.add(vg.getPlatform());
	// FXCollections.sort(platforms);
	// return platforms;
	// }
	//Brands case shape, case Material, Color

//	 public ObservableList<String> getDistinctPublishers() {
//		 ObservableList<String> publishers = FXCollections.observableArrayList();
//		 for (VideoGame vg : theOne.mAllGamesList)
//		 if (!publishers.contains(vg.getPublisher()))
//		 publishers.add(vg.getPublisher());
//		 FXCollections.sort(publishers);
//		 return publishers;
//	 }

	// private int initializeVideoGameDBFromFile() throws SQLException {
	// int recordsCreated = 0;
	//
	// // If the result set contains results, database table already has
	// // records, no need to populate from file (so return false)
	// if (theOne.mUserDB.getRecordCount() > 0)
	// return 0;
	//
	// try {
	// // Otherwise, open the file (CSV file) and insert user data
	// // into database
	// Scanner fileScanner = new Scanner(new File(VIDEO_GAME_DATA_FILE));
	// // First read is for headings:
	// fileScanner.nextLine();
	// // All subsequent reads are for user data
	// while (fileScanner.hasNextLine()) {
	// String[] data = fileScanner.nextLine().split(",");
	// // Length of values is one less than field names because values
	// // does not have id (DB will assign one)
	// String[] values = new String[VIDEO_GAME_FIELD_NAMES.length - 1];
	// values[0] = data[1].replaceAll("'", "''");
	// values[1] = data[2];
	// values[2] = data[3];
	// values[3] = data[4];
	// values[4] = data[5];
	// theOne.mVideoGameDB.createRecord(Arrays.copyOfRange(VIDEO_GAME_FIELD_NAMES,
	// 1, VIDEO_GAME_FIELD_NAMES.length), values);
	// recordsCreated++;
	// }
	//
	// // All done with the CSV file, close the connection
	// fileScanner.close();
	// } catch (FileNotFoundException e) {
	// return 0;
	// }
	// return recordsCreated;
	// }

	public ObservableList<Watch> filter(Predicate<Watch> criteria)
	{
		mFilteredWatchesList.clear();
		for (Watch vg : mAllWatchesList)
			if (criteria.test(vg))
				mFilteredWatchesList.add(vg);
		
		return mFilteredWatchesList;
	}

	public void logoutUser() {
		mCurrentUser = null;
		ViewNavigator.loadScene("WatchPlace", ViewNavigator.SIGN_IN_SCENE);
	}

	public void createListing(String[] args) {
		try {
			mWatchDB.createRecord(Arrays.copyOfRange(WATCH_FIELD_NAMES, 1, WATCH_FIELD_NAMES.length), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
