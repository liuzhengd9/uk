package com.pcitc.uk.dao.impl;

import org.springframework.stereotype.Repository;

import com.pcitc.uk.dao.UkAppFormDao;
import com.pcitc.uk.dao.base.BaseDaoImpl;
import com.pcitc.uk.model.UkAppForm;

@Repository(value = "ukAppFormDao")
public class UkAppFormDaoImpl extends BaseDaoImpl<UkAppForm, Integer> implements UkAppFormDao {

	@Override
	public Class<UkAppForm> getPersistenceClass() {

		return UkAppForm.class;
	}

}
