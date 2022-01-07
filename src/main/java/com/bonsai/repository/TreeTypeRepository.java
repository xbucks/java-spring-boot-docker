package com.bonsai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bonsai.model.TreeType;

@Repository
public interface TreeTypeRepository extends CrudRepository<TreeType, String> {
	@Query(value = "SELECT * FROM Tree_type ", nativeQuery = true)
	List<TreeType> customFind();
}
