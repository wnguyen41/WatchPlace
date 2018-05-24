package edu.orangecoastcollege.watchplace.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class Wishlist implements Initializable {
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private Label fullNameLabel;
	@FXML
	private ListView<Watch> listView;
	@FXML
	private Button viewDetailsButton;
	@FXML
	private Button addToShoppingCartButton;
	@FXML
	private Button deleteItemButton;
	@FXML
	private Label cartItemCount;
	@FXML
	private Label ShoppingCartLabel;
	@FXML
	private Label countItemsLabel;
	


	/**
	 * Goes to checkout scene
	 */
	@FXML
	public void goToCheckout() {
		ViewNavigator.loadScene("Checkout", ViewNavigator.CHECKOUT_SCENE);
	}

	/**
	 * Goes to main scene
	 */
	@FXML
	public void goToMain() {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}

	/**
	 * Logout the current user
	 * @param event
	 */
	@FXML
	public void logout() {
		controller.logoutUser();
	}

	/**
	 * Loads Shopping Cart scene
	 */
	@FXML
	public void goToShoppingCart() {
		ViewNavigator.loadScene("Your Shopping Cart", ViewNavigator.SHOPPING_CART_SCENE);
	}

	/**
	 * Actions when a watch is selected in the list view
	 */
	@FXML
	public void checkSelected() {
		if(!listView.getSelectionModel().isEmpty())
		{
			deleteItemButton.setDisable(false);
			addToShoppingCartButton.setDisable(false);
		}
		else
		{
			deleteItemButton.setDisable(true);
			addToShoppingCartButton.setDisable(true);
		}
	}
	
	/**
	 * Adds the selected item to Shopping Cart and deletes it from wishlist
	 */
	@FXML
	public void addToShoppingCart() {
		
		Watch w = listView.getSelectionModel().getSelectedItem();
		boolean add = controller.addWatchToShoppingCart(w);
		controller.deleteWishlistItem(w.getId());
		listView.setItems(controller.getWishlistWatchesForCurrentUser());
		listView.getSelectionModel().select(-1);
		checkSelected();
	}
	
	/**
	 * Deletes the item from wishlist
	 */
	@FXML
	public void deleteItem() {
		controller.deleteWishlistItem(listView.getSelectionModel().getSelectedItem().getId());
		listView.setItems(controller.getWishlistWatchesForCurrentUser());
		listView.getSelectionModel().select(-1);
	}
	
	/**
	 * Initializes all nodes related to filtering and the ListView.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listView.setItems(controller.getWishlistWatchesForCurrentUser());
		fullNameLabel.setText(controller.getCurrentUser().getName());
		count();
		
		deleteItemButton.setOnAction(e -> deleteItem());
		
	}
	
	/**
	 * Counts the number of items in wishlist and sets to the label
	 */
	public void count() 
	{
		countItemsLabel.setText(String.valueOf(listView.getSelectionModel().getSelectedItems().size()));
	}
}
