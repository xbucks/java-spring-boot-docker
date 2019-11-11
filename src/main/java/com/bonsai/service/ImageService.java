package com.bonsai.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bonsai.model.Image;
import com.bonsai.repository.*;

@Service("ImageService")
public class ImageService implements IImageService{

	@Autowired
	private ImageRepository ImageRepository;

	@Override
	public List<Image> getAllImage() {
		return ImageRepository.findAll();
	}

	@Override
	public void insert(Image Image) {
		ImageRepository.saveAndFlush(Image);
	}
	
	@Override
	public void update(Image Image) {
		ImageRepository.save(Image);
	}
	
	@Override
	public void delete(Image Image) {
		ImageRepository.delete(Image);
	}

	@Override
	public Optional<Image> findById(String id) {
		return ImageRepository.findById(id);
	}

	@Override
	public Iterable<Image> findAll() {
		return ImageRepository.findAll();
	}

}
