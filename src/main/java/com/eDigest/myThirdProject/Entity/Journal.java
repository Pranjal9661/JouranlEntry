package com.eDigest.myThirdProject.Entity;

import org.springframework.stereotype.Component;

@Component
public class Journal {
	
	private int id;
	private String journal;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
