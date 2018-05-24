package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class ViewCompare implements Initializable {
	@FXML
	private Label mBrand1;
	@FXML
	private Label mReferenceID1;
	@FXML
	private Label mName1;
	@FXML
	private Label mCaseMaterial1;
	@FXML
	private Label mGlassType1;
	@FXML
	private Label mShape1;
	@FXML
	private Label mDiameter1;
	@FXML
	private Label mHeight1;
	@FXML
	private Label mWaterResistance1;
	@FXML
	private Label mDialColor1;
	@FXML
	private Label mDialIndexes1;
	@FXML
	private Label mDialHands1;
	@FXML
	private Label mModementType1;
	@FXML
	private Label mPrice;
	@FXML
	private Label mBrand2;
	@FXML
	private Label mReferenceID2;
	@FXML
	private Label mName2;
	@FXML
	private Label mCaseMaterial2;
	@FXML
	private Label mGlassType2;
	@FXML
	private Label mShape2;
	@FXML
	private Label mDiameter2;
	@FXML
	private Label mHeight2;
	@FXML
	private Label mWaterResistance2;
	@FXML
	private Label mDialColor2;
	@FXML
	private Label mDialIndexes2;
	@FXML
	private Label mDialHands2;
	@FXML
	private Label mModementType2;
	@FXML
	private Label mPrice2;
	@FXML
	private Label mBrand3;
	@FXML
	private Label mReferenceID3;
	@FXML
	private Label mName3;
	@FXML
	private Label mCaseMaterial3;
	@FXML
	private Label mGlassType3;
	@FXML
	private Label mShape3;
	@FXML
	private Label mDiameter3;
	@FXML
	private Label mHeight3;
	@FXML
	private Label mWaterResistance3;
	@FXML
	private Label mDialColor3;
	@FXML
	private Label mDialIndexes3;
	@FXML
	private Label mDialHands3;
	@FXML
	private Label mModementType3;
	@FXML
	private Label mPrice3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Read relationship database (compareDB)
		// Take first three watches
		// Clear all fields first
		// If exists, fill the fields
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void goToHomeScene(ActionEvent event) {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
	}
}
