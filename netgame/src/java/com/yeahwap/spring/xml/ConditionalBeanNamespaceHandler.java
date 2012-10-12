/**
 * 
 */
package com.yeahwap.spring.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author bill
 * 
 */
public class ConditionalBeanNamespaceHandler extends NamespaceHandlerSupport {

	/**
	 * 
	 */
	public ConditionalBeanNamespaceHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	public void init() {
		super.registerBeanDefinitionParser("condition",
				new ConditionalBeanDefinitionParser());
	}

}
