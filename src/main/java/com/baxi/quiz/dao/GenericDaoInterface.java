package com.baxi.quiz.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoInterface <T, Id extends Serializable>{


	public void persist(T entity);
	
	public void update(T entity);
	
	public T findById(Id id);

	public void remove(T entity);

	public List<T> findAll();

	public void removeAll();
	
}
