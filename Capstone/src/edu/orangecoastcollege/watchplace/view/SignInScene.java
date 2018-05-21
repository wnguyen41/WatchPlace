package edu.orangecoastcollege.watchplace.view;


import edu.orangecoastcollege.watchplace.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * JavaFX scene for users to sign in.
 * @author Winston Nguyen
 *
 */
public class SignInScene {
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private Label signInErrorLabel;
	@FXML
	private PasswordField passwordPF;
	@FXML
	private TextField emailTF;

	/**
	 * Loads the sign up scene.
	 */
	@FXML
	public void signUpScene() {
		ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
	}
	/**
	 * Signs in the user.
	 * @return return true if user is able to sign in, otherwise false.
	 */
	@FXML
	public boolean signIn() {
		String email = emailTF.getText();
	    String password =  passwordPF.getText();
	    
	    if(email.isEmpty() || password.isEmpty())
	    {
	    	signInErrorLabel.setText("Fields missing.");
	        signInErrorLabel.setVisible(true);
	        return false;
	    }
	    
	    String result = controller.signInUser(email, password);
        if(result.equalsIgnoreCase("SUCCESS"))
        {
            signInErrorLabel.setVisible(false);
            ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.HOME_SCENE);
            return true;
        }
        signInErrorLabel.setText(result);
        signInErrorLabel.setVisible(true);
        return false;
	}
}
