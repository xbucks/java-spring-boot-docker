package com.bonsai.service;


import java.util.List;
import java.util.Optional;

import com.bonsai.model.Image;

public interface ImageService {
	
	public abstract List<Image> getAllImage();

	public abstract void insert(Image Image);
	
	public abstract void update(Image Image);
	
	public abstract void delete(Image Image);

	public abstract Optional<Image> findById(String id) ;

	public abstract Iterable<Image> findAll() ;
}
