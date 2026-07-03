package com.Practice.myThirdProject.Entity;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;


@Document(collection = "journal_entry")
@Data
public class Journal {
	
	public Journal() {
		// TODO Auto-generated constructor stub
	}


	@Id
	private String id;
	
	@NonNull
	private String journal;
	
	@NonNull
	private String description;
	
	@NonNull
	private LocalDate date;
	
	
	@NonNull
	private String title;
	
	
	
	

}
