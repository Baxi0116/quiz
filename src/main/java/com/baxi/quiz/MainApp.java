package com.baxi.quiz;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.quiz.dao.QuestionDao;
import com.baxi.quiz.model.Question;
import com.baxi.quiz.util.EntityManagerProvider;
import com.baxi.quiz.util.QuestionUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application{

	private static Logger logger = LoggerFactory.getLogger(MainApp.class);
	
	private Stage primaryStage;
	
	private BorderPane rootLayout;
	
	private QuestionDao dao;
	
	@Override
	public void stop() throws Exception {
		dao.removeAll();
		EntityManagerProvider.closeConnection();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Autómentes vetélkedő...");
		
		dao = new QuestionDao(EntityManagerProvider.provideEntityManager());
		
		QuestionUtil parser = new QuestionUtil();
		
		for(Question question : parser.readQuestion()) {
			dao.persist(question);
		}
		
		//parser.readQuestion();
		initRootLayout();
		showStartMenu();
	}
	public void initRootLayout(){
		try{
			logger.debug("initRootLayout method from MainApp...");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
    public void showStartMenu() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/StartMenu.fxml"));
            AnchorPane startMenu = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public static void main(String[] args){
		launch(args);
		logger.debug("MainApp initial main method...");
	}
	
}