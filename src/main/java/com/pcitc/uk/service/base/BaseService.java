package com.pcitc.uk.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.pcitc.uk.common.PageDataBean;
import com.pcitc.uk.dao.base.BaseDao;

public interface BaseService<T, PK extends Serializable, DAO extends BaseDao<T, PK>> {

	public void save(T o);

	public void delete(T o);

	public void delete(PK id);

	public void delete(PK[] ids);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(PK id);

	public List<T> find(String hql, Object... params);

	public List<T> find(final String hql, final Object[] params, final int size);

	public PageDataBean<T> findPageDataBean(String hql, Object[] params, int start, int limit);

	public PageDataBean<T> findPageDataBean(String className, Map<String, Object[]> params, int start, int limit, String sort, String order);
}
