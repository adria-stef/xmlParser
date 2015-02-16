package com.adii.parser.task;

import java.util.List;

public class Task {
	private String subject;
	private String number;
	private String dueDate;
	private String topic;
	private List<String> instructions;
	private String condition;
	private String solution;
	private String maxPoints;

	public Task(String subject, String number, String dueDate, String topic, List<String> instructions,
	        String condition, String solution, String maxPoints) {
		this.subject = subject;
		this.number = number;
		this.dueDate = dueDate;
		this.topic = topic;
		this.instructions = instructions;
		this.condition = condition;
		this.solution = solution;
		this.maxPoints = maxPoints;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(String maxPoints) {
		this.maxPoints = maxPoints;
	}

	@Override
	public String toString() {
		return "Task [subject=" + subject + ", number=" + number + ", dueDate=" + dueDate + ", topic=" + topic
		        + ", instructions=" + instructions + ", condition=" + condition + ", solution=" + solution
		        + ", maxPoints=" + maxPoints + "]";
	}
}