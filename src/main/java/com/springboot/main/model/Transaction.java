package com.springboot.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double pointsGiven;
	private double oldPoints;
	private double newPoints;
	private String comments;

	@CreationTimestamp

	@Column(name = "transaction_date", nullable = false, updatable = false)
	private Date date;

	@ManyToOne
	private Employee employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPointsGiven() {
		return pointsGiven;
	}

	public void setPointsGiven(double pointsGiven) {
		this.pointsGiven = pointsGiven;
	}

	public double getOldPoints() {
		return oldPoints;
	}

	public void setOldPoints(double oldPoints) {
		this.oldPoints = oldPoints;
	}

	public double getNewPoints() {
		return newPoints;
	}

	public void setNewPoints(double newPoints) {
		this.newPoints = newPoints;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}