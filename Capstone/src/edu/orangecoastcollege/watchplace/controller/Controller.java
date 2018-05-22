package edu.orangecoastcollege.watchplace.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import edu.orangecoastcollege.watchplace.model.DBModel;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.Review;
import edu.orangecoastcollege.watchplace.model.User;
import edu.orangecoastcollege.watchplace.model.Watch;
import edu.orangecoastcollege.watchplace.view.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Controller manages the flow of information to and from the databases into
 * the application
 *
 */
public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "watchplace.db";
	// Winston
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "password", "shipping_address",
			"billing_address" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT" };
	// Winston
	private static final String WATCH_TABLE_NAME = "watch";
	private static final String[] WATCH_FIELD_NAMES = { "id_", "reference", "brand", "name", "case_material",
			"case_glass", "case_back_type", "case_shape", "case_diameter", "case_height", "case_water_resistance",
			"dial_color", "dial_index", "dial_hands", "movement", "price" };
	private static final String[] WATCH_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT",
			"TEXT", "TEXT", "REAL", "REAL", "REAL", "TEXT", "TEXT", "TEXT", "TEXT", "REAL" };
	// Winston
	private static final String LISTING_TABLE_NAME = "listings";
	private static final String[] LISTING_FIELD_NAMES = { "id_", "watch_id", "user_id", "quantity" };
	private static final String[] LISTING_FIELD_TYPES = { "INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "INTEGER" };

	// YB 
	// Changed fields 
	private static final String REVIEW_TABLE_NAME = "reviews";
	private static final String[] REVIEW_FIELD_NAMES = { "id_", "review", "rate", "user_name"};
	private static final String[] REVIEW_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "REAL", "TEXT"};

	// YB
	private static final String WISHLIST_TABLE_NAME = "watch_wishlist";
	private static final String[] WISHLIST_FIELD_NAMES = { "user_id", "watch_id" };
	private static final String[] WISHLIST_FIELD_TYPES = { "INTEGER", "INTEGER" };

	// YB
	private static final String SHOPPING_CART_TABLE_NAME = "shopping_cart";
	private static final String[] SHOPPING_CART_FIELD_NAMES = { "user_id", "watch_id", "quantity" };
	private static final String[] SHOPPING_CART_FIELD_TYPES = { "INTEGER", "INTEGER", "INTEGER" };

	// YB
	private static final String USER_REVIEW_TABELE_NAME = "user_review";
	private static final String[] USER_REVIEW_FIELD_NAMES = { "user_id", "review_id" };
	private static final String[] USER_REVIEW_FIELD_TYPES = { "INTEGER", "INTEGER" };

	// YB
	private static final String WATCH_REVIEW_TABLE_NAME = "watch_review";
	private static final String[] WATCH_REVIEW_FIELD_NAMES = { "watch_id", "review_id" };
	private static final String[] WATCH_REVIEW_FIELD_TYPES = { "INTEGER", "INTEGER" };

	// TODO Implement following data tables
	private static final String COMPARE_TABLE_NAME = "compare";
	private static final String[] COMPARE_FIELD_NAME = { "user_id", "watch_id" };
	private static final String[] COMPARE_FIELD_TYPES = { "INTEGER", "INTEGER" };

	private static final String ORDER_HISTORY_TABELE_NAME = "order_history"; // Should it be changed to view history
																				// (accessed from profile)

	private static final String VIDEO_GAME_DATA_FILE = "videogames_lite.csv";

	// Below is the relationship table "user_games" which associates users with the
	// video games in their inventory
	private static final String USER_GAMES_TABLE_NAME = "user_games";
	private static final String[] USER_GAMES_FIELD_NAMES = { "user_id", "game_id" };
	private static final String[] USER_GAMES_FIELD_TYPES = { "INTEGER", "INTEGER" };

	private User mCurrentUser;
	private DBModel mUserDB;
	private DBModel mWatchDB;
	private DBModel mListingDB;
	private DBModel mReviewDB;

	private DBModel mWishlistDB;
	private DBModel mShoppingCartDB;

	private DBModel mWatchReviewsDB;
	// private DBModel mVideoGameDB;
	// private DBModel mUserGamesDB;

	private ObservableList<User> mAllUsersList;
	private ObservableList<Watch> mAllWatchesList;
	private ObservableList<Listing> mFilteredListingsList;
	private ObservableList<Listing> mAllListingsList;
	private ObservableList<Review> mAllReviewsList;
	// Stores the selected listing
	private Listing selectedListing;
	// Stores the seller of the listing that was selected by the user
	// Allows access of seller information from other scenes
	private User selectedListingSeller;

	private Controller() {
	}

	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllUsersList = FXCollections.observableArrayList();
			theOne.mAllWatchesList = FXCollections.observableArrayList();
			theOne.mFilteredListingsList = FXCollections.observableArrayList();
			theOne.mAllListingsList = FXCollections.observableArrayList();
			// theOne.mAllGamesList = FXCollections.observableArrayList();

			try {
				// Create the user table in the database
				// Winston
				theOne.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
				ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String email = values.get(2);
					String password = values.get(3);
					theOne.mAllUsersList.add(new User(id, name, email, password, "N/A", "N/A"));
				}
				// Winston
				theOne.mWatchDB = new DBModel(DB_NAME, WATCH_TABLE_NAME, WATCH_FIELD_NAMES, WATCH_FIELD_TYPES);
				resultsList = theOne.mWatchDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String reference = values.get(1);
					String brand = values.get(2);
					String name = values.get(3);
					String caseMaterial = values.get(4);
					String caseGlass = values.get(5);
					String caseBackType = values.get(6);
					String caseShape = values.get(7);
					double caseDiameter = Double.parseDouble(values.get(8));
					double caseHeight = Double.parseDouble(values.get(9));
					double caseWaterResistance = Double.parseDouble(values.get(10));
					String dialColor = values.get(11);
					String dialIndex = values.get(12);
					String dialHands = values.get(13);
					String movement = values.get(14);
					double price = Double.parseDouble(values.get(15));
					theOne.mAllWatchesList.add(new Watch(id, reference, brand, name, caseMaterial, caseGlass,
							caseBackType, caseShape, caseDiameter, caseHeight, caseWaterResistance, dialColor,
							dialIndex, dialHands, movement, price));
				}
				// Winston
				theOne.mListingDB = new DBModel(DB_NAME, LISTING_TABLE_NAME, LISTING_FIELD_NAMES, LISTING_FIELD_TYPES);
				resultsList = theOne.mListingDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					int watchID = Integer.parseInt(values.get(1));
					int userID = Integer.parseInt(values.get(2));
					int quantity = Integer.parseInt(values.get(3));
					Watch watch = getWatchFromList(watchID);
					User user = getUserFromList(userID);
					theOne.mAllListingsList.add(new Listing(id, watch, user, quantity));
				}

				for (Listing l : theOne.mAllListingsList)
					theOne.mFilteredListingsList.add(l);

				// Create the relationship table between users and the video games they own
				// theOne.mUserGamesDB= new DBModel(DB_NAME, USER_GAMES_TABLE_NAME,
				// USER_GAMES_FIELD_NAMES, USER_GAMES_FIELD_TYPES);
				
				// YB
				theOne.mReviewDB = new DBModel(DB_NAME, REVIEW_TABLE_NAME, REVIEW_FIELD_NAMES, 
						REVIEW_FIELD_TYPES);
				resultsList = theOne.mReviewDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String review = values.get(1);
					double rate = Double.parseDouble(values.get(2));
					String userName = values.get(3);
					
					theOne.mAllReviewsList.add(new Review(id, review, rate, userName));
				}

				// YB
				theOne.mWishlistDB = new DBModel(DB_NAME, WISHLIST_TABLE_NAME, WISHLIST_FIELD_NAMES,
						WISHLIST_FIELD_TYPES);

				// YB
				theOne.mShoppingCartDB = new DBModel(DB_NAME, SHOPPING_CART_TABLE_NAME, SHOPPING_CART_FIELD_NAMES,
						SHOPPING_CART_FIELD_TYPES);
				
				// YB
				theOne.mWatchReviewsDB = new DBModel(DB_NAME, WATCH_REVIEW_TABLE_NAME, WATCH_REVIEW_FIELD_NAMES,
						WATCH_REVIEW_FIELD_TYPES);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}

	private static User getUserFromList(int userID) {
		for (User user : theOne.mAllUsersList)
			if (user.getId() == userID)
				return user;
		return null;
	}

	private static Watch getWatchFromList(int watchID) {
		for (Watch watch : theOne.mAllWatchesList)
			if (watch.getId() == watchID)
				return watch;
		return null;
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
	 * Signs up the user.
	 * 
	 * @author Winston Nguyen
	 * @param name
	 *            The user's full name.
	 * @param email
	 *            The user's inputed email.
	 * @param password
	 *            The user's password.
	 * @return true if sign up successful, otherwise false.
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
		String[] values = { name, email, password, "N/A", "N/A" };
		// Insert the new user database
		try {

			System.out.println(name + email + password);

			// Store the new id
			int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length),
					values);
			// Save the new user as the current user
			theOne.mCurrentUser = new User(id, name, email, password, "N/A", "N/A");
			// Add the new user to the observable list
			theOne.mAllUsersList.add(theOne.mCurrentUser);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error creating user, please try again.";
		}

		return "SUCCESS";
	}

	/**
	 * Signs in the user.
	 * 
	 * @author Winston Nguyen
	 * @param email
	 *            The user's email.
	 * @param password
	 *            The user's password
	 * @return True if successful, otherwise false.
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
						theOne.mCurrentUser = u;
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

	public User getCurrentUser() {
		return theOne.mCurrentUser;
	}

	public ObservableList<User> getAllUsers() {
		return theOne.mAllUsersList;
	}

	public ObservableList<Watch> getAllWatches() {
		return theOne.mAllWatchesList;
	}

	/**
	 * Gets the distinct types of movements for a watch.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct movements.
	 */
	public ObservableList<String> getDistinctMovements() {
		List<String> movements = new ArrayList<>();
		movements = Arrays.asList("Manual", "Automatic", "Quartz");
		ObservableList<String> distinctMovements = FXCollections.observableArrayList();
		for (String s : movements)
			distinctMovements.add(s);
		return distinctMovements;
	}

	/**
	 * Gets the distinct colors from all the watches.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct colors.
	 */
	public ObservableList<String> getDistinctDialColors() {
		ObservableList<String> colors = FXCollections.observableArrayList();
		for (Watch w : mAllWatchesList)
			if (!colors.contains(w.getDialColor()))
				colors.add(w.getDialColor());
		FXCollections.sort(colors);
		return colors;
	}

	/**
	 * Gets the distinct brands from all the watches.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct brands.
	 */
	public ObservableList<String> getDistinctBrands() {
		ObservableList<String> brands = FXCollections.observableArrayList();
		for (Watch w : mAllWatchesList)
			if (!brands.contains(w.getBrand()))
				brands.add(w.getBrand());
		FXCollections.sort(brands);
		return brands;
	}

	/**
	 * Gets the distinct case shapes from all the watches.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct case shapes.
	 */
	public ObservableList<String> getDistinctCaseShape() {
		ObservableList<String> shape = FXCollections.observableArrayList();
		for (Watch w : mAllWatchesList)
			if (!shape.contains(w.getCaseShape()))
				shape.add(w.getCaseShape());
		FXCollections.sort(shape);
		return shape;
	}

	/**
	 * Gets the distinct case materials from all the watches.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct case materials.
	 */
	public ObservableList<String> getDistinctCaseMaterial() {
		ObservableList<String> material = FXCollections.observableArrayList();
		for (Watch w : mAllWatchesList)
			if (!material.contains(w.getCaseMaterial()))
				material.add(w.getCaseMaterial());
		FXCollections.sort(material);
		return material;
	}

	/**
	 * Gets the types of indexes on a watch.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct indexes.
	 */
	public ObservableList<String> getDistinctIndex() {
		List<String> indexes = new ArrayList<>();
		indexes = Arrays.asList("Arabic & Stick", "Arabic", "Stick", "Roman & Stick", "Roman", "California");
		ObservableList<String> distinctIndexes = FXCollections.observableArrayList();
		for (String s : indexes)
			distinctIndexes.add(s);
		return distinctIndexes;
	}

	/**
	 * Gets the types of crystal/glass on a watch
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct glass/crystal.
	 */
	public ObservableList<String> getDistinctGlass() {
		List<String> glass = new ArrayList<>();
		glass = Arrays.asList("Sapphire", "Mineral", "Acrylic");
		ObservableList<String> distinctGlass = FXCollections.observableArrayList();
		for (String s : glass)
			distinctGlass.add(s);
		return distinctGlass;
	}

	/**
	 * Gets the types of back types of a watch.
	 * 
	 * @author Winston Nguyen
	 * @return the ObservableList of distinct back types.
	 */
	public ObservableList<String> getDistinctBackTypes() {
		List<String> back = new ArrayList<>();
		back = Arrays.asList("Opened", "Closed", "Skeleton");
		ObservableList<String> distinctBack = FXCollections.observableArrayList();
		for (String s : back)
			distinctBack.add(s);
		return distinctBack;
	}

	public ObservableList<Listing> filter(Predicate<Listing> criteria) {
		theOne.mFilteredListingsList.clear();
		for (Listing vg : mAllListingsList)
			if (criteria.test(vg))
				theOne.mFilteredListingsList.add(vg);

		return theOne.mFilteredListingsList;
	}

	/**
	 * Logs out the current user.
	 * 
	 * @author Winston Nguyen
	 */
	public void logoutUser() {
		theOne.mCurrentUser = null;
		ViewNavigator.loadScene("WatchPlace", ViewNavigator.SIGN_IN_SCENE);
	}

	/**
	 * Creates a new watch and listing and adds them to the databases.
	 * 
	 * @author Winston Nguyen
	 * @param args
	 *            an array of Watch details.
	 */
	public int createListing(String[] args, int quantity) {
		try {
			int watchId = theOne.mWatchDB
					.createRecord(Arrays.copyOfRange(WATCH_FIELD_NAMES, 1, WATCH_FIELD_NAMES.length), args);
			theOne.mAllWatchesList.add(new Watch(watchId, args[0], args[1], args[2], args[3], args[4], args[5], args[6],
					Double.parseDouble(args[7]), Double.parseDouble(args[8]), Double.parseDouble(args[9]), args[10],
					args[11], args[12], args[13], Double.parseDouble(args[14])));
			String[] values = { String.valueOf(watchId), String.valueOf(theOne.getCurrentUser().getId()),
					String.valueOf(quantity) };
			int listingID = theOne.mListingDB
					.createRecord(Arrays.copyOfRange(LISTING_FIELD_NAMES, 1, LISTING_FIELD_NAMES.length), values);
			theOne.mAllListingsList
					.add(new Listing(listingID, getWatchFromList(watchId), theOne.mCurrentUser, quantity));
			return listingID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ObservableList<Listing> getAllListings() {
		return theOne.mAllListingsList;
	}

	/**
	 * Sorts the list based on the inputed reference.
	 * @author Winston Nguyen
	 * @param key
	 *            the inputed reference
	 * @return the filtered ObservableList.
	 */
	public ObservableList<Listing> searchByRef(String key) {
		theOne.mFilteredListingsList.clear();

		for (Listing l : theOne.mAllListingsList)
			if (l.getWatch().getReference().substring(0, key.length()).equals(key))
				theOne.mFilteredListingsList.add(l);
		return theOne.mFilteredListingsList;

	}

	/**
	 * Deletes the selected listing.
	 * 
	 * @author Winston Nguyen
	 * @param selectedItem
	 *            a Listing object.
	 */
	public void deleteListing(Listing selectedItem) {
		if (selectedItem == null)
			return;
		for (Listing target : mAllListingsList) {
			if (target == selectedItem) {
				try {
					mListingDB.deleteRecord(selectedItem.getId() + "");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mAllListingsList.remove(target);
				return;
			}
			return;
		}
	}

	/**
	 * Gets the current filtered list.
	 * 
	 * @return the filtered ObservableList.
	 */
	public ObservableList<Listing> getFilteredListings() {
		return theOne.mFilteredListingsList;
	}

	/**
	 * Gets the Listing that was selected in HomeScene.
	 * 
	 * @return the selectedListing
	 */
	public Listing getSelectedListing() {
		return selectedListing;
	}

	/**
	 * Sets the selectedListing to what was selected in HomeScene.
	 * 
	 * @param selectedListing
	 *            the selectedListing to set
	 */
	public void setSelectedListing(Listing selectedListing) {
		this.selectedListing = selectedListing;
	}

	/**
	 * Gets the seller/creator of the selected Listing.
	 * 
	 * @return the selectedListingSeller
	 */
	public User getSelectedListingSeller() {
		return selectedListingSeller;
	}

	/**
	 * Sets the seller/creator of the selected Listing.
	 * 
	 * @param selectedListingSeller
	 *            the selectedListingSeller to set
	 */
	public void setSelectedListingSeller(User selectedListingSeller) {
		this.selectedListingSeller = selectedListingSeller;
	}

	/**
	 * Deletes the Watch of a Listing.
	 * 
	 * @author Winston Nguyen
	 * @param watch
	 *            the watch selected.
	 */
	public void deleteWatch(Watch watch) {
		if (watch == null)
			return;
		for (Watch target : mAllWatchesList) {
			if (target == watch) {
				try {
					mWatchDB.deleteRecord(watch.getId() + "");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mAllWatchesList.remove(target);
				return;
			}
			return;
		}
	}

	/**
	 *
	 * @return
	 */
	public ObservableList<Listing> getSellersListings() {
		ObservableList<Listing> list = FXCollections.observableArrayList();
		for (Listing l : theOne.getAllListings())
			if (l.getUser().getEmail().equals(selectedListingSeller.getEmail()))
				list.add(l);
		return list;
	}

	/**
	 * Gets the seller's rating score.
	 * 
	 * @return the User's rating
	 */
	public double getSellersRating() {
		// TODO Return the average rating of this seller
		return 0.0;
	}
	
	// YB
	public Review createReview(String[] args) {
		try {
			int reviewId = theOne.mReviewDB
					.createRecord(Arrays.copyOfRange(REVIEW_FIELD_NAMES, 1, REVIEW_FIELD_NAMES.length), args);
			Review r = new Review(reviewId, args[0], Double.parseDouble(args[1]), theOne.mCurrentUser.getName());
			theOne.mAllReviewsList.add(r);
			
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// YB
		public ObservableList<Review> getReviewsForWatch(Watch w) {
			ObservableList<Review> watchReviewsList = FXCollections.observableArrayList();
			try {
				ArrayList<ArrayList<String>> resultsList = mWatchReviewsDB
						.getRecord(String.valueOf(w.getId()));
				// loop through the results
				int reviewId;
				for (ArrayList<String> values : resultsList) {
					reviewId = Integer.parseInt(values.get(1));
					// Loop through all the games, try to find a match
					for (Review r : theOne.mAllReviewsList) {
						if (reviewId == r.getId()) {
							watchReviewsList.add(r);
							break;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return watchReviewsList;
		}
		
		// YB
		public boolean addReviewToWatch(Review review, Watch w) {
			ObservableList<Review> reviewsOwnedByWatch = getReviewsForWatch(w);
			if (reviewsOwnedByWatch.contains(review))
				return false;
			String[] values = { String.valueOf(w.getId()), String.valueOf(review.getId()) };
			try {
				mWatchReviewsDB.createRecord(WATCH_REVIEW_FIELD_NAMES, values);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

	// YB
	public ObservableList<Watch> getWishlistWatchesForCurrentUser() {
		ObservableList<Watch> userWatchesList = FXCollections.observableArrayList();
		try {
			ArrayList<ArrayList<String>> resultsList = mWishlistDB
					.getRecord(String.valueOf(theOne.mCurrentUser.getId()));
			// loop through the results
			int watchID;
			for (ArrayList<String> values : resultsList) {
				watchID = Integer.parseInt(values.get(1));
				// Loop through all the games, try to find a match
				for (Watch w : theOne.mAllWatchesList) {
					if (watchID == w.getId()) {
						userWatchesList.add(w);
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userWatchesList;
	}

	// YB
	public boolean addWatchToWishlist(Watch selectedWatch) {
		ObservableList<Watch> watchesOwnedByCurrentUser = getWishlistWatchesForCurrentUser();
		if (watchesOwnedByCurrentUser.contains(selectedWatch))
			return false;
		String[] values = { String.valueOf(theOne.mCurrentUser.getId()), String.valueOf(selectedWatch.getId()) };
		try {
			mWishlistDB.createRecord(WISHLIST_FIELD_NAMES, values);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// YB
	public ObservableList<Watch> getShoppingCartWatchesForCurrentUser() {
		ObservableList<Watch> userWatchesList = FXCollections.observableArrayList();
		try {
			ArrayList<ArrayList<String>> resultsList = mShoppingCartDB
					.getRecord(String.valueOf(theOne.mCurrentUser.getId()));
			// loop through the results
			int watchID;
			for (ArrayList<String> values : resultsList) {
				watchID = Integer.parseInt(values.get(1));
				// Loop through all the games, try to find a match
				for (Watch w : theOne.mAllWatchesList) {
					if (watchID == w.getId()) {
						userWatchesList.add(w);
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userWatchesList;
	}

	// YB
	public boolean addWatchToShoppingCart(Watch selectedWatch) {
		ObservableList<Watch> watchesOwnedByCurrentUser = getShoppingCartWatchesForCurrentUser();
		if (watchesOwnedByCurrentUser.contains(selectedWatch))
			return false;
		String[] values = { String.valueOf(theOne.mCurrentUser.getId()), String.valueOf(selectedWatch.getId()), "1" };
		try {
			mShoppingCartDB.createRecord(SHOPPING_CART_FIELD_NAMES, values);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// YB
	public void deleteWishlistItem(int id) {

		try {
			mWishlistDB.deleteRecord(String.valueOf(id));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// YB
	public void deleteShoppingCartItem(int id) {

		try {
			mShoppingCartDB.deleteRecord(String.valueOf(id));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// YB
	public void clearShoppingCart()
	{
		try {
			theOne.mShoppingCartDB.deleteAllRecords();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Closes the controller.
	 */
	public void close() {
		theOne = null;
	}

	/**
	 * Deletes all databases
	 */
	public void clearDatabases() {
		try {
			theOne.mListingDB.deleteAllRecords();
			theOne.mUserDB.deleteAllRecords();
			theOne.mWatchDB.deleteAllRecords();
			theOne.mWishlistDB.deleteAllRecords();
			;
			theOne.mShoppingCartDB.deleteAllRecords();
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
