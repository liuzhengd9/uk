package com.pcitc.uk.dao.impl;

import org.springframework.stereotype.Repository;

import com.pcitc.uk.dao.UkFormItemDao;
import com.pcitc.uk.dao.base.BaseDaoImpl;
import com.pcitc.uk.model.UkFormItem;

@Repository(value = "ukFormItemDao")
public class UkFormItemDaoImpl extends BaseDaoImpl<UkFormItem, Integer> implements UkFormItemDao {

	@Override
	public Class<UkFormItem> getPersistenceClass() {

		return UkFormItem.class;
	}

}
