package com.yeahwap.netgame.hessian;

import java.io.Serializable;

import com.yeahwap.netgame.domain.pojo.User;

/**
 * Create on 2012-10-12 14:03
 * 
 * @author Harry
 * 
 */
public interface UserHessianService {
	public User add(Serializable obj);

	public User update(Serializable obj);
}
