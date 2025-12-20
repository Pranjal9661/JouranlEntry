package com.Practice.myThirdProject.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(value = "users")
@Data
public class User {
	
	@Id
	private String id;
	
	@NonNull
	@Indexed
	private String userName;
	
	@NonNull
	private String userPassword;
	
	@DBRef
	private List<Journal> journalEntries = new ArrayList<>();
	private List<String> roles;

}
