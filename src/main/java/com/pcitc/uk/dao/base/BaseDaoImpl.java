package com.pcitc.uk.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	@Resource(name = "hibernateTemplate", type = HibernateTemplate.class)
	private HibernateTemplate hibernateTemplate;

	public abstract Class<T> getPersistenceClass();

	@Override
	public void save(T o) {

		hibernateTemplate.save(o);
	}

	@Override
	public void delete(T o) {

		hibernateTemplate.delete(o);

	}

	@Override
	public void deleteById(PK id) {

		hibernateTemplate.delete(get(id));

	}

	@Override
	public void update(T o) {

		hibernateTemplate.update(o);

	}

	@Override
	public void saveOrUpdate(T o) {

		hibernateTemplate.saveOrUpdate(o);
	}

	@Override
	public T get(PK id) {

		return hibernateTemplate.get(getPersistenceClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String hql, Object... params) {

		return hibernateTemplate.find(hql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPage(final String hql, final Object[] params, final int start, final int size) {

		return hibernateTemplate.executeFind(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {

				Query query = session.createQuery(hql);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.setFirstResult(start).setMaxResults(size).list();
			}

		});
	}

	@Override
	public int count(String hql, Object[] params) {

		return DataAccessUtils.intResult(hibernateTemplate.find(hql, params));
	}

}
