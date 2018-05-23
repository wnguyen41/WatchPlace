package edu.orangecoastcollege.watchplace.view;

import java.net.URL;

import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * The HomeScene is the primary scene in the WatchPlace application
 * It displays the main lists of current watch listings, user information,
 * and links to other related scenes.
 * @author Winston Nguyen
 *
 */
public class HomeScene implements Initializable{ 
	private static Controller controller = Controller.getInstance();
	@FXML
	private ComboBox<String> dialColorCB;
	@FXML
	private ComboBox<String> brandCB;
	@FXML
	private ComboBox<String> caseShapeCB;
	@FXML
	private ComboBox<String> caseMaterialCB;
	@FXML
	private TextField minPriceTF;
	@FXML
	private TextField maxPriceTF;
	@FXML
	private Label fullNameLabel;
	@FXML
	private Button deleteListingButton;
	@FXML
	private Button viewDetailsButton;
	@FXML
	private Button addToShoppingCartButton;
	@FXML
	private Button addToWishListButton;
	@FXML
	private Label listingCount;
	@FXML
	private TextField referenceTF;
	@FXML
	private ListView<Listing> listView;
	
	/**
	 * Initializes all nodes related to filtering and the ListView.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fullNameLabel.setText(controller.getCurrentUser().getName());
		dialColorCB.setItems(controller.getDistinctDialColors());
		brandCB.setItems(controller.getDistinctBrands());
		caseShapeCB.setItems(controller.getDistinctCaseShape());
		caseMaterialCB.setItems(controller.getDistinctCaseMaterial());
		
		updateCount();
		
		dialColorCB.setOnAction(e -> filter());
		brandCB.setOnAction(e -> filter());
		caseShapeCB.setOnAction(e -> filter());
		caseMaterialCB.setOnAction(e -> filter());
		minPriceTF.setOnAction(e -> filter());
		maxPriceTF.setOnAction(e -> filter());
		listView.setItems(controller.getAllListings());
	}
	/**
	 * Filters the list based on the information selected/inputed into filter nodes.
	 */
	@FXML
	public void filter() {
		String brand = brandCB.getSelectionModel().getSelectedItem();
		String shape = caseShapeCB.getSelectionModel().getSelectedItem();
		String material = caseMaterialCB.getSelectionModel().getSelectedItem();
		String color = dialColorCB.getSelectionModel().getSelectedItem();
		double minPrice = (minPriceTF.getText().isEmpty())? Double.MIN_VALUE:Double.parseDouble(minPriceTF.getText());
		double maxPrice = (maxPriceTF.getText().isEmpty())? Double.MAX_VALUE:Double.parseDouble(maxPriceTF.getText());
		
		listView.setItems(controller.filter(l -> (brand == null || l.getWatch().getBrand().equalsIgnoreCase(brand))
				&& (shape == null || l.getWatch().getCaseShape().equalsIgnoreCase(shape)) 
				&& (material == null || l.getWatch().getCaseMaterial().equalsIgnoreCase(material))
				&& (color == null || l.getWatch().getDialColor().equalsIgnoreCase(color))
				&& l.getWatch().getPrice() >= minPrice
				&& l.getWatch().getPrice() <= maxPrice));
		updateCount();
	}
	/**
	 * Logs out the user from the application.
	 */
	@FXML
	public void logout()
	{
		controller.logoutUser();
	}
	/**
	 * Clears all filter nodes.
	 */
	@FXML
	public void clearFilters() {
		brandCB.getSelectionModel().clearSelection();
		caseShapeCB.getSelectionModel().clearSelection();
		caseMaterialCB.getSelectionModel().clearSelection();
		dialColorCB.getSelectionModel().clearSelection();
		minPriceTF.clear();
		maxPriceTF.clear();
	}

	/**
	 * Loads the user's profile scene.
	 */
	@FXML
	public void profileScene() {
		String email = controller.getCurrentUser().getEmail();
		ViewNavigator.loadScene(email.substring(0, email.indexOf('@')) + "'s Account",
				ViewNavigator.ACCOUNT_OVERVIEW_SCENE);
	}

	/**
	 * Loads the user's shopping cart scene.
	 */
	@FXML
	public void goToShoppingCart() {
		ViewNavigator.loadScene("Your Shopping Cart", ViewNavigator.SHOPPING_CART_SCENE);
	}

	/**
	 * Loads the user's wish list scene.
	 */
	@FXML
	public void goToWishList() {
		ViewNavigator.loadScene("Your Wish List", ViewNavigator.WISH_LIST_SCENE);
	}

	/**
	 * Loads the scene that displays information of the Listing that is selected.
	 */
	@FXML
	public void viewDetails() {
		Listing item = listView.getSelectionModel().getSelectedItem();
		controller.setSelectedListing(item);
		ViewNavigator.loadScene(item.getWatch().getName(), ViewNavigator.VIEW_ITEM_DETAILS_SCENE);
	}

	/**
	 * Adds the selected Watch to the user's shopping cart.
	 * @return returns true if successfully able to add the item, otherwise false.
	 */
	@FXML
	public boolean addToShoppingCart() {
		//TODO
		Watch item = listView.getSelectionModel().getSelectedItem().getWatch();
		return controller.addWatchToShoppingCart(item);
	}

	/**
	 * Adds the selected Watch to the user's wish list cart.
	 * @return returns true if successfully able to add the item, otherwise false.
	 */
	@FXML
	public boolean addToWishlist(ActionEvent event) {
		
		Watch item = listView.getSelectionModel().getSelectedItem().getWatch();
		return controller.addWatchToWishlist(item);
	}
	
	/**
	 * Loads the add listing scene.
	 */
	@FXML
	public void addListing() {
		ViewNavigator.loadScene("Add Listing", ViewNavigator.ADD_LISTING_SCENE);
	}

	/**
	 * Deletes the currently selected scene.
	 */
	@FXML
	public void deleteListing() {
		controller.deleteWatch(listView.getSelectionModel().getSelectedItem().getWatch());
		controller.deleteListing(listView.getSelectionModel().getSelectedItem());
		listView.getSelectionModel().select(-1);
		checkSelected();
		filter();
	}
	
	/**
	 * Checks if an item is selected in the ListView.
	 */
	@FXML
	public void checkSelected() {
		listView.setItems(controller.getFilteredListings());
		updateCount();
		if(!listView.getSelectionModel().isEmpty())
		{
			deleteListingButton.setDisable(false);
			viewDetailsButton.setDisable(false);
			addToShoppingCartButton.setDisable(false);
			addToWishListButton.setDisable(false);
		}
		else
		{
			if(controller.getCurrentUser().equals(controller.getSelectedListingSeller()))
			deleteListingButton.setDisable(true);
			viewDetailsButton.setDisable(true);
			addToShoppingCartButton.setDisable(true);
			addToWishListButton.setDisable(true);
		}
	}

	private void updateCount() {
		listingCount.setText("Number of Listings: "+String.valueOf(controller.getFilteredListings().size()));
		
	}
	/**
	 * Filters the list based on inputed text.
	 */
	@FXML
	public void searchByRef() {
		String key = referenceTF.getText();
		listView.setItems(controller.searchByRef(key));
	}
}
