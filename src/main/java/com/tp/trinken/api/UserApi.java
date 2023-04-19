package com.tp.trinken.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tp.trinken.dto.ProfileDto;
import com.tp.trinken.dto.SignUpDto;
import com.tp.trinken.dto.UserDto;
import com.tp.trinken.entity.User;
import com.tp.trinken.service.CloudinaryService;
import com.tp.trinken.service.EmailService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/user")
public class UserApi {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	EmailService emailService;
	
	Result rs=new Result();
	
	@GetMapping(value = "/get-all")
	public ResponseEntity<List<User>> listAllUser(){
		List<User> listUsers= userService.findAll();
		if(listUsers.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
	}
	
	//register
	@PostMapping(value = "/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
		Optional<User> userOptional=userService.findByUserName(signUpDto.getUserName());
		if(!userOptional.isEmpty() /*&& userOptional.get().isActive()*/){
			return new ResponseEntity<>(rs.result(true,"Tài khoản đã tồn tại"),HttpStatus.CONFLICT);
		}
		else if(userService.checkEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>(rs.result(true,"Email đã tồn tại"),HttpStatus.CONFLICT);
		}else {
			try {
				User user= new User();
				BeanUtils.copyProperties(signUpDto, user);
				userService.save(user);
				return new ResponseEntity<>(rs.resultUser(false,"Đăng kí thành công",user),HttpStatus.OK);
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(rs.result(true, "An error occur!"),HttpStatus.NOT_IMPLEMENTED);
			}
		}
	}
	
	//login
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password ) throws IOException{
		Optional<User> userOptional=userService.login(username, password);
		if(userOptional.isEmpty()){
			return new ResponseEntity<>(rs.result(true,"Thông tin đăng nhập không chính xác"),HttpStatus.BAD_REQUEST);
		}
		else if(!userOptional.get().isActive()){
			return new ResponseEntity<>(rs.result(true,"Tài khoản không còn hoạt động"),HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(rs.resultUser(false, "Đăng nhập thành công", userOptional.get()),HttpStatus.OK);
		}
	}
	
	//update profile
	@PutMapping(value = "/profile/{id}")
	public ResponseEntity<?> updateProfile(@PathVariable Integer id,@Valid @ModelAttribute ProfileDto profileDto){
		User user=userService.findById(id).get();
		if(profileDto.getImageFile()!=null) {
			if(user.getImage()!=null) {
				cloudinaryService.delete(user.getImage());
			}
			user.setImage(cloudinaryService.upload(profileDto.getImageFile()));
		}
		BeanUtils.copyProperties(profileDto, user);
		try {
			userService.save(user);	
			return new ResponseEntity<>(rs.resultUser(false,"Đã cập nhật thành công", user),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(rs.result(false,"Error"),HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	
	//upload image
	@PostMapping(value="/upload-avatar")
	public String uploadAvatar(@RequestParam MultipartFile imageFile) {
		return cloudinaryService.upload(imageFile);	
	}
	
	
	//delete image
	@PostMapping(value="/delete-avatar")
	public void deleteImage(@RequestParam String imageUrl) {
		cloudinaryService.delete(imageUrl);
		
	}
	
	//Forgot password
	@PostMapping(value="/forgot-password")
	public ResponseEntity<?>ForgotPassword(@RequestParam String email){
		try {
			emailService.sendMail(email);
			return new ResponseEntity<>(rs.result(false, "Đã gửi mã xác thực thành công"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(rs.result(true, "Failed"),HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	
	//Auth
	@PostMapping(value="/auth")
	public ResponseEntity<?>Auth(@RequestParam int code, HttpServletRequest request){
		HttpSession session= request.getSession();
		String recode = (String) session.getAttribute("code");
		if(session!=null && recode!=null) {
			Integer code2= Integer.parseInt(recode);
			if(code == code2) {
				session.invalidate();
				return new ResponseEntity<>(rs.result(false, "Xác thực thành công"),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(rs.result(false, "Mã xác thực ko chính xác"),HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<>(rs.result(false, "Vui lòng yêu cầu gửi mã xác nhận mới"),HttpStatus.OK);
		}
	}
	
	@PutMapping(value="/create-new-pasword")
	public ResponseEntity<?>CreateNewPassword(@RequestParam String email,@RequestParam String password, @RequestParam String rePassword){
		User user=userService.findByEmail(email).get();
		if(password.equals(rePassword)) {
			user.setPassword(password);
			userService.save(user);
			return new ResponseEntity<>(rs.result(false, "Tạo thành công mật khẩu mới"),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(rs.result(false, "Xác nhận lại mật khẩu của bạn"),HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value="/{id}/change-password")
	public ResponseEntity<?>ChangePassword(@PathVariable int id,@RequestParam String password, @RequestParam String newPassword, @RequestParam String rePassword){
		User user= userService.findById(id).get();
		if(user.getPassword().equals(password)) {
			if(newPassword.equals(rePassword)) {
				user.setPassword(newPassword);
				userService.save(user);
				return new ResponseEntity<>(rs.result(false, "Đổi mật khẩu thành công"),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(rs.result(false, "Xác nhận lại mật khẩu mới không đúng"),HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<>(rs.result(false, "Mật khẩu của bạn không đúng"),HttpStatus.CONFLICT);
		}
	}
	
}
	