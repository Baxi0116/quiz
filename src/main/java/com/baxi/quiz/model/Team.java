package com.baxi.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TEAM_ID")
	private int id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "SCORE")
	private int score;

	public Team(String name) {
		this.name = name;
	}
	
	public Team(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", score=" + score + "]";
	}
	
	
	
	
	
	
}
