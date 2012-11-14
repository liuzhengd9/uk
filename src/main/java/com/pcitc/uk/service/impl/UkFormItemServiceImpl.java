package com.pcitc.uk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.uk.dao.UkFormItemDao;
import com.pcitc.uk.model.UkFormItem;
import com.pcitc.uk.service.UkFormItemService;
import com.pcitc.uk.service.base.BaseServiceImpl;

@Service(value = "ukFormItemService")
@Transactional
public class UkFormItemServiceImpl extends BaseServiceImpl<UkFormItem, Integer, UkFormItemDao> implements UkFormItemService {

	@Resource(name = "ukFormItemDao", type = UkFormItemDao.class)
	private UkFormItemDao ukFormItemDao;

	@Override
	public UkFormItemDao getDao() {

		return ukFormItemDao;
	}

}
