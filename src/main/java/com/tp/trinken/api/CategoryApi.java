package com.tp.trinken.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.dto.CategoryDto;
import com.tp.trinken.entity.Category;
import com.tp.trinken.service.CategoryService;
import com.tp.trinken.service.CloudinaryService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/category")
public class CategoryApi {
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;

	@Autowired
	CloudinaryService cloudinaryService;

	Result rs = new Result();

	@GetMapping(value = "/get-all")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categories = new ArrayList<>();
		categories = categoryService.findAll();
		if (categories.size() > 0) {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> addCategory(@Valid @ModelAttribute CategoryDto categoryDto) {
		Category category = new Category();
		if (!categoryService.existsByCategoryName(categoryDto.getName())) {
			category.setCategoryName(categoryDto.getName());
			if (categoryDto.getImage() != null) {
				category.setImage(cloudinaryService.upload(categoryDto.getImage()));
			}

			categoryService.save(category);

			rs.resultCategory(false, "Successfully", category);
			return new ResponseEntity<>(category, HttpStatus.OK);
		} else
			return new ResponseEntity<>(rs.result(true, "Category is existed"), HttpStatus.CONFLICT);

	}

	@PutMapping(value = "delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
		Optional<Category> optional = categoryService.findById(id);
		Category category = optional.get();
		category.setActive(false);
		categoryService.save(category);

		return new ResponseEntity<>(rs.resultCategory(false, "Deleted successfully", category), HttpStatus.OK);
	}

	@PutMapping(value = "restore/{id}")
	public ResponseEntity<?> restoreCategory(@PathVariable int id) {
		Optional<Category> optional = categoryService.findById(id);
		Category category = optional.get();
		category.setActive(true);
		categoryService.save(category);

		return new ResponseEntity<>(rs.resultCategory(false, "Restored successfully", category), HttpStatus.OK);
	}

	@PutMapping(value = "update/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @Valid @ModelAttribute CategoryDto categoryDto) {
		Optional<Category> optional = categoryService.findById(id);
		Category category = optional.get();

		if (categoryDto.getName() != null && !categoryService.existsByCategoryName(categoryDto.getName())) {
			category.setCategoryName(categoryDto.getName());
		}
		if (categoryDto.getImage() != null) {
			if (category.getImage() != null) {
				cloudinaryService.delete(category.getImage());
			}
			category.setImage(cloudinaryService.upload(categoryDto.getImage()));
		}

		categoryService.save(category);

		return new ResponseEntity<>(rs.resultCategory(false, "Updated successfully", category), HttpStatus.OK);

	}

	@GetMapping(value = "/get-all-active")
	public ResponseEntity<List<Category>> getAllActiveCategory() {
		List<Category> categories = new ArrayList<>();
		categories = categoryService.findByActive(true);
		if (categories.size() > 0) {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
	}

}
