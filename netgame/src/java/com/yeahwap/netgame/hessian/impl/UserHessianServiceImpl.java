package com.yeahwap.netgame.hessian.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.yeahwap.netgame.hessian.UserHessianService;

@Service
public class UserHessianServiceImpl implements UserHessianService {

	@Override
	public boolean add(Serializable obj) {
		System.out.println("add obj");
		return true;
	}

	@Override
	public boolean update(Serializable obj) {
		System.out.println("update obj");
		return true;
	}
}
