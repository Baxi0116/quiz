package com.baxi.quiz.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.quiz.dao.TeamDao;
import com.baxi.quiz.model.Team;
import com.baxi.quiz.util.EntityManagerProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BattleResultDialogController {
	
	private static Logger logger = LoggerFactory.getLogger(BattleResultDialogController.class);

	@FXML
	private TextField firstTeamTextField;
	
	@FXML
	private TextField secondTeamTextField;
	
	@FXML
	private TextField thirdTeamTextField;
	
	@FXML
	private Label firstTeamLabel;
	
	@FXML
	private Label secondTeamLabel;
	
	@FXML
	private Label thirdTeamLabel;
	
	@FXML
	private Button nextButton;
	
	private Stage dialogStage;
	private boolean nextClicked = false;
	private TeamDao teamDao;
	private List<Team> allTeams;
	
	public void setDialogStage(Stage stage){
		this.dialogStage = stage;
	}
	
	public boolean isNextClicked(){
		return nextClicked;
	}
	
	@FXML
	private void initialize(){
		teamDao = new TeamDao(EntityManagerProvider.provideEntityManager());
		allTeams = teamDao.findAll();
		
		logger.debug(allTeams.get(0).getName());
		logger.debug(allTeams.get(1).getName());
		logger.debug(allTeams.get(2).getName());
		
		firstTeamLabel.setText(allTeams.get(0).getName());
		secondTeamLabel.setText(allTeams.get(1).getName());
		thirdTeamLabel.setText(allTeams.get(2).getName());
		
		firstTeamTextField.setText("0");
		secondTeamTextField.setText("0");
		thirdTeamTextField.setText("0");
		
		
	}
	
	public void setTeamList(List<Team> teamList){
		this.allTeams = teamList;
	}
	
	@FXML
	public void handleNextButton(){
		
		if(isInputValid()){
			try{
				
				Team team1 = allTeams.get(0);
				Team team2 = allTeams.get(1);
				Team team3 = allTeams.get(2);
				
				int team1Score = team1.getScore();
				team1.setScore(Integer.parseInt(firstTeamTextField.getText())+ team1Score);
				
				int team2Score = team2.getScore();
				team2.setScore(Integer.parseInt(secondTeamTextField.getText())+ team2Score);
				
				int team3Score = team3.getScore();
				team3.setScore(Integer.parseInt(thirdTeamTextField.getText())+ team3Score);
				
				teamDao.update(team1);
				teamDao.update(team2);
				teamDao.update(team3);
				
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		
		
		nextClicked = true;
		dialogStage.close();
	}
	
	private boolean isInputValid(){
		
		if(firstTeamLabel.getText() == null || firstTeamLabel.getText().length() == 0 ){
			return false;
		}
		if(secondTeamLabel.getText() == null || secondTeamLabel.getText().length() == 0 ){
			return false;
		}
		if(thirdTeamLabel.getText() == null || thirdTeamLabel.getText().length() == 0 ){
			return false;
		}
		
		return true;
	}
}
	

