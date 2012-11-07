package com.yeahwap.netgame.service;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.Balance;
import com.yeahwap.spring.GeneralService;

/**
 * 
 * @author Harry Ye
 *
 */
@Service
public class BalanceService extends GeneralService<Balance> {
	public BalanceService() {
		log = Logger.getLogger("revenueDataLog");
	}
	
	@Transactional
	public Balance get(int uid) {
		Balance b = (Balance) hibernateTemplate.get(Balance.class, uid);
		if (null == b) {
			b = createBalance(uid);
		}
		return b;
	}
	
	@Transactional
	public Balance getNoCache(int uid) {
		// TODO 需要修改nocahe
		Balance b = (Balance) hibernateTemplate.get(Balance.class, uid);
		if (null == b) {
			b = createBalance(uid);
		}
		return b;
	}
	
	@Transactional
	public void update(Balance balance) {
		hibernateTemplate.update(balance);

		if (log.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder("balance update");
			sb.append(":uid=").append(balance.getUid());
			sb.append(",b=").append(balance.getBalance());
			sb.append(",p=").append(balance.getPaid());
			sb.append(",f=").append(balance.getFreeze());
			log.info(sb.toString());
		}
	}
	
	private Balance createBalance(int uid) {
		Balance b = new Balance();
		b.setUid(uid);
		b.setBalance(0);
		b.setPaid(0);
		b.setFreeze(0);
		hibernateTemplate.save(b);

		if (log.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder("balance create");
			sb.append(":uid=").append(b.getUid());
			sb.append(",b=").append(b.getBalance());
			sb.append(",p=").append(b.getPaid());
			sb.append(",f=").append(b.getFreeze());
			log.info(sb.toString());
		}

		return b;
	}
	
	public void addBalance (Balance b) {
		Balance balance = getNoCache(b.getUid());
		balance.setBalance(b.getBalance() + balance.getBalance());
		update(balance);
	}
}
