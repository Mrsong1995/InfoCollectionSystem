package com.key.common.service;

import com.key.common.base.entity.IdEntity;
import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T extends IdEntity,ID extends Serializable> {
	
	void setBaseDao();
	
	void save(T t);
	
	void delete(T t);
	
	void delete(ID id);
	
	T get(ID id);
	
	T getModel(ID id);
	
	Page<T> findPage(Page<T> page, List<PropertyFilter> filters);

	List<T> findList(List<PropertyFilter> filters);

	List<T> findAll(Page<T> page, List<PropertyFilter> filters);

	List<T> findList(Criterion... criterions);

	Page<T> findPage(Page<T> page, Criterion... criterion);
}
