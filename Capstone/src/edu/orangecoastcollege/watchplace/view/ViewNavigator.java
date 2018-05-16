package edu.orangecoastcollege.watchplace.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator {
    //TODO add file name constants
	public static final String SIGN_UP_SCENE = "SignUpScene.fxml";
	public static final String SIGN_IN_SCENE = "SignInScene.fxml";
	public static final String HOME_SCENE = "HomeScene.fxml";
	public static final String ACCOUNT_OVERVIEW_SCENE = "AccountOverview.fxml";
	public static final String VIEW_ITEM_DETAILS_SCENE = "ViewItemDetailsScene.fxml";

	public static Stage mainStage;

	public static void setStage(Stage stage) {
		mainStage = stage;
	}

	public static void loadScene(String title, String sceneFXML) {

		try {
			mainStage.setTitle(title);
			Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
			mainStage.setScene(scene);
			mainStage.show();
		} catch (IOException e) {
			System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}

}
