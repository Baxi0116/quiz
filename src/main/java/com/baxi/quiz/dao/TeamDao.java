package com.baxi.quiz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.quiz.model.Team;

public class TeamDao implements GenericDaoInterface<Team, Integer> {

	private EntityManager entityManager;
	
	public TeamDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(Team entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Team entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public Team findById(Integer id) {
		Team team = entityManager.find(Team.class, id);
		return team;
	}

	@Override
	public void remove(Team entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Team> findAll() {
		TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM com.baxi.quiz.model.Team t", Team.class);
		return query.getResultList();
	}

	@Override
	public void removeAll() {
		List<Team> teamList = findAll();
		for(Team team : teamList){
			remove(team);
		}
	}

}
