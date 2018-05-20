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
	@FXML
	public void viewItemDetails(ActionEvent event) {
		Listing selected = sellerListView.getSelectionModel().getSelectedItem();
		controller.setSelectedListing(selected);
		ViewNavigator.loadScene(selected.getWatch().getName(), ViewNavigator.VIEW_ITEM_DETAILS_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public void addReview(ActionEvent event) {
		String email = seller.getEmail();
		ViewNavigator.loadScene(email.substring(0, email.indexOf("@")).concat("'s Seller Profile"), ViewNavigator.SELLER_REVIEWS_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public void viewSellersListings(ActionEvent event) {
		detailsGridpane.setVisible(false);
		sellerListView.setVisible(true);
		viewReviewsButton.setDisable(false);
		viewListingsButton.setDisable(true);
	}
	// Event Listener on Button.onAction
	@FXML
	public void loadReviewScene(ActionEvent event) {
		detailsGridpane.setVisible(true);
		sellerListView.setVisible(false);
		viewReviewsButton.setDisable(true);
		viewListingsButton.setDisable(false);
	}
	@FXML
	public void loadHomeScene(ActionEvent event) {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
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
