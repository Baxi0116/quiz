package com.baxi.quiz.view;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.quiz.dao.TeamDao;
import com.baxi.quiz.model.Team;
import com.baxi.quiz.util.EntityManagerProvider;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EndGameMenuController {

	private static Logger logger = LoggerFactory.getLogger(EndGameMenuController.class);
	
	private TeamDao teamDao;
	
	List<Team> allTeams;
	
	@FXML
	private Label firstPlaceLabel;
	
	@FXML
	private Label secondPlaceLabel;
	
	@FXML
	private Label thirdPlaceLabel;
	
	@FXML
	private ImageView firstPlaceIcon;
	
	@FXML
	private ImageView secondPlaceIcon;
	
	@FXML
	private ImageView thirdPlaceIcon;
	
	@FXML
	private Label bigFirstPlaceLabel;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private void initialize(){
		teamDao = new TeamDao(EntityManagerProvider.provideEntityManager());
		allTeams = teamDao.findAll();
		showResults();
	}
	
	@FXML
	private void handleExitButton(){
		Platform.exit();
	}
	
	private void showResults(){
		logger.debug("showing results...");
		
		orderByScore(allTeams);
		
		firstPlaceLabel.setText(allTeams.get(0).getName() + "\n" + allTeams.get(0).getScore() + " pont");
		secondPlaceLabel.setText(allTeams.get(1).getName() + "\n" + allTeams.get(1).getScore() + " pont");
		thirdPlaceLabel.setText(allTeams.get(2).getName() + "\n" + allTeams.get(2).getScore() + " pont");
		bigFirstPlaceLabel.setText("A játék győztese: \n" + allTeams.get(0).getName() + "\nGratulálunk!");
		
		
	}
	
	private void orderByScore(List<Team> list){
		
		list.sort(new Comparator<Team>(){

			@Override
			public int compare(Team o1, Team o2) {
				return -(o1.getScore() - o2.getScore());
			}
			
		});
		
	}
	
}
