package com.pcitc.uk.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.pcitc.uk.common.PageDataBean;
import com.pcitc.uk.model.UkAppForm;
import com.pcitc.uk.service.UkAppFormService;
import com.pcitc.uk.test.BaseTest;

public class UkAppFormServiceImplTest extends BaseTest {

	@Resource(name = "ukAppFormService", type = UkAppFormService.class)
	private UkAppFormService ukAppFormService;

	// @Test
	public void findPageDataBean() {

		PageDataBean<UkAppForm> pdb = ukAppFormService.findPageDataBean("UkAppForm", null, 0, 1, "id", "asc");
		System.out.println(pdb.getTotal());
		Assert.isTrue(pdb.getTotal() > 0);
		System.out.println(pdb.getList().size());
	}

	@Test
	public void deleteById() {

		ukAppFormService.delete(26);
	}

}
