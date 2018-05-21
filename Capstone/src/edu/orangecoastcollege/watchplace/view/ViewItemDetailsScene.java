package edu.orangecoastcollege.watchplace.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.scene.traversal.WeightedClosestCorner;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
/**
 * Displays information regarding the selected listing from HomeScene.
 * @author Winston Nguyen
 *
 */
public class ViewItemDetailsScene implements Initializable{
	private static Controller controller = Controller.getInstance();
	@FXML
	private Label colorLabel;
	@FXML
	private Label indexesLabel;
	@FXML
	private Label handsLabel;
	@FXML
	private Label movementLabel;
	@FXML
	private Label materialLabel;
	@FXML
	private Label glassLabel;
	@FXML
	private Label backLabel;
	@FXML
	private Label shapeLabel;
	@FXML
	private Label diameterLabel;
	@FXML
	private Label heightLabel;
	@FXML
	private Label waterResistenceLabel;
	@FXML
	private Label brandLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label referenceLabel;
	@FXML
	private Label quantityLabel;
	@FXML
	private Label userLabel;
	
	private Listing selectedItem;
	/**
	 * Loads the seller's scene.
	 */
	@FXML
	public void loadSellerScene() {
		controller.setSelectedListingSeller(selectedItem.getUser());
		String email = selectedItem.getUser().getEmail();
		ViewNavigator.loadScene(email.substring(0, email.indexOf("@")).concat("'s Seller Profile"), ViewNavigator.SELLER_DETAILS_SCENE);
	}
	/**
	 * Loads the HomeScene.
	 */
	@FXML
	public void loadHomeScene() {
		selectedItem = null;
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
	/**
	 * Loads the ProductReview scene.
	 */
	@FXML
	public void loadProductReviews() {
		ViewNavigator.loadScene(selectedItem.getWatch().getName().concat("'s Review"), ViewNavigator.PRODUCT_REVIEW_SCENE);
	}
	/**
	 * Initializes all the labels.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectedItem = controller.getSelectedListing();
		Watch watch = selectedItem.getWatch();
		String email =selectedItem.getUser().getEmail();
		brandLabel.setText(watch.getBrand());
		nameLabel.setText(watch.getName());
		referenceLabel.setText(watch.getReference());
		quantityLabel.setText(String.valueOf(selectedItem.getQuantity()));
		userLabel.setText(email.substring(0, email.indexOf("@")));
		colorLabel.setText(watch.getDialColor());
		indexesLabel.setText(watch.getDialIndex());
		handsLabel.setText(watch.getDialHands());
		movementLabel.setText(watch.getMovement());
		materialLabel.setText(watch.getCaseMaterial());
		glassLabel.setText(watch.getCaseGlass());
		backLabel.setText(watch.getCaseBackType());
		shapeLabel.setText(watch.getCaseShape());
		diameterLabel.setText(String.valueOf(watch.getCaseDiameter()));
		heightLabel.setText(String.valueOf(watch.getCaseHeight()));
		waterResistenceLabel.setText(String.valueOf(watch.getCaseWaterResistance()));
		
	}
}
