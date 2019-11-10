package com.bonsai.service;

import java.util.List;

import com.bonsai.model.Image;

public interface IQueryService {
	List<Image> JPQLQuery(String type);
}
