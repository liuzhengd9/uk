package com.pcitc.uk.service.base;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.pcitc.uk.common.PageDataBean;
import com.pcitc.uk.dao.base.BaseDao;

@Transactional
public abstract class BaseServiceImpl<T, PK extends Serializable, DAO extends BaseDao<T, PK>> implements BaseService<T, PK, DAO> {

	public abstract DAO getDao();

	@Override
	public void save(T o) {

		getDao().save(o);

	}

	@Override
	public void delete(T o) {

		getDao().delete(o);

	}

	@Override
	public void delete(PK id) {

		getDao().deleteById(id);
	}

	@Override
	public void delete(PK[] ids) {

		for (PK id : ids) {
			getDao().deleteById(id);
		}
	}

	@Override
	public void update(T o) {

		getDao().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {

		getDao().saveOrUpdate(o);
	}

	@Override
	public T get(PK id) {

		return getDao().get(id);
	}

	@Override
	public List<T> find(String hql, Object... params) {

		return getDao().query(hql, params);
	}

	@Override
	public List<T> find(String hql, Object[] params, int size) {

		return getDao().findByPage(hql, params, 0, size);
	}

	@Override
	public PageDataBean<T> findPageDataBean(String hql, Object[] params, int start, int limit) {

		PageDataBean<T> pdb = new PageDataBean<T>();
		int count = getDao().count(hql, params);
		if (count > 0) {
			pdb.setTotal(count);
			pdb.setList(getDao().findByPage(hql, params, start, limit));
		}
		return pdb;
	}

	@Override
	public PageDataBean<T> findPageDataBean(String className, Map<String, Object[]> params, int start, int limit, String sort, String order) {

		StringBuilder hql = new StringBuilder(MessageFormat.format("from {0} where 1=1", className));
		StringBuilder countHql = new StringBuilder(MessageFormat.format("select count(id) from {0} where 1=1", className));
		List<Object> paramList = null;
		if (params != null && !params.isEmpty()) {
			paramList = new ArrayList<Object>();
			for (String key : params.keySet()) {
				hql.append(" ").append(key);
				countHql.append(" ").append(key);
				if (params.get(key) != null) {
					for (Object o : params.get(key)) {
						paramList.add(o);
					}
				}
			}
		}
		hql.append(" ").append(MessageFormat.format("order by {0} {1}", sort, order));
		PageDataBean<T> pdb = new PageDataBean<T>();
		int columns = getDao().count(countHql.toString(), (paramList != null && paramList.size() > 0) ? paramList.toArray() : null);
		List<T> list = getDao().findByPage(hql.toString(), (paramList != null && paramList.size() > 0) ? paramList.toArray() : null, start, limit);
		if (columns > 0 && list != null && list.size() > 0) {
			pdb.setTotal(columns);
			pdb.setList(list);
		}
		return pdb;
	}

}
