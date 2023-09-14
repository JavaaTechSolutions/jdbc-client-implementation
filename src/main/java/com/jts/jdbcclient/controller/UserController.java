package com.jts.jdbcclient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private JDBCClientUserService jdbcClientUserService;

	@GetMapping("/findAll")
	List<User> findAll() {
		return jdbcClientUserService.finalAll();
	}

	@GetMapping("/findById/{id}")
	Optional<User> findById(@PathVariable String id) {
		return jdbcClientUserService.finalById(id);
	}

	@PostMapping("/createUser")
	@ResponseStatus(HttpStatus.CREATED)
	void createUser(@RequestBody User user) {
		jdbcClientUserService.createdUser(user);
	}

	@PutMapping("/updateUser/{id}")
	void update(@RequestBody User user, @PathVariable String id) {
		jdbcClientUserService.updateUser(user, id);
	}

	@DeleteMapping("/deleteById/{id}")
	void delete(@PathVariable String id) {
		jdbcClientUserService.deleteUser(id);
	}

}
