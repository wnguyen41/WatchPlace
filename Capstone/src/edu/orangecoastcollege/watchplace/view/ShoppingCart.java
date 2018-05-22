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

	// Event Listener on Label.onMouseClicked
	@FXML
	public void goToCheckout(MouseEvent event) {
		ViewNavigator.loadScene("Checkout", ViewNavigator.CHECKOUT_SCENE);
	}
	// Event Listener on Label.onMouseClicked
	@FXML
	public void goToMain(MouseEvent event) {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public void logout(ActionEvent event) {
		controller.logoutUser();
	}
	// Event Listener on Label.onMouseClicked
	@FXML
	public void goToWishList(MouseEvent event) {
		ViewNavigator.loadScene("Your Wish List", ViewNavigator.WISH_LIST_SCENE);
	}
	// Event Listener on ListView[#listView].onMouseClicked
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
	
	// Event Listener on Button[#addToWishListButton].onAction
	@FXML
	public void addToWishlist() {
		Watch w = listView.getSelectionModel().getSelectedItem();
		boolean add = controller.addWatchToWishlist(w);
		controller.deleteShoppingCartItem(w.getId());
		listView.setItems(controller.getShoppingCartWatchesForCurrentUser());
		listView.getSelectionModel().select(-1);
		checkSelected();
	}
	// Event Listener on Button[#deleteItemButton].onAction
	@FXML
	public void deleteItem(ActionEvent event) {
		controller.deleteShoppingCartItem(listView.getSelectionModel().getSelectedItem().getId());
		listView.setItems(controller.getShoppingCartWatchesForCurrentUser());

		listView.getSelectionModel().select(-1);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listView.setItems(controller.getShoppingCartWatchesForCurrentUser());
		fullNameLabel.setText(controller.getCurrentUser().getName());
		count();
		
		deleteItemButton.setOnAction(e -> deleteItem(e));
		
	}
	
	public void count() 
	{
		int count = 0;
		for (Watch w : controller.getShoppingCartWatchesForCurrentUser())
			count++;
				
				
		countItems.setText(String.valueOf(count));
	}
}
