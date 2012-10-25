package com.yeahwap.netgame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.NgFrom;
import com.yeahwap.spring.GeneralService;

@Service
public class NgFromService extends GeneralService<NgFrom> {
	@Transactional(readOnly = true)
	public NgFrom get(int id) {
		return super.get(id);
	}
}
