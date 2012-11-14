package com.pcitc.uk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.uk.dao.UkAppFormDao;
import com.pcitc.uk.model.UkAppForm;
import com.pcitc.uk.service.UkAppFormService;
import com.pcitc.uk.service.base.BaseServiceImpl;

@Service(value = "ukAppFormService")
@Transactional
public class UkAppFormServiceImpl extends BaseServiceImpl<UkAppForm, Integer, UkAppFormDao> implements UkAppFormService {

	@Resource(name = "ukAppFormDao", type = UkAppFormDao.class)
	private UkAppFormDao ukAppFormDao;

	@Override
	public UkAppFormDao getDao() {

		return ukAppFormDao;
	}

}
