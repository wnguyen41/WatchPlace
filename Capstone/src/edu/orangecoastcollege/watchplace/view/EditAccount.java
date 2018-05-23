package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.User;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.CheckBox;

public class EditAccount implements Initializable {
	
	Controller controller = Controller.getInstance();
	
	@FXML
	private Label mUserLabel;
	@FXML
	private TextField mNameTF;
	@FXML
	private TextField mEmailTF;
	@FXML
	private TextField mShippingStreetTF;
	@FXML
	private TextField mShippingCityTF;
	@FXML
	private TextField mShippingStateTF;
	@FXML
	private TextField mShippingCountryTF;
	@FXML
	private CheckBox mIdenticalAddressCheckBox;
	@FXML
	private TextField mBillingStreetTF;
	@FXML
	private TextField mBillingCityTF;
	@FXML
	private TextField mBillingStateTF;
	@FXML
	private TextField mBillingCountryTF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User user = controller.getCurrentUser();
		mNameTF.setText(user.getName());
		mEmailTF.setText(user.getEmail());
		// Set Shipping Address
		// Set Billing Address		
	}
	// Event Listener on Button.onAction
	@FXML
	public void saveChanges(ActionEvent event) {
		String name = mNameTF.getText();
		String email = mEmailTF.getText();
		StringBuilder shippingAddress = new StringBuilder();
		shippingAddress.append(mShippingStreetTF.getText())
			.append(", ").append(mShippingCityTF.getText())
			.append(", ").append(mShippingStateTF.getText())
			.append(", ").append(mShippingCountryTF.getText());
		StringBuilder billingAddress = new StringBuilder();
		billingAddress.append(mBillingStreetTF.getText())
		.append(", ").append(mBillingCityTF.getText())
		.append(", ").append(mBillingStateTF.getText())
		.append(", ").append(mBillingCountryTF.getText());
		
		User user = controller.getCurrentUser();
		user.setName(name);
		user.setEmail(email);
		user.setShippingAddress(shippingAddress.toString());
		user.setBillingAddress(billingAddress.toString());
		
		goToAccountOverview(null);
		}
	// Event Listener on Button.onAction
	@FXML
	public void goToAccountOverview(ActionEvent event) {
		String email = controller.getCurrentUser().getEmail();
		ViewNavigator.loadScene(email.substring(0, email.indexOf('@')) + "'s Account",
				ViewNavigator.ACCOUNT_OVERVIEW_SCENE);
	}
}
