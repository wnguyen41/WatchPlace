package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.CheckBox;

public class Checkout implements Initializable {
	

	private static Controller controller = Controller.getInstance();
	@FXML
	private TextArea shippingAddressTA;
	@FXML
	private CheckBox sameCB;
	@FXML
	private TextArea billingAddressTA;
	@FXML
	private ListView<Watch> shoppingCartLV;
	@FXML
	private Label priceLabel;
	@FXML
	private Label taxLabel;
	@FXML
	private Label totalLabel;
	@FXML
	private Button goToShoppingCartButton;
	@FXML
	private Button goToMainButton;
	@FXML
	private Button checkoutButton;

	/**
	 * Goes back to Shopping Cart
	 */
	@FXML
	public void goToShoppingCart() {
		ViewNavigator.loadScene("Your Shopping Cart", ViewNavigator.SHOPPING_CART_SCENE);
	}
	
	/**
	 * Goes back to main scene
	 */
	@FXML
	public void goToMain() {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}

	/**
	 * Checkout
	 */
	@FXML
	public void checkout() {
		controller.clearShoppingCart();
		ViewNavigator.loadScene("Transaction Complete!", ViewNavigator.THANKYOU_SCENE);
	}
	
	/**
	 * Initializes all nodes related to filtering and the ListView.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		shippingAddressTA.setText(controller.getCurrentUser().getShippingAddress());
		billingAddressTA.setText(controller.getCurrentUser().getShippingAddress());
		sameCB.setOnAction(e -> copy());
		shoppingCartLV.setItems(controller.getShoppingCartWatchesForCurrentUser());
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		double price = calculatePrice();
		double tax = price * 0.08;
		priceLabel.setText(nf.format(price));
		taxLabel.setText(nf.format(tax));
		totalLabel.setText(nf.format(price + tax));
		
	}
	
	/**
	 * If the user's billing address is the same as the shipping address, copies and sets it
	 */
	public void copy() {
		billingAddressTA.setText(controller.getCurrentUser().getShippingAddress());
	}
	
	/**
	 * Calculates total price in Shopping Cart
	 * @return
	 */
	public double calculatePrice()
	{
		double price = 0;
		
		for (Watch w : controller.getShoppingCartWatchesForCurrentUser())
			price += w.getPrice();
		
		return price;
	}
}
