package com.bonsai.service;

import java.util.List;

import com.bonsai.model.Image;
import com.bonsai.model.TreeType;

public interface IQueryService {
	List<Image> JPQLQuery(String type);
	
	List<TreeType> getAllCategory();
}
