package com.baxi.quiz.view;

import java.util.List;

import com.baxi.quiz.dao.QuestionDao;
import com.baxi.quiz.model.Question;
import com.baxi.quiz.util.EntityManagerProvider;
import com.baxi.quiz.util.QuestionUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class QuizController {

	private QuestionDao dao = new QuestionDao(EntityManagerProvider.provideEntityManager());
	
	private List<Question> allQuestions;
	
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
	private Label answerAreaLabel;
	
	@FXML
	private Button startButton;
	
	@FXML
	private void handleStartButton(){
		allQuestions = dao.findAll();
		Question question = QuestionUtil.chooseRandomQuestion(allQuestions);
		questionAreaLabel.setText(question.getQuestionText());
	}
	
}
