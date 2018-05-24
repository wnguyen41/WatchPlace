package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;

public class ShoppingCart implements Initializable {
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private Label fullNameLabel;
	@FXML
	private ListView<Watch> listView;
	@FXML
	private Button viewDetailsButton;
	@FXML
	private Button addToWishListButton;
	@FXML
	private Button deleteItemButton;
	@FXML
	private Label cartItemCount;
	@FXML
	private Label ShoppingCartLabel;
	@FXML
	private Label countItems;


	/**
	 * Loads the checkout scene
	 */
	@FXML
	public void goToCheckout() {
		ViewNavigator.loadScene("Checkout", ViewNavigator.CHECKOUT_SCENE);
	}

	/**
	 * Goes back to the main scene
	 */
	@FXML
	public void goToMain() {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}

	/**
	 * Logout the current user
	 */
	@FXML
	public void logout() {
		controller.logoutUser();
	}

	/**
	 * Loads wishlist scene
	 */
	@FXML
	public void goToWishList() {
		ViewNavigator.loadScene("Your Wish List", ViewNavigator.WISH_LIST_SCENE);
	}
	
	/**
	 * Actions when an item is selected in the list view
	 */
	@FXML
	public void checkSelected() {
		if(!listView.getSelectionModel().isEmpty())
		{
			deleteItemButton.setDisable(false);
			addToWishListButton.setDisable(false);
		}
		else
		{
			deleteItemButton.setDisable(true);
			addToWishListButton.setDisable(true);
		}
	}


	/**
	 * Adds the watch in Shopping Cart to wishlist and deletes it
	 */
	@FXML
	public void addToWishlist() {
		Watch w = listView.getSelectionModel().getSelectedItem();
		boolean add = controller.addWatchToWishlist(w);
		controller.deleteShoppingCartItem(w.getId());
		listView.setItems(controller.getShoppingCartWatchesForCurrentUser());
		listView.getSelectionModel().select(-1);
		checkSelected();
	}

	/**
	 * Deletes the watch in Shopping Cart
	 * @param event
	 */
	@FXML
	public void deleteItem(ActionEvent event) {
		controller.deleteShoppingCartItem(listView.getSelectionModel().getSelectedItem().getId());
		listView.setItems(controller.getShoppingCartWatchesForCurrentUser());

		listView.getSelectionModel().select(-1);
	}
	
	/**
	 * Initializes all nodes related to filtering and the ListView.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listView.setItems(controller.getShoppingCartWatchesForCurrentUser());
		fullNameLabel.setText(controller.getCurrentUser().getName());
		count();
		
		deleteItemButton.setOnAction(e -> deleteItem(e));
		
	}
	
	/**
	 * Counts the number of watches in Shopping Cart and sets to the label
	 */
	public void count() 
	{	
		countItems.setText(String.valueOf(listView.getSelectionModel().getSelectedItems().size()));
	}
}
