package org.self.controller;

import org.self.model.User;
import org.self.request.CreateUserRequest;
import org.self.service.IUserService;
import org.self.util.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {

	private IUserService userService;

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
		User newUser = new User();
		newUser.setEmail(request.getEmail());
		newUser.setEnabled(request.getEnabled());
		newUser.setPassword(request.getPassword());
		newUser.setUsername(request.getUsername());		
		User user = userService.createNewUser(newUser);
		CreateUserResponse response = new CreateUserResponse();
		response.setUsername(user.getUsername());
		return response;
	}

}
