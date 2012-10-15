package com.yeahwap.netgame.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.spring.GeneralService;

/**
 * Create on 2012-10-15
 * 
 * @author Harry
 *         <ul>
 *         <li></li>
 *         <li></li>
 *         </ul>
 */
@Service
public class UserService extends GeneralService<User> {
	@Transactional
	public int add(User u) {
		int id = super.add(u);
		System.out.println("u=" + u.getId());
		return id;
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
	public User get(int id) {
		return super.get(id);
	}


	@Transactional(readOnly = true)
	public User getUserByName(String name) {
		String hql = "from User where name=?";
		List<User> list = hibernateTemplate.find(hql, new Object[] { name });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Transactional(readOnly = true)
	public User getUserByNameAndPassword(String name, String password) {
		String hql = "from User where name=? and password=?";
		List<User> list = hibernateTemplate.find(hql, new Object[] { name,
				password });

		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Transactional(readOnly = true)
	public User getUserByIdAndName(int id, String name, String password) {
		String hql = "from User where id=? and name=? and password=?";
		List<User> list = hibernateTemplate.find(hql, new Object[] { id,
				name, password });

		return list.size() > 0 ? list.get(0) : null;
	}
	
	public static void main(String[] args) {
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("applicationContext-hibernate.xml");
		// UserService us = (UserService) ctx.getBean("userService");
		// User u = us.getUserByName("harry");
		// System.out.println(u);
	}
}
