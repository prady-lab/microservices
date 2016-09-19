/**
 * 
 */
package com.prady.sample.clould.user.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prady.sample.cloud.model.user.User;

/**
 * @author Prady
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prady.sample.clould.gateway.service.GatewayService#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		log.info("GET All Users ");
		return new ArrayList<User>();
	}

}
