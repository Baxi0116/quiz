package com.baxi.quiz.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.quiz.dao.TeamDao;
import com.baxi.quiz.model.Team;
import com.baxi.quiz.util.EntityManagerProvider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeamSelectController {

	private static Logger logger = LoggerFactory.getLogger(TeamSelectController.class);
	
	private TeamDao dao = new TeamDao(EntityManagerProvider.provideEntityManager());
	
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
				
				setupTeams();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuizBoard.fxml"));
				root = loader.load();
				loader.<QuizController> getController();
				stage = (Stage) nextButton.getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();		
				
			} else {
				logger.warn("Empty team textfield(s)...");
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Hiba");
				alert.setHeaderText("Csapatnév nem lehet üres.");
				alert.setContentText("Töltsön ki minden mezőt!");

				alert.showAndWait();
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
		
		if(firstTeamTextField.getText() == null || firstTeamTextField.getText().length() == 0){
			return false;
		}
		if(secondTeamTextField.getText() == null || secondTeamTextField.getText().length() == 0){
			return false;
		}
		if(thirdTeamTextField.getText() == null || thirdTeamTextField.getText().length() == 0){
			return false;
		}
		
		return true;
	}
	
	public void setupTeams(){
		
		Team firstTeam = new Team();
		Team secondTeam = new Team();
		Team thirdTeam = new Team();
		
		firstTeam.setName(firstTeamTextField.getText());
		secondTeam.setName(secondTeamTextField.getText());
		thirdTeam.setName(thirdTeamTextField.getText());
		
		firstTeam.setScore(1000);
		secondTeam.setScore(1000);
		thirdTeam.setScore(1000);
		
		dao.persist(firstTeam);
		dao.persist(secondTeam);
		dao.persist(thirdTeam);
		
	}
	
}
