package com.phegondev.Phegon_Ecommerce.service.interf;

import com.phegondev.Phegon_Ecommerce.dto.LoginRequest;
import com.phegondev.Phegon_Ecommerce.dto.Response;
import com.phegondev.Phegon_Ecommerce.dto.UserDto;
import com.phegondev.Phegon_Ecommerce.entity.User;

public interface UserService {

	Response registerUser(UserDto registrationRequest);
	Response loginUser(LoginRequest loginRequest);
	Response getAllUsers();
	User getLoginUser();
	Response getUserInfoAndOrderHistory();
}
