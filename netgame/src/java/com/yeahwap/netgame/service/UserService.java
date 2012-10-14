package com.yeahwap.netgame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.spring.GeneralService;

@Service
public class UserService extends GeneralService<User> {
	@Transactional
	public int add(User u) {
		int id = super.add(u);
		System.out.println("u="+u.getId());
		return id;
	}
	
	@Transactional(readOnly=true)
	public User get(int id) {
		return super.get(id);
	}
	
	@Transactional
	public void update(User u) {
		User oldUser = get(u.getId());
		if (oldUser != null) {
			super.update(u);
			log.debug("change user for uid is " + u.getId());
		}
	}
}
