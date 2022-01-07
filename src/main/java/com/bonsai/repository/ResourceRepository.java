package com.bonsai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonsai.model.Resource;

//Indicates that an annotated class is a "Repository", originally defined byDomain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage,retrieval, and search behavior which emulates a collection of objects". 
//Teams implementing traditional Java EE patterns such as "Data Access Object"may also apply this stereotype to DAO classes, though care should be taken tounderstand the distinction between Data Access Object and DDD-style repositoriesbefore doing so. This annotation is a general-purpose stereotype and individual teamsmay narrow their semantics and use as appropriate. 
//A class thus annotated is eligible for Spring DataAccessException translationwhen used in conjunction with a PersistenceExceptionTranslationPostProcessor. The annotated class is also clarified asto its role in the overall application architecture for the purpose of tooling,aspects, etc. 

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer>{
//	void getAllResource();
}
