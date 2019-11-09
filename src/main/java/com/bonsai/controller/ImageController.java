package com.bonsai.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonsai.model.Image;
import com.bonsai.service.ApachePOIExcelRead;
import com.bonsai.service.ImageServiceImpl;
import com.bonsai.service.WebCrawler;


@RestController
public class ImageController {

	@Autowired
	private final ImageServiceImpl ImageService;
	

	@Autowired
	public ImageController(ImageServiceImpl ImageService) {
		this.ImageService = ImageService;
	}

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
			ImageService.insert(Image);

			model.addAttribute("Success Message", "Image has been added succesfully");

			model.addAttribute("Image", new Image());
		}
		return "Ok";
	}
	
	// Trả về một Object dạng JSON
	@RequestMapping(value = "/getAllImages", method = RequestMethod.GET)
	private ResponseEntity<Iterable<Image>> findAll(){
		try{
			return new ResponseEntity<Iterable<Image>>(ImageService.findAll(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Iterable<Image>>(HttpStatus.BAD_REQUEST);
		}
	}

	// Trả về một Object dạng JSON
	@RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
	public ResponseEntity<Image> getImage(@PathVariable("id") String id, Model model) {
		Image image = ImageService.findById(id).get();
		return new ResponseEntity<Image>(image, HttpStatus.OK);
	}

	// Update một tấm ảnh
	@RequestMapping(value = "/updateImage", method = RequestMethod.PUT)
	public String updateImage(@RequestBody Image image) {
		ImageService.update(image);
		if (image.getLast_modified() == null)
			image.setLast_modified(new Date());
		return "Update image successfully";
	}

	// Xóa một tấm ảnh
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteImage(@PathVariable("id") String id, Model model) {
		Image image = ImageService.findById(id).get();
		ImageService.delete(image);
		return "Delete successfully";
	}
	
	
	// Lay du lieu anh
	@RequestMapping(value = "/getImageSource", method = RequestMethod.GET)
	private ResponseEntity<Iterable<String>> getImageSource(String searchTerm){
		try{
			List<String> allImgSrc = new WebCrawler().getPageLinks(searchTerm);
			int totalPage = allImgSrc.size();
			
			return new ResponseEntity<Iterable<String>>(allImgSrc,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Iterable<String>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Khởi tạo ảnh trong database
	@RequestMapping(value = "/initImages", method = RequestMethod.POST)
	public String initAllImages(){
		try{
			List<Image> allImgSrc = new ApachePOIExcelRead().getModels(0);
			for (Image image : allImgSrc) {
				if (image.getCreated_at() == null)
					image.setCreated_at(new Date());
				ImageService.insert(image);
				System.out.println(image.getSummary());
			}
			return "OK";
		}
		catch (Exception e) {
			return "Error" + e;
		}
	}
}
