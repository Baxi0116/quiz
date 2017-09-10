package com.baxi.quiz.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.quiz.model.Question;

public class QuestionDao implements GenericDaoInterface<Question, Integer>{

	private EntityManager entityManager;
	
	public QuestionDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Question entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();	
	}

	@Override
	public void update(Question entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public Question findById(Integer id) {
		Question question = entityManager.find(Question.class, id);
		return question;
	}

	@Override
	public void remove(Question entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Question> findAll() {
		TypedQuery<Question> query = entityManager.createQuery("SELECT q FROM com.baxi.quiz.model.Question q", Question.class);
		return query.getResultList();
	}

	@Override
	public void removeAll() {
		List<Question> questionList = findAll();
		for(Question question : questionList){
			remove(question);
		}
	}
	
	public List<Question> findAllNotAskedQuestions(){	
		List<Question> questions = findAll();
		return questions.stream()
						.filter(question -> !question.isAsked())
						.collect(Collectors.toList());
	}

}
