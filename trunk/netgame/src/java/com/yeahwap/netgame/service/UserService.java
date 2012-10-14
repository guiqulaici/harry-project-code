package com.yeahwap.netgame.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.spring.GeneralService;

@Service
public class UserService extends GeneralService<User> {
	@Transactional
	public int add(User u) {
		int id = super.add(u);
		System.out.println("u=" + u.getId());
		return id;
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	public User getUserByName(String name) {
		String hql = "from User where name=?";
		List<User> list = hibernateTemplate.find(hql, new Object[] { name });
		return list.size() > 0 ? list.get(0) : null;
	}
}
