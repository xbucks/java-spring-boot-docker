package com.bonsai.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bonsai.model.Image;
import com.bonsai.repository.*;

@Service("ImageService")
public class ImageServiceImpl implements ImageService{

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
	
//	public void importImage(Image image) {
//
//	    MultipartFile proudctImage = product.getProudctImage();
//
//	    if (!proudctImage.isEmpty()) {
//	        try {
//	            proudctImage.transferTo(resourceLoader.getResource("resources/images/"+product.getProductId()+".png").getFile());
//
//	        } catch (Exception e) {
//	            throw new RuntimeException("Product Image saving failed", e);
//	        }
//	    }
//
//	    listOfProducts.add(product);
//	}

}
