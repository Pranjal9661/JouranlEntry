package com.Practice.myThirdProject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Practice.myThirdProject.Entity.Journal;

public interface JournalRepository extends MongoRepository<Journal, String>{

}
