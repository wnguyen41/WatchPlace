package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.User;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class AccountOverview implements Initializable {
	
	Controller controller = Controller.getInstance();
	
	@FXML
	private Label mEmailLabel;
	@FXML
	private Label mNameLabel;
	@FXML
	private Label mShippingAddressLabel;
	@FXML
	private Label mBillingAddressLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User user = controller.getCurrentUser();
		mEmailLabel.setText(user.getEmail());
		mNameLabel.setText(user.getName());
		mShippingAddressLabel.setText(user.getShippingAddress());
		mBillingAddressLabel.setText(user.getBillingAddress());
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToEditAccountScene(ActionEvent event) {
		// Go to Edit Account Scene (Unimplemented)
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToDeleteAccountScene(ActionEvent event) {
		// Go to Delete Account Scene (Unimplemented)
	}	
	// Event Listener on Button.onAction
	@FXML
	public void goToHomeScene(ActionEvent event) {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
}
