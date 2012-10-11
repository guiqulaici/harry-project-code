package com.yeahwap.spring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @author Harry
 *
 */
public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return OperationHolder.getOperation();
	}
}
