package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

/*
 * Add Listing scene
 * @author James Kim
 */

public class AddListing implements Initializable {
	
	Controller controller = Controller.getInstance();
	
	@FXML
	private TextField mBrandTF;
	@FXML
	private TextField mNameTF;
	@FXML
	private ComboBox<String> mMovementCB;
	@FXML
	private TextField mMaterialTF;
	@FXML
	private TextField mShapeTF;
	@FXML
	private TextField mDiameterTF;
	@FXML
	private TextField mHeightTF;
	@FXML
	private TextField mPriceTF;
	@FXML
	private ComboBox<String> mDialIndexCB;
	@FXML
	private TextField mDialHandsTF;
	@FXML
	private TextField mDialColorTF;
	@FXML
	private ComboBox<String> mGlassCB;
	@FXML
	private ComboBox<String> mBackTypeCB;
	@FXML
	private TextField mReferenceTF;
	@FXML
	private TextField mWaterResistanceTF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mBackTypeCB.setItems(controller.getDistinctBackTypes());
		mMovementCB.setItems(controller.getDistinctMovements());
		mDialIndexCB.setItems(controller.getDistinctIndex());
		mGlassCB.setItems(controller.getDistinctGlass());
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void addListing(ActionEvent event) {
		String[] args = new String[15];
		args[0] = mReferenceTF.getText();
		args[1] = mBrandTF.getText();
		args[2] = mNameTF.getText();
		args[3] = mMaterialTF.getText();
		args[4] = mGlassCB.getSelectionModel().getSelectedItem();
		args[5] = mBackTypeCB.getSelectionModel().getSelectedItem();
		args[6] = mShapeTF.getText();
		args[7] = mDiameterTF.getText();
		args[8] = mHeightTF.getText();
		args[9] = mWaterResistanceTF.getText();
		args[10] = mDialColorTF.getText();
		args[11] = mDialIndexCB.getSelectionModel().getSelectedItem();
		args[12] = mDialHandsTF.getText();
		args[13] = mMovementCB.getSelectionModel().getSelectedItem();
		args[14] = mPriceTF.getText();
		
		int quantity = 1;
		
		controller.createListing(args, quantity);
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToHomeScene(ActionEvent event) {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public void checkMatching(ActionEvent event) {
		String refID = mReferenceTF.getText();
		try {
			ObservableList<Listing> theListing = controller.searchByRef(refID);
			Watch watchID = theListing.get(0).getWatch();
			
			mBrandTF.setText(watchID.getBrand());
			mNameTF.setText(watchID.getName());
			mMaterialTF.getText();
			// mGlassCB.getSelectionModel().getSelectedItem();
			// mBackTypeCB.getSelectionModel().getSelectedItem();
			mShapeTF.setText(String.valueOf(watchID.getCaseShape()));
			mDiameterTF.setText(String.valueOf(watchID.getCaseDiameter()));
			mHeightTF.setText(String.valueOf(watchID.getCaseHeight()));
			mWaterResistanceTF.setText(String.valueOf(watchID.getCaseWaterResistance()));
			mDialColorTF.setText(watchID.getDialColor());
			mDialIndexCB.getSelectionModel().getSelectedItem();
			mDialHandsTF.setText(watchID.getDialHands());
			// Something like this
			// mMovementCB.getSelectionModel();//.clearAndSelect(watchID.getMovement());
			mPriceTF.setText(String.valueOf(watchID.getPrice()));
		}
		catch(SQLException e) { e.printStackTrace(); }
		
		}
	}
}
