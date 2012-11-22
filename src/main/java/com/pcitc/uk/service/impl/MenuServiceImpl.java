package com.pcitc.uk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.uk.dao.MenuDao;
import com.pcitc.uk.model.Menu;
import com.pcitc.uk.service.MenuService;
import com.pcitc.uk.service.base.BaseServiceImpl;

@Service(value = "menuService")
@Transactional
public class MenuServiceImpl extends BaseServiceImpl<Menu, Integer, MenuDao> implements MenuService {

	@Resource(name = "menuDao", type = MenuDao.class)
	private MenuDao menuDao;

	@Override
	public MenuDao getDao() {

		return menuDao;
	}

	@Override
	public List<Menu> findAll() {

		return ((MenuService) AopContext.currentProxy()).find("from Menu m where m.parentId is null order by m.text asc");
	}

	@Override
	public void drag(int sourceId, int targetId) {

		Menu menu = ((MenuService) AopContext.currentProxy()).get(sourceId);
		menu.setParentId(targetId == 0 ? null : targetId);
		((MenuService) AopContext.currentProxy()).saveOrUpdate(menu);
	}

}
