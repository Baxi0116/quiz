package com.baxi.quiz.view;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.quiz.dao.QuestionDao;
import com.baxi.quiz.dao.TeamDao;
import com.baxi.quiz.model.Question;
import com.baxi.quiz.model.Team;
import com.baxi.quiz.util.EntityManagerProvider;
import com.baxi.quiz.util.QuestionUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuizController {

	private static Logger logger = LoggerFactory.getLogger(QuizController.class);
	
	private QuestionDao questionDao;
	
	private TeamDao teamDao;
	
	private List<Question> allQuestions;
	
	private List<Team> allTeams;
	
	private Question currentQuestion;
	
	@FXML
	private Label firstTeamLabel;
	
	@FXML
	private Label secondTeamLabel;
	
	@FXML
	private Label thirdTeamLabel;
	
	@FXML
	private Label timeLabel;
	
	@FXML
	private Label questionAreaLabel;
	
	@FXML
	private Label answerALabel;
	
	@FXML
	private Label answerBLabel;
	
	@FXML
	private Label answerCLabel;
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button newQuestionButton;
	
	@FXML
	private Button checkAnswerButton;
	
	@FXML
	private Button battleResultButton;
	
	@FXML
	private void initialize(){
		
		questionDao = new QuestionDao(EntityManagerProvider.provideEntityManager());
		teamDao = new TeamDao(EntityManagerProvider.provideEntityManager());
		questionAreaLabel.setWrapText(true);
	}
	
	@FXML
	private void handleStartButton(){
		allQuestions = questionDao.findAllNotAskedQuestions();
		allTeams = teamDao.findAll();
		
		for(Team team : allTeams){
			logger.debug(team.getName());
		}
		
		Question question = QuestionUtil.chooseRandomQuestion(allQuestions);
		question.setAsked(true);
		currentQuestion = question;
		questionAreaLabel.setText(question.getQuestionText());
		answerALabel.setText("a) " + question.getAnswerA());
		answerBLabel.setText("b) " + question.getAnswerB());
		answerCLabel.setText("c) " + question.getAnswerC());
		
		Team team1 = allTeams.get(0);
		Team team2 = allTeams.get(1);
		Team team3 = allTeams.get(2);
		
		logger.debug(team1.getName());
		logger.debug(team2.getName());
		logger.debug(team3.getName());
		
		firstTeamLabel.setText(team1.getName() + "\n" + team1.getScore() + " pont");
		secondTeamLabel.setText(team2.getName() + "\n" + team2.getScore() + " pont");
		thirdTeamLabel.setText(team3.getName() + "\n" + team3.getScore() + " pont");
		
		startButton.setDisable(true);
		checkAnswerButton.setDisable(false);
	}
	
	@FXML
	private void handleNewQuestionButton(){
		logger.debug("handleNewQuestionButton call...");
		allQuestions.clear();
		allQuestions = questionDao.findAllNotAskedQuestions();
		logger.debug("Number of questions in list: " + allQuestions.size());
		
		if(allQuestions.size() != 0){
			Question question = QuestionUtil.chooseRandomQuestion(allQuestions);
			question.setAsked(true);
			currentQuestion = question;
			questionAreaLabel.setText(question.getQuestionText());
			answerALabel.setText("a) " + question.getAnswerA());
			answerBLabel.setText("b) " + question.getAnswerB());
			answerCLabel.setText("c) " + question.getAnswerC());
			
			newQuestionButton.setDisable(true);
			checkAnswerButton.setDisable(false);
		}else{
			questionAreaLabel.setText("Elfogytak a kérdések :(");
		}

	}
	
	@FXML
	private void handleCheckAnswerButton(){
		logger.debug("handleCheckAnswerButton call...");
		
		switch(currentQuestion.getCorrectAnswer().toLowerCase()){
		case "a":
			answerBLabel.setText("");
			answerCLabel.setText("");
			break;
		case "b":
			answerALabel.setText("");
			answerCLabel.setText("");
			break;
		case "c":
			answerALabel.setText("");
			answerBLabel.setText("");
			break;
		default:
			logger.debug("no answer found");
		}

		battleResultButton.setDisable(false);
		checkAnswerButton.setDisable(true);


	}
	
	@FXML
	private void handleBattleResultButton(){
		
		if(showBattleResultDialog()){
			allTeams.clear();
			allTeams = teamDao.findAll();
			
			firstTeamLabel.setText(allTeams.get(0).getName() + "\n" + allTeams.get(0).getScore() + " pont");
			secondTeamLabel.setText(allTeams.get(1).getName() + "\n" + allTeams.get(1).getScore() + " pont");
			thirdTeamLabel.setText(allTeams.get(2).getName() + "\n" + allTeams.get(2).getScore() + " pont");
			
			battleResultButton.setDisable(true);
			newQuestionButton.setDisable(false);
		}
		
	}
	
	 public boolean showBattleResultDialog() {
		 try {
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(QuizController.class.getResource("/view/BattleResultDialog.fxml"));
			 AnchorPane page = (AnchorPane) loader.load();

			 Stage dialogStage = new Stage();
			 dialogStage.setTitle("Szerkesztés");
			 dialogStage.initModality(Modality.WINDOW_MODAL);
			 Scene scene = new Scene(page);
			 dialogStage.setScene(scene);

			 BattleResultDialogController controller = loader.getController();
			 controller.setDialogStage(dialogStage);

			 dialogStage.showAndWait();

			 return controller.isNextClicked();
		 } catch (IOException e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	
}
