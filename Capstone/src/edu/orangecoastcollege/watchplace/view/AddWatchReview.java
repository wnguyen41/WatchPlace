package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Review;
import javafx.event.ActionEvent;

import javafx.scene.control.Slider;

import javafx.scene.control.TextArea;

public class AddWatchReview implements Initializable {
	
	private static Controller controller = Controller.getInstance();
	@FXML
	private Slider rateSlider;
	@FXML
	private TextArea reviewTA;
	@FXML
	private Button addButton;
	@FXML
	private Button cancelButton;

	/**
	 * Adds review
	 */
	@FXML
	public void addReview() {
		
		String[] args = new String[3];
		args[0] = reviewTA.getText();
		args[1] = String.valueOf(rateSlider.getValue());
		args[2] = controller.getCurrentUser().getName();
		
		Review r = controller.createReview(args);
		controller.addReviewToWatch(r);
		
	}
/**
 * Closes add review window and goes back to the watch detail
 */
	@FXML
	public void cancel() {
		
		ViewNavigator.loadScene(controller.getSelectedListing().getWatch().getName(), ViewNavigator.VIEW_ITEM_DETAILS_SCENE);
		
	}
	
	/**
	 * Initializes all nodes related to filtering and the ListView.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
