package onlineshop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onlineshop.model.User;
import onlineshop.service.UserService;
import onlineshop.support.UserDTOToUser;
import onlineshop.support.UserToUserDTO;
import onlineshop.web.dto.UserDTO;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/users")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toDTO;
	
	@Autowired
	private UserDTOToUser toUser;
	
	
	@GetMapping("/sve")
	ResponseEntity<List<UserDTO>> getAlls() {
		List<User> userPage = null;
		userPage = userService.findAll();
		return new ResponseEntity<>( toDTO.convert(userPage) , HttpStatus.OK);
	}	
		
	@GetMapping()
	ResponseEntity<List<UserDTO>> getAllUsers(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String city,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<User> userPage = null;
		
		if(name != null || city != null ) {
			userPage = userService.search(name, city, pageNum);
		}
		else {
			userPage = userService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(userPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(userPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
		User user = userService.getById(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(user), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	ResponseEntity<UserDTO> deleteUser(@PathVariable Integer id){
		User deleted = userService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<UserDTO> addUser( @Valid @RequestBody UserDTO newUserDTO){
		
		if(newUserDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User savedUser = userService.save(toUser.convert(newUserDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedUser), HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value="/{id}" , consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id,
			@Valid  @RequestBody UserDTO userDTO ){
		
		User persisted = userService.getById(id);
		persisted.setName(userDTO.getName());
		persisted.setCity(userDTO.getCity());
		persisted.setAddress(userDTO.getAddress());
		persisted.setJmbg(userDTO.getJmbg());
		persisted.setPhone(userDTO.getPhone());
		persisted.setAccountNumber(userDTO.getAccountNumber());
		persisted.setAccountBalance(userDTO.getAccountBalance());
		
		userService.save(persisted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

	
	
}
