package com.eDigest.myThirdProject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eDigest.myThirdProject.Entity.Journal;

public interface JournalRepository extends MongoRepository<Journal, String>{

}
