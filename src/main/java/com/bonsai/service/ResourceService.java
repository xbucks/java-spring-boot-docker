package com.bonsai.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonsai.model.Resource;
import com.bonsai.repository.ImageRepository;
import com.bonsai.repository.ResourceRepository;
import com.bonsai.utils.WebCrawler;

@Service
public class ResourceService implements IResourceService{
	
	@Autowired
	ResourceRepository resourceRepository;

	@Override
	public void getAllResource(String searchTerm) {
		List<Resource> resources = new ArrayList<Resource>();
		List<String> allImgSrc;
		try {
			allImgSrc = new WebCrawler().getPageLinks(searchTerm);
		
			for(String link : allImgSrc) {
				Resource resource= new Resource();
				resource.setLink(link);
				resources.add(resource);
			}
			
			resourceRepository.saveAll(resources);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}		
		
	}

}
