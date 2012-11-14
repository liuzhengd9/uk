package com.pcitc.uk.dao.impl;

import org.springframework.stereotype.Repository;

import com.pcitc.uk.dao.MenuDao;
import com.pcitc.uk.dao.base.BaseDaoImpl;
import com.pcitc.uk.model.Menu;

@Repository(value = "menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu, Integer> implements MenuDao {

	@Override
	public Class<Menu> getPersistenceClass() {

		return Menu.class;
	}

}
