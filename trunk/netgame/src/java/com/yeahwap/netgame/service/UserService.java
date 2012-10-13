package com.yeahwap.netgame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.spring.GeneralService;

@Service
public class UserService extends GeneralService<User> {
	@Transactional
	public int add(User u) {
		return super.add(u);
	}
	
	@Transactional(readOnly=true)
	public User get(int id) {
		return super.get(id);
	}
}
