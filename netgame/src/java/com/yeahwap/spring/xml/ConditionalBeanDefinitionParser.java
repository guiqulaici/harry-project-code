/**
 * 
 */
package com.yeahwap.spring.xml;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * @author bill
 * 
 */
public class ConditionalBeanDefinitionParser implements BeanDefinitionParser {

	private final Logger log = Logger.getLogger(ConditionalBeanDefinitionParser.class);

	/** Default placeholder prefix: "${" */
	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	/** Default placeholder suffix: "}" */
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

	private static Properties config = null;

	public ConditionalBeanDefinitionParser() {
		config = new Properties();
	}

	private static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	private static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * Parse the "condition" element and check the mandatory "test" attribute.
	 * If the provided resources or the system property named by test is
	 * null/empty/false (i.e. not defined) then return null, which is the same
	 * as not defining the bean.
	 */
	public BeanDefinition parse(Element element,
			ParserContext parserContext) {
		try {
			if (element.getLocalName().equals("condition")) {
				String test = element.getAttribute("test");
				String location = element.getAttribute("location");
				if (isEmpty(location)) {
					throw new Exception(
						"the if:condition's attribute 'src' should not be empty !!");
				} else {
					loadConfig(location);
				}
				if (isNotEmpty(test)) {
					if ("true".equalsIgnoreCase(getProperty(test))) {
						Element importElement = DomUtils.getChildElementByTagName(
								element, "import");
						String resource = importElement.getAttribute("resource");
						if (isNotEmpty(resource)) {
							Resource resourceHelper = parserContext.getReaderContext().getResource();
							XmlBeanDefinitionReader reader = parserContext.getReaderContext().getReader();
							reader.loadBeanDefinitions(resourceHelper.createRelative(resource));
							System.out.println("import " + resource
									+ " success !!");
						} else {
							throw new Exception(
								"the import's attribute 'resource' should not be empty !!");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Fail to load condition import.", e);
		}
		return null;
	}

	private void loadConfig(String location) {
		InputStream is = null;
		try {
			is = this.getClass().getResourceAsStream(location);
			if (null != is) {
				config.load(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Get the value of a named resource/system property (it may not be
	 * defined).
	 * 
	 * @param strVal
	 *            The name of a system property. The property may optionally be
	 *            surrounded in Ant/EL-style brackets. e.g. "${propertyname}"
	 * 
	 * @return
	 */
	private String getProperty(String strVal) {
		if (isEmpty(strVal)) {
			return null;
		}
		String returnValue = null;
		if (strVal.startsWith(DEFAULT_PLACEHOLDER_PREFIX)
				&& strVal.endsWith(DEFAULT_PLACEHOLDER_SUFFIX)) {
			returnValue = config.getProperty(strVal.substring(
					DEFAULT_PLACEHOLDER_PREFIX.length(),
					strVal.length() - DEFAULT_PLACEHOLDER_SUFFIX.length()).trim());
			if (null == returnValue) {
				returnValue = System.getProperty(strVal.substring(
						DEFAULT_PLACEHOLDER_PREFIX.length(), strVal.length()
								- DEFAULT_PLACEHOLDER_SUFFIX.length()));
			}
			if (isNotEmpty(returnValue)) {
				if (returnValue.trim().equalsIgnoreCase("false"))
					returnValue = null;
			}
			if (log.isDebugEnabled()) {
				log.debug("Returned : "
						+ System.getProperty(strVal.substring(
								DEFAULT_PLACEHOLDER_PREFIX.length(),
								strVal.length()
										- DEFAULT_PLACEHOLDER_SUFFIX.length())));
			}
			return returnValue;
		} else {
			return System.getProperty(strVal);
		}
	}

}
