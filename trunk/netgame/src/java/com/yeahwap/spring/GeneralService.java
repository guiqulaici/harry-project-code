package com.yeahwap.spring;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Harry
 * 
 */
public class GeneralService<T> {
	protected Logger log = Logger.getLogger(getClass());
	private Class<T> entityClass;

	public GeneralService() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof Class) {
			String className = type.toString().substring(6);
			try {
				type = Class.forName(className).getGenericSuperclass();
			} catch (Exception e) {
			}
		}
		entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Autowired
	@Qualifier("dataSource")
	protected DataSource dataSource;

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	@Transactional(readOnly = true)
	public T get(int id) {
		return (T) hibernateTemplate.get(entityClass, id);
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		return (List<T>) hibernateTemplate.findByCriteria(dc);
	}

	@Transactional
	public int add(T t) {
		Integer id = (Integer) hibernateTemplate.save(t);
		return id.intValue();
	}

	@Transactional
	public void delete(T t) {
		hibernateTemplate.delete(t);
	}

	@Transactional
	public void delete(int id) {
		T t = get(id);
		if (t != null) {
			hibernateTemplate.delete(t);
		}
	}

	@Transactional
	public void update(T t) {
		hibernateTemplate.update(t);
	}
}
