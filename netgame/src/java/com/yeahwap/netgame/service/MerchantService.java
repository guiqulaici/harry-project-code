package com.yeahwap.netgame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.Merchant;
import com.yeahwap.spring.GeneralService;

/**
 * 
 * 
 * @author Harry
 *         <ul>
 *         <li>Create on 2012-10-15</li>
 *         <li>description:管理商户所有信息</li>
 *         </ul>
 */
@Service
public class MerchantService extends GeneralService<Merchant> {
	@Transactional
	public int add(Merchant u) {
		int id = super.add(u);
		return id;
	}

	// TODO : get merchant from id
	@Transactional(readOnly = true)
	public Merchant get(int id) {
		return super.get(id);
	}
}
