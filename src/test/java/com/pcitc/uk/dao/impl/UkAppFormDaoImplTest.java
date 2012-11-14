package com.pcitc.uk.dao.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.pcitc.uk.dao.UkAppFormDao;
import com.pcitc.uk.model.UkAppForm;
import com.pcitc.uk.model.UkFormItem;
import com.pcitc.uk.test.BaseTest;

public class UkAppFormDaoImplTest extends BaseTest {

	@Resource(name = "ukAppFormDao", type = UkAppFormDao.class)
	private UkAppFormDao ukAppFormDao;

	@Test
	public void save() {

		UkAppForm f = new UkAppForm();
		f.setUkFormItemSet(new HashSet<UkFormItem>());
		for (int i = 0; i < 100; i++) {
			UkFormItem item = new UkFormItem();
			f.getUkFormItemSet().add(item);
		}
		ukAppFormDao.save(f);
	}

	// @Test
	public void delete() {

		ukAppFormDao.delete(ukAppFormDao.get(2));
	}

	// @Test
	public void get() {

		ukAppFormDao.get(1);
	}

	// @Test
	public void query() {

		List<UkAppForm> list = ukAppFormDao.query("from UkAppForm");
		for (UkAppForm f : list) {
			System.out.println(f.getId());
			if (!CollectionUtils.isEmpty(f.getUkFormItemSet())) {
				for (UkFormItem item : f.getUkFormItemSet()) {
					System.out.println(item.getId());
				}
			}
		}
	}

	// @Test
	public void queryByArray() {

		List<UkAppForm> list = ukAppFormDao.findByPage("from UkAppForm", null, 0, 10);
		for (UkAppForm f : list) {
			System.out.println(f.getId());
		}
	}

}
