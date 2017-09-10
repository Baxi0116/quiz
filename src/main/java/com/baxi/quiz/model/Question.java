package com.baxi.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "QUESTION_ID")
	private int id;
	
	@Column(name = "QUESTION_TEXT", nullable=false)
	private String questionText;
	
	@Column(name = "ANSWER_A", nullable=false)
	private String answerA;
	
	@Column(name = "ANSWER_B", nullable=false)
	private String answerB;
	
	@Column(name = "ANSWER_C", nullable=false)
	private String answerC;
	
	@Column(name = "CORRECT_ANSWER", nullable=false)
	private String correctAnswer;
	
	@Column(name = "WAS_ASKED", nullable=false)
	private boolean asked;

	public Question() {
		this.questionText = "";
		this.answerA = "";
		this.answerB = "";
		this.answerC = "";
		this.correctAnswer = "";
		this.asked = false;
	}
	
	
	public Question(String questionText, String answerA, String answerB, String answerC, String correctAnswer) {
		this.questionText = questionText;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.correctAnswer = correctAnswer;
		this.asked = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public boolean isAsked() {
		return asked;
	}

	public void setAsked(boolean wasAsked) {
		this.asked = wasAsked;
	}

	@Override
	public String toString() {
		return "Question [questionText=" + questionText + ", answerA=" + answerA + ", answerB=" + answerB
				+ ", answerC=" + answerC + "]";
	}
	
	
}
