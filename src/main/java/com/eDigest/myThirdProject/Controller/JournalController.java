package com.eDigest.myThirdProject.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eDigest.myThirdProject.Entity.Journal;

@RestController
@RequestMapping("/journal")
public class JournalController {
	
	
	private Map<Integer,Journal> journalentries = new HashMap();
	
	@GetMapping("/id/{myid}")
	public Journal getById(@PathVariable int myid){
		Journal j = journalentries.get(myid);
		return j;
	}
	
	@PostMapping("/add")
	public void addJournal(@RequestBody Journal entryJournal) {
		journalentries.put(entryJournal.getId(), entryJournal);
	
	}
	@GetMapping("/list")
	public List<Journal> getall(){
		return new ArrayList<Journal>(journalentries.values());
	}
	
	@PutMapping("/updateDescription/{id}")
	public Journal update(@PathVariable int id,@RequestBody Journal entry) {
		Journal j  = journalentries.get(id);
		System.out.println("Earlier the description was " +j.getDescription());
		j.setDescription(entry.getDescription());
		System.out.println("Earlier the description was " +j.getDescription());
		journalentries.put(id, j);
		return j;	
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		journalentries.remove(id);
	}
	
	

}
