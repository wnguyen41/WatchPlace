package edu.orangecoastcollege.watchplace.view;

import edu.orangecoastcollege.watchplace.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpScene {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private Label nameErrorLabel;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Label confirmPasswordErrorLabel;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField emailTF;
	@FXML
	private Button createNewAcctButton;
	@FXML
	private PasswordField confirmPassPF;
	@FXML
	private PasswordField passwordPF;

	// Event Listener on Button[#createNewAcctButton].onAction
	@FXML
	public boolean createAccount(ActionEvent event) {
		String fullName = nameTF.getText();
		String email = emailTF.getText();
		String password = passwordPF.getText();
		String confirmPass = confirmPassPF.getText();
		
		nameErrorLabel.setVisible(fullName.isEmpty());
		emailErrorLabel.setVisible(email.isEmpty());
		passwordErrorLabel.setText("Field required.");
		confirmPasswordErrorLabel.setText("Field required.");
		passwordErrorLabel.setVisible(password.isEmpty());
		confirmPasswordErrorLabel.setVisible(confirmPass.isEmpty());
		
		if(!password.equals(confirmPass)) 
		{
			passwordErrorLabel.setVisible(false);
			confirmPasswordErrorLabel.setVisible(false);
			passwordErrorLabel.setText("Passwords don't match");
			confirmPasswordErrorLabel.setText("Passwords don't match");
		}
		
		if(emailErrorLabel.isVisible() || passwordErrorLabel.isVisible()||nameErrorLabel.isVisible()|| confirmPasswordErrorLabel.isVisible()) return false;
		
		String result = controller.signUpUser(fullName, email, password);
        if(result.equalsIgnoreCase("SUCCESS"))
        {
            signInScene();
            return true;
        }
        return false;

	}
	// Event Listener on Label.onMouseClicked
	@FXML
	public void signInScene() {
		ViewNavigator.loadScene("Welcome to WatchPlace", ViewNavigator.SIGN_IN_SCENE);
	}
}
