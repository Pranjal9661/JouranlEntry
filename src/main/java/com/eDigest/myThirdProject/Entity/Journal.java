package com.eDigest.myThirdProject.Entity;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "journal_entry")
@Data
public class Journal {
	
	@Id
	private String id;
	
	private String journal;
	
	
	private String description;
	
	private LocalDate date;
	
	private String title;
	
	
	
	

}
