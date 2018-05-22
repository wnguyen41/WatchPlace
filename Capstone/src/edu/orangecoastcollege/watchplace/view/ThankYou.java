package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import javafx.event.ActionEvent;

public class ThankYou implements Initializable {
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private Button mainButton;
	@FXML
	private Button logoutButton;

	// Event Listener on Button[#mainButton].onAction
	@FXML
	public void goToMain(ActionEvent event) {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
	// Event Listener on Button[#logoutButton].onAction
	@FXML
	public void logout(ActionEvent event) {
		controller.logoutUser();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
