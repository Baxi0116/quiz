package com.baxi.quiz.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartMenuController {
	
	private static Logger logger = LoggerFactory.getLogger(StartMenuController.class);
	
	@FXML
	Button newGameButton;
	
	@FXML
	Button exitButton;
	
	@FXML
	void handleNewGameButton(){
		Stage stage;
		Parent root;
		
		try{
			logger.debug("Handling New Game button action...");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizBoard.fxml"));
			root = loader.load();
			loader.<QuizController> getController();
			stage = (Stage) newGameButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to New Game button interaction");
			e.printStackTrace();
		}
	}
	
	@FXML
	void handleExitButton(){
		logger.warn("Exiting...");
		System.exit(0);	
	}
	
}
