package edu.orangecoastcollege.watchplace.view;


import edu.orangecoastcollege.watchplace.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignInScene {
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private Label signInErrorLabel;
	@FXML
	private PasswordField passwordPF;
	@FXML
	private TextField emailTF;

	// Event Listener on Label.onMouseClicked
	@FXML
	public void signUpScene(MouseEvent event) {
		ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public boolean signIn(ActionEvent event) {
		String email = emailTF.getText();
	    String password =  passwordPF.getText();
	    String result = controller.signInUser(email, password);
        if(result.equalsIgnoreCase("SUCCESS"))
        {
            signInErrorLabel.setVisible(false);
            ViewNavigator.loadScene("Video Game List", ViewNavigator.HOME_SCENE);
            return true;
        }
        signInErrorLabel.setText(result);
        signInErrorLabel.setVisible(true);
        return false;
	}
}
