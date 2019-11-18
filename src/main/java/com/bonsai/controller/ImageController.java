package com.bonsai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bonsai.model.Image;
import com.bonsai.model.PageDataDTO;
import com.bonsai.model.Resource;
import com.bonsai.model.TreeType;
import com.bonsai.repository.ResourceRepository;
import com.bonsai.service.ImageService;
import com.bonsai.service.QueryService;
import com.bonsai.service.ResourceService;
import com.bonsai.utils.ApachePOIExcelRead;
import com.bonsai.utils.WebCrawler;


@RestController
public class ImageController {

	@Autowired
	private final ImageService imageService;
	
	@Autowired
	private final ResourceService resourceService;
	
	@Autowired
	ResourceRepository resourceRepository;
	

	@Autowired
	private  ImageController(ImageService imageService) {
		this.resourceService = new ResourceService();
		this.queryService = new QueryService();
		this.imageService = imageService;
	}
	
	@Autowired
	private final QueryService queryService;

	/**
	 * Image management
	 * @author DVQuan
	 */

	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	@ResponseBody
	public String addNewImage(@Valid @RequestBody Image Image, BindingResult bindingResult, Model model) {
		if (Image.getCreated_at() == null)
			Image.setCreated_at(new Date());
		if (bindingResult.hasErrors()) {
			return "Error";
		} else {
			imageService.insert(Image);

			model.addAttribute("Success Message", "Image has been added succesfully");

			model.addAttribute("Image", new Image());
		}
		return "Ok";
	}
	
	// Trả về một Object dạng JSON
	@RequestMapping(value = "/getAllImages", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Image>> findAll(){
		List<Image> allImage = (List<Image>) imageService.findAll();
		if(allImage!=null) {
			return new ResponseEntity<Iterable<Image>>(allImage, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Iterable<Image>>(HttpStatus.BAD_REQUEST);
		}
	}

	// Trả về một Object dạng JSON
	@RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> getImage(@PathVariable("id") String id, Model model) {
		List<Image> images = new ArrayList<Image>();
		if(id.contains(",")) {
			String[] listId = id.split(",");
			for(String imgId : listId) {
				Image image = new Image();
				image = imageService.findById(imgId).get();
				images.add(image);
			}
			System.out.println(listId);
		}
		else {
			Image image = imageService.findById(id).get();
			images.add(image);
		}
		return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
	}

	// Update một tấm ảnh
	@RequestMapping(value = "/updateImage", method = RequestMethod.PUT)
	public String updateImage(@RequestBody Image image) {
		imageService.update(image);
		if (image.getLast_modified() == null)
			image.setLast_modified(new Date());
		return "Update image successfully";
	}

	// Xóa một tấm ảnh
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteImage(@PathVariable("id") String id, Model model) {
		Image image = imageService.findById(id).get();
		imageService.delete(image);
		return "Delete successfully";
	}
	
	
	// Lưu ảnh crawl được về database
	@RequestMapping(value = "/getImageResource", method = RequestMethod.GET)
	public String getImageSource(String searchTerm) throws InterruptedException, IOException{
		resourceService.getAllResource(searchTerm);	
		return "Ok";
	}
	
	// Khởi tạo ảnh trong database
	@RequestMapping(value = "/initImages", method = RequestMethod.GET)
	public String initAllImages(){
		try{
			List<Image> allImgSrc = new ApachePOIExcelRead().getModels(0);
			for (Image image : allImgSrc) {
				if (image.getCreated_at() == null)
					image.setCreated_at(new Date());
				imageService.insert(image);
				System.out.println(image.getSummary());
			}
			return "OK";
		}
		catch (IOException e) {
			return "Error at" + e;
		}
	}
	
	// Lấy ảnh theo id_tr
	@RequestMapping(value = "/getImagesbyGroupId/{id}/{page}", method = RequestMethod.GET)
	public PageDataDTO getImagesbyGroupId(@PathVariable("id") String id, @PathVariable("page") int page) {
		List<Image> allImage = queryService.JPQLQuery(id);
		int size = 6;
		PageDataDTO result = new PageDataDTO();
		
		result.setTotal(allImage.size());
		result.setPageData(allImage.stream()
				  .skip(page * size)
				  .limit(size)
				  .collect(Collectors.toCollection(ArrayList::new)));
		return result;
	}
	
	// Lấy ảnh theo id_tr
	@RequestMapping(value = "/getAllTreeTypes", method = RequestMethod.GET)
	public List<TreeType> getImagesbyGroupId() {
		List<TreeType> allTypes = queryService.getAllCategory();
		return allTypes;
	}
	
}
