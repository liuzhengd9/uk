package com.pcitc.uk.service;

import java.util.List;

import com.pcitc.uk.dao.MenuDao;
import com.pcitc.uk.model.Menu;
import com.pcitc.uk.service.base.BaseService;

public interface MenuService extends BaseService<Menu, Integer, MenuDao> {

	public List<Menu> findAll();

	public void drag(int sourceId, int targetId);
}
