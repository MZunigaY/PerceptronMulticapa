package com.demo.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.demo.model.UserCredential;
import com.demo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);

	@Override
	public boolean authentication(UserCredential userCredential) {
		LOG.info("METHOD: loginCheck() -- PARAMS: "+userCredential.toString());
		if (userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
			LOG.info("Returning to contacts view");
			return true;
		} else {
			LOG.info("Redirect to login?error");
			return false;
		}
	}

}
