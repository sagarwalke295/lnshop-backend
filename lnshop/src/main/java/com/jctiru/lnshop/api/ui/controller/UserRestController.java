package com.jctiru.lnshop.api.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jctiru.lnshop.api.service.UserService;
import com.jctiru.lnshop.api.shared.dto.UserDto;
import com.jctiru.lnshop.api.ui.model.request.UserDetailsRequestModel;
import com.jctiru.lnshop.api.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping
	public UserRest createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);

		return modelMapper.map(createdUser, UserRest.class);
	}

}
