package com.bonsai.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonsai.model.Image;
import com.bonsai.repository.ImageRepository;

@Service
public class QueryService implements IQueryService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	EntityManagerFactory emf;

	@Override
	public List<Image> JPQLQuery(String type) {
		EntityManager em = emf.createEntityManager();
		
		// Sử dụng HQL query khác với SQL
		Query query = em.createQuery(
				"SELECT r FROM Image r WHERE r.id_tree_type="+type);
//		"SELECT i.id FROM Image i INNER JOIN Tree_type tt ON i.id_tree_type = tt.id WHERE tt.id = " + type);
//		"Select e FROM Image e INNER JOIN e.Tree_type");
		List<Image> list = query.getResultList();
//		System.out.println(list);
		
		return list;
	}

}
