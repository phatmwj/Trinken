package com.tp.trinken.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tp.trinken.dto.ProductDto;
import com.tp.trinken.entity.Asset;
import com.tp.trinken.entity.Brand;
import com.tp.trinken.entity.Category;
import com.tp.trinken.entity.Product;
import com.tp.trinken.service.AssetService;
import com.tp.trinken.service.BrandService;
import com.tp.trinken.service.CategoryService;
import com.tp.trinken.service.CloudinaryService;
import com.tp.trinken.service.ProductService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/product")
public class ProductApi {
	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BrandService brandService;

	@Autowired
	AssetService assetService;

	@Autowired
	CloudinaryService cloudinaryService;

	Result rs = new Result();

	@GetMapping(value = "/get-all")
	public ResponseEntity<?> getAllProduct() {
		List<Product> products = productService.findAll();

		if (products.size() > 0) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-all-active")
	public ResponseEntity<?> getAllProductActive() {
		List<Product> products = productService.findByActive(true);

		if (products.size() > 0) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-all-unactive")
	public ResponseEntity<?> getAllProductUnActive() {
		List<Product> products = productService.findByActive(false);

		if (products.size() > 0) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/create")
	public ResponseEntity<?> addNewProduct(@Valid @ModelAttribute ProductDto productDto) {
		if (!productService.checkExitsProductName(productDto.getProductName())) {
			Product product = new Product();
			List<Category> categories = categoryService.findAllByIds(productDto.getCategoryIds());
			product.setCategories(categories);
			if (productDto.getImageFile() != null) {
				product.setImage(cloudinaryService.upload(productDto.getImageFile()));
			}
			Brand brand = brandService.findById(productDto.getBrandId());
			product.setBrand(brand);
			BeanUtils.copyProperties(productDto, product);
			if (productDto.getImageFiles() != null) {
				List<Asset> assets = new ArrayList<>();
				for (MultipartFile imageFile : productDto.getImageFiles()) {
					Asset asset = new Asset();
					asset.setPath(cloudinaryService.upload(imageFile));
					asset.setProduct(product);
					assets.add(asset);
				}
				product.setAssets(assets);
			}
			productService.save(product);
			return new ResponseEntity<>(rs.result(false, "Created product successfully!"), HttpStatus.OK);
		} else if (productService.checkExitsProductName(productDto.getProductName())) {
			return new ResponseEntity<>(rs.result(true, "Existed product!"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<>(rs.result(true, "Failed!"), HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping(value = "/get-product-by-id/{id}")
	public ResponseEntity<?> getProductById(@Valid @PathVariable Integer id) {
		Optional<Product> productOptional = productService.findById(id);
		if (productOptional != null) {
			return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(rs.result(true, "There is no product"), HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "get-product-by-category/active={active}/cateid={id}")
	public ResponseEntity<?> getProductByCategory(@PathVariable("active") Boolean active,
			@PathVariable("id") Integer category_id) {
		List<Product> products = new ArrayList<>();
		if (active) {
			products = productService.getProductByCategoryAndActive(1, category_id);
		} else {
			products = productService.getProductByCategoryAndActive(0, category_id);
		}

		if (products.size() > 0) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(rs.result(true, "There is no product"), HttpStatus.NO_CONTENT);
		}

	}

}
