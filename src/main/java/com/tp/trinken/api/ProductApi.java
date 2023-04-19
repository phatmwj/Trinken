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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping(value = "/create")
	public ResponseEntity<?> addNewProduct(@Valid @ModelAttribute ProductDto productDto) {
		if(!productService.checkExitsProductName(productDto.getProductName())) {
			Product product = new Product();
			List<Category> categories = categoryService.findAllByIds(productDto.getCategoryIds());
			product.setCategories(categories);
			if(productDto.getImageFile()!=null) {
				product.setImage(cloudinaryService.upload(productDto.getImageFile()));
			}
			Brand brand=brandService.findById(productDto.getBrandId());
			product.setBrand(brand);
			BeanUtils.copyProperties(productDto, product);
			if(!productDto.getImageFiles().isEmpty()) {
				List<Asset> assets=new ArrayList<>();
				for(MultipartFile imageFile: productDto.getImageFiles()) {
					Asset asset=new Asset();
					asset.setPath(cloudinaryService.upload(imageFile));
					assets.add(asset);
				}
				product.setAssets(assets);
				productService.save(product);
				for(Asset asset: assets) {
					asset.setProduct(product);
					assetService.save(asset);
				}
			}else {
				productService.save(product);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}else if(productService.checkExitsProductName(productDto.getProductName())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	

	public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductDto productDto,
			@RequestParam MultipartFile imageFile, @RequestParam MultipartFile[] file) {
		Product product = new Product();
		Asset asset = new Asset();
		if (productDto.getProductName() != null && !productService.existsByCategoryName(productDto.getProductName())
				&& file.length > 0) {
			product.setProductName(productDto.getProductName());
			product.setDescription(productDto.getDescription());
			int pid = productService.save(product).getId();

			/*
			 * for (MultipartFile files : file) {
			 * 
			 * }
			 */

		}
		return null;
	}

	@GetMapping(value = "/get-product-by-id")
	public ResponseEntity<?> getProductById(@Valid @RequestParam Integer id) {
		Optional<Product> productOptional = productService.findById(id);
		if (productOptional != null) {
			return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(rs.result(true, "There is no product"), HttpStatus.NO_CONTENT);
		}

	}

}
