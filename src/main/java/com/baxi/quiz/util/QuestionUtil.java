package com.baxi.quiz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.baxi.quiz.model.Question;

public class QuestionUtil {

	private InputStream fileName;
	
	public QuestionUtil() {
		this.fileName = QuestionUtil.class.getResourceAsStream("/kerdessor.txt");
	}
	
	@SuppressWarnings("finally")
	public List<Question> readQuestion() {
		BufferedReader bfr;
		List<Question> questionList = new ArrayList<Question>();
		try {
		
			bfr = new BufferedReader(new InputStreamReader(fileName));
		
			String currentLine;
		
	
			while ((currentLine = bfr.readLine()) != null) {
				System.out.println(currentLine);
				String[] questionParts = currentLine.split(";");
				Question question = new Question();
				question.setQuestionText(questionParts[0]);
				question.setAnswerA(questionParts[1]);
				question.setAnswerB(questionParts[2]);
				question.setAnswerC(questionParts[3]);
				question.setCorrectAnswer(questionParts[4]);
				System.out.println(question);
				questionList.add(question);
			}
			return questionList;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return questionList;
		}
	}
}
