package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.Settings;
import com.websystique.springsecurity.model.User;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findByLogin(String login);
        
        Boolean changePassword(Settings settings);	
}