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


	/**
	 * Go to main
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
	 * Initializes all nodes related to filtering and the ListView.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
