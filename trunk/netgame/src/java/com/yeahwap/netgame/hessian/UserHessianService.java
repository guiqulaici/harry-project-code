package com.yeahwap.netgame.hessian;

import java.io.Serializable;

/**
 * Create on 2012-10-12 14:03
 * 
 * @author Harry
 * 
 */
public interface UserHessianService {
	public boolean add(Serializable obj);

	public boolean update(Serializable obj);
}
