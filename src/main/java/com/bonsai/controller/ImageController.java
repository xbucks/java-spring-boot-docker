package com.bonsai.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bonsai.model.Image;
import com.bonsai.service.ImageServiceImpl;
import com.bonsai.service.QueryService;
import com.bonsai.utils.ApachePOIExcelRead;
import com.bonsai.utils.WebCrawler;


@RestController
public class ImageController {

	@Autowired
	private final ImageServiceImpl imageService;
	

	@Autowired
	private  ImageController(ImageServiceImpl imageService) {
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
	public ResponseEntity<Image> getImage(@PathVariable("id") String id, Model model) {
		Image image = imageService.findById(id).get();
		return new ResponseEntity<Image>(image, HttpStatus.OK);
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
	
	
	// Lay du lieu anh
	@RequestMapping(value = "/getImageSource", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getImageSource(String searchTerm) throws InterruptedException, IOException{
		List<String> allImgSrc = new WebCrawler().getPageLinks(searchTerm);
		if(allImgSrc!=null) {
			return new ResponseEntity<List<String>>(allImgSrc,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
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
	
	// Xóa một tấm ảnh
	@RequestMapping(value = "/getImagesbyGroupId/{id}", method = RequestMethod.GET)
	public List<Image> getImagesbyGroupId(@PathVariable("id") String id) {
		List<Image> allImage = (List<Image>) queryService.JPQLQuery(id);
		return allImage;
	}
}
