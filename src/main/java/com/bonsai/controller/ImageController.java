package com.bonsai.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bonsai.service.FileStorageService;
import com.bonsai.service.ImageServiceImpl;
import com.bonsai.service.UploadFileResponse;
import com.bonsai.model.Image;

@RestController
public class ImageController {

	@Autowired
	private final ImageServiceImpl ImageService;
//	private final ImageRepository ImageRepository;
	

	@Autowired
	public ImageController(ImageServiceImpl ImageService) {
		this.ImageService = ImageService;
	}

	/**
	 * Image management
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
//		return "redirect:/api/home/tables";
		return "Ok";
	}
	
	// Trả về một Object dạng JSON
	@RequestMapping(value = "/getAllImages", method = RequestMethod.GET)
	private ResponseEntity<Iterable<Image>> findAll(){
		try{
			return new ResponseEntity<Iterable<Image>>(ImageService.findAll(),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Iterable<Image>>(HttpStatus.BAD_REQUEST);
		}
	}

	// Trả về một Object dạng JSON
	@RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
	public ResponseEntity<Image> getImage(@PathVariable("id") String id, Model model) {
		Image image = ImageService.findById(id).get();
		model.addAttribute("Image", image);
//		List<Category> categories = categoryRepository.findAll();
//		model.addAttribute("categories",categories);
		return new ResponseEntity<Image>(image, HttpStatus.OK);
	}

	// Update một tấm ảnh
	@RequestMapping(value = "/updateImage", method = RequestMethod.PUT)
	public String updateImage(@PathVariable("id") String id, @RequestBody Image image) {
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


//	@GetMapping(value="/Image/{ImageId}")
//	public String displayImageWithId(@PathVariable("ImageId") Integer id, Principal principal, @RequestParam(defaultValue = "0") int page, ModelMap model) {
//		Optional<Image> ImageOptional = ImageService.findById(id);
//		
//		if(!ImageOptional.isPresent())
//			throw new ResourceNotFoundException();
//		else {
//			Image Image = ImageOptional.get();
//			Image.setViews(Image.getViews()+1);
//			Comment comment = new Comment();
//			comment.setImage(Image);
//			Page<Image> Images = ImageRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(subtractPageByOne(page), 5));
//            Page<Category> categories = categoryRepository.findAll(PageRequest.of(subtractPageByOne(page), 5));
//            User user = null;
//            if(principal!=null) {
//	            String email = principal.getName();
//	            user = userRepository.findByEmail(email);
//            }
//            comment.setUser(user);
//            Pager pager = new Pager(Images, categories);
//            model.addAttribute("comment",comment);
//            model.addAttribute("Image",Image);
//            model.addAttribute("pager",pager);
//            model.addAttribute("user",user);
//            model.addAttribute("category",Image.getCategory());
//            ImageRepository.saveAndFlush(Image);
//		}
//		
//		return "/single-Image";
//	}
}
