package com.tp.trinken.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tp.trinken.entity.Brand;
import com.tp.trinken.service.BrandService;
import com.tp.trinken.service.CloudinaryService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/brand")
public class BrandApi {

	@Autowired
	BrandService brandService;

	@Autowired
	CloudinaryService cloudinaryService;

	Result rs = new Result();

	@PostMapping("/create")
	public ResponseEntity<?> createBrand(@RequestParam String brandName, @RequestParam MultipartFile imageFile) {
		Brand brand = new Brand();
		if (!brandService.checkExistsByBrandName(brandName)) {
			brand.setBrandName(brandName);
			if (imageFile != null) {
				brand.setImage(cloudinaryService.upload(imageFile));
			}
			try {
				brandService.save(brand);
				return new ResponseEntity<>(rs.resultBrand(false, "Create successfully", brand), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else
			return new ResponseEntity<>(rs.result(true, "Brand is existed"), HttpStatus.CONFLICT);

		return new ResponseEntity<>(rs.result(true, "Failed"), HttpStatus.NOT_IMPLEMENTED);

	}

}
