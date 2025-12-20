package com.Practice.myThirdProject.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Practice.myThirdProject.Entity.Journal;
import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Service.JournalService;
import com.Practice.myThirdProject.Service.UserService;

@RestController
@RequestMapping("/api/auth/journal")
public class JournalController {
	
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/id/{myid}")
	public ResponseEntity<?> getById(@PathVariable String myid){
		try {
			Optional<Journal> list = journalService.getentryByid(myid);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/add/{userName}")
	public ResponseEntity<?> addJournal(@RequestBody Journal entryJournal,@PathVariable String userName) {
		journalService.saveEntry(entryJournal,userName);
		return new ResponseEntity<>(HttpStatus.OK);
	
	}
	
	  @GetMapping("/list/{userName}") 
	  public ResponseEntity<?> getUserEntries(@PathVariable String userName){
		  User byUserName = userService.findByUserName(userName);
		  List<Journal> list = byUserName.getJournalEntries();
		  return new ResponseEntity<>(list,HttpStatus.OK);
	  }
	  
	
		  @PutMapping("/updateDescription/{id}/{userName}") 
		  public ResponseEntity<?>update(@PathVariable String id,@PathVariable String userName,@RequestBody Journal entry) {
			  try {
		  Optional<Journal> old = journalService.getentryByid(id); 
		  Journal j = new Journal(); 
		  if(old!=null) { 
			j= old.get();
		  j.setDescription((entry.getDescription() != null &&
		  !entry.getDescription().equals("") )? entry.getDescription() :
		  j.getDescription()); j.setTitle((entry.getTitle() != null &&
		  !entry.getTitle().equals("")) ? entry.getTitle() : j.getTitle());
		  
		  } journalService.saveEntry(j); 
		  return new ResponseEntity<>(j,HttpStatus.OK);
		  } 
			  catch (Exception e) {
				  // TODO: handle exception return new
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
		  
		  
		  }
	
	  
	  @DeleteMapping("/delete/{id}/{userName}") 
	  public ResponseEntity<?> delete(@PathVariable String id,@PathVariable String userName) {
		  
		  journalService.deleteEntryById(id,userName);
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  
	  }
	 
	
	

}
