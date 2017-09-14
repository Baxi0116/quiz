package com.baxi.quiz.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeamSelectController {

	private static Logger logger = LoggerFactory.getLogger(TeamSelectController.class);
	
	@FXML
	private TextField firstTeamTextField;
	
	@FXML
	private TextField secondTeamTextField;
	
	@FXML
	private TextField thirdTeamTextField;
	
	@FXML
	private Button nextButton;

	@FXML
	private Button backButton;
	
	@FXML
	public void handleNextButton() {
		Stage stage;
		Parent root;
		
		try{
			logger.debug("Handling Next button action...");
			
			if(checkIfTeamsArePresent()) {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizBoard.fxml"));
				root = loader.load();
				loader.<QuizController> getController();
				stage = (Stage) nextButton.getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();		
				
			} else {
				//TODO: alert  dialog 
				logger.warn("Empty team textfield(s)...");
			}

			
		}catch(IOException e){
			logger.error("Can't respond to next button interaction");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleBackButton() {
		Stage stage;
		Parent root;
		
		try{
			logger.debug("Handling Back button action...");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StartMenu.fxml"));
			root = loader.load();
			loader.<StartMenuController> getController();
			stage = (Stage) backButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to back button interaction");
			e.printStackTrace();
		}
	}
	
	public boolean checkIfTeamsArePresent() {
		
		return false;
	}
	
}
