package edu.orangecoastcollege.watchplace.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
/**
 * The SellerDetailsScene displays information regarding the user who created a selected listing.
 * @author Winston Nguyen
 *
 */
public class SellerDetailsScene implements Initializable{
	private static Controller controller = Controller.getInstance();
	@FXML
	private Label userLabel;
	@FXML
	private GridPane detailsGridpane;
	@FXML
	private Label rateingScoreLabel;
	@FXML
	private Label numberOfListingLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private ListView<Listing> sellerListView;
	@FXML
	private Button viewListingsButton;
	@FXML
	private Button viewReviewsButton;
	
	private User seller;
	// Event Listener on Button.onAction
	/**
	 * Loads the details of the selected item.
	 */
	@FXML
	public void viewItemDetails() {
		Listing selected = sellerListView.getSelectionModel().getSelectedItem();
		controller.setSelectedListing(selected);
		ViewNavigator.loadScene(selected.getWatch().getName(), ViewNavigator.VIEW_ITEM_DETAILS_SCENE);
	}
	/**
	 * Loads the add review scene.
	 */
	@FXML
	public void addReview() {
		String email = seller.getEmail();
		ViewNavigator.loadScene(email.substring(0, email.indexOf("@")).concat("'s Seller Profile"), ViewNavigator.SELLER_REVIEWS_SCENE);
	}
	/**
	 * Hides the list view and displays the user's rating, listing count, and description.
	 */
	@FXML
	public void viewSellersListings() {
		detailsGridpane.setVisible(false);
		sellerListView.setVisible(true);
		viewReviewsButton.setDisable(false);
		viewListingsButton.setDisable(true);
	}
	/**
	 * Loads the list view and hides the user's rating, listing count, and description.
	 */
	@FXML
	public void loadReviewScene() {
		detailsGridpane.setVisible(true);
		sellerListView.setVisible(false);
		viewReviewsButton.setDisable(true);
		viewListingsButton.setDisable(false);
	}
	/**
	 * Loads the HomeScene.
	 */
	@FXML
	public void loadHomeScene() {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
	/**
	 * Initializes all nodes on the scene.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		seller = controller.getSelectedListingSeller();
		sellerListView.setItems(controller.getSellersListings());
		String email = seller.getEmail();
		userLabel.setText(email.substring(0, email.indexOf("@")));
		numberOfListingLabel.setText(String.valueOf(controller.getSellersListings().size()));
		//TODO Allow user to set up description
		descriptionLabel.setText("No description has been set up.");
		rateingScoreLabel.setText(String.valueOf(controller.getSellersRating()));
		viewReviewsButton.setDisable(true);
		
	}
}
