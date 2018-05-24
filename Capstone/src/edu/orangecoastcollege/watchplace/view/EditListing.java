package edu.orangecoastcollege.watchplace.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.watchplace.controller.Controller;
import edu.orangecoastcollege.watchplace.model.Listing;
import edu.orangecoastcollege.watchplace.model.Watch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class EditListing implements Initializable {
	
	Controller controller = Controller.getInstance();
	
	@FXML
	private TextField mReferenceTF;
	@FXML
	private TextField mBrandTF;
	@FXML
	private TextField mNameTF;
	@FXML
	private ComboBox<String> mMovementCB;
	@FXML
	private TextField mMaterialTF;
	@FXML
	private ComboBox<String> mGlassCB;
	@FXML
	private ComboBox<String> mBackTypeCB;
	@FXML
	private TextField mShapeTF;
	@FXML
	private TextField mDiameterTF;
	@FXML
	private TextField mHeightTF;
	@FXML
	private TextField mWaterResistanceTF;
	@FXML
	private TextField mDialColorTF;
	@FXML
	private ComboBox<String> mDialIndexCB;
	@FXML
	private TextField mDialHandsTF;
	@FXML
	private TextField mPriceTF;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mBackTypeCB.setItems(controller.getDistinctBackTypes());
		mMovementCB.setItems(controller.getDistinctMovements());
		mDialIndexCB.setItems(controller.getDistinctIndex());
		mGlassCB.setItems(controller.getDistinctGlass());
		
		// Use Watch object to set the fields
	}
	// Event Listener on Button.onAction
	@FXML
	public void saveChanges(ActionEvent event) {
		// Use fields to change the Watch object
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
		if (controller.searchByRef(refID).size() != 0)
		{
			int index = 0;
			ObservableList<Listing> theListing = controller.searchByRef(refID);
			Watch watchID = theListing.get(0).getWatch();
			
			mBrandTF.setText(watchID.getBrand());
			mNameTF.setText(watchID.getName());
			mMaterialTF.setText(watchID.getCaseMaterial());
			
			switch (watchID.getCaseGlass())
			{
			case "Sapphire" : index = 0;
			break;
			case "Mineral" : index = 1;
			break;
			case "Acrylic" : index = 2;
			break;
			}
			
			mGlassCB.getSelectionModel().clearAndSelect(index);;
			
			switch (watchID.getCaseBackType())
			{
			case "Opened" : index = 0;
			break;
			case "Closed" : index = 1;
			break;
			case "Skeleton" : index = 2;
			break;
			}
			
			mBackTypeCB.getSelectionModel().clearAndSelect(index);
			mShapeTF.setText(String.valueOf(watchID.getCaseShape()));
			mDiameterTF.setText(String.valueOf(watchID.getCaseDiameter()));
			mHeightTF.setText(String.valueOf(watchID.getCaseHeight()));
			mWaterResistanceTF.setText(String.valueOf(watchID.getCaseWaterResistance()));
			mDialColorTF.setText(watchID.getDialColor());
			mDialIndexCB.getSelectionModel().getSelectedItem();
			mDialHandsTF.setText(watchID.getDialHands());
			
			switch (watchID.getMovement())
			{
			case "Manual" : index = 0;
			break;
			case "Automatic" : index = 1;
			break;
			case "Quartz" : index = 2;
			break;
			}
			
			mMovementCB.getSelectionModel().clearAndSelect(index);
			
			switch (watchID.getDialIndex()) {
			case "Arabic & Stick" : index = 0;
			break;
			case "Arabic" : index = 1;
			break;
			case "Stick" : index = 2;
			break;
			case "Roman & Stick" : index = 3;
			break;
			case "Roman" : index = 4;
			break;
			case "California" : index = 5;
			break;
			}
			
			mDialIndexCB.getSelectionModel().clearAndSelect(index);
			
			mPriceTF.setText(String.valueOf(watchID.getPrice()));
		}
	}
}
