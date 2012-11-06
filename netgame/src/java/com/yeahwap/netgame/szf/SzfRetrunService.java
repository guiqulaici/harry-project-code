package com.yeahwap.netgame.szf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yeahwap.netgame.service.MerchantService;

@Service
public class SzfRetrunService {
	@Autowired
	@Qualifier("merchantService")
	private MerchantService merchantService;
	
	
}
