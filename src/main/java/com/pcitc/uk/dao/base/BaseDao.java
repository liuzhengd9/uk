package com.pcitc.uk.dao.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, PK extends Serializable> {

	public void save(T o);

	public void delete(T o);

	public void deleteById(PK id);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(PK id);

	public List<T> query(String hql, Object... params);

	public List<T> findByPage(final String hql, final Object[] params, final int start, final int size);

	public int count(String hql, Object[] params);
}
