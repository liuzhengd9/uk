package com.pcitc.uk.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcitc.uk.model.Menu;
import com.pcitc.uk.service.MenuService;

@Controller
public class MenuController {

	private final Logger logger = Logger.getLogger(getClass());

	@Resource(name = "menuService", type = MenuService.class)
	private MenuService menuService;

	@RequestMapping(value = "/menu/index.html")
	public String index() {

		return "menu/menu";
	}

	@RequestMapping(value = "/menu/tree.html")
	@ResponseBody
	public List<Menu> permission() {

		return menuService.findAll();
	}

	@RequestMapping(value = "/menu/persist.html")
	@ResponseBody
	public ModelMap persist(HttpServletRequest request) throws ServletException {

		ModelMap model = new ModelMap();

		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String text = ServletRequestUtils.getRequiredStringParameter(request, "text");
		String url = ServletRequestUtils.getStringParameter(request, "url", "");
		String cls = ServletRequestUtils.getStringParameter(request, "cls", "");
		boolean leaf = ServletRequestUtils.getRequiredBooleanParameter(request, "leaf");
		int parentId = ServletRequestUtils.getIntParameter(request, "parentId", 0);

		logger.info("id:" + id);
		logger.info("text:" + text);
		logger.info("url:" + url);
		logger.info("cls:" + cls);
		logger.info("leaf:" + leaf);
		logger.info("parentId:" + parentId);

		Menu menu = new Menu();
		if (id > 0) {
			menu.setId(id);
		}
		menu.setText(text);
		menu.setUrl(url);
		menu.setCls(cls);
		menu.setLeaf(leaf);
		if (parentId > 0) {
			menu.setParentId(parentId);
		}

		menuService.saveOrUpdate(menu);

		model.addAttribute("success", true);
		model.addAttribute("msg", "添加成功");
		return model;
	}

	@RequestMapping(value = "/menu/delete.html")
	@ResponseBody
	public ModelMap delete(@RequestParam(value = "id") int id) {

		logger.info("id:" + id);
		menuService.delete(id);
		ModelMap model = new ModelMap();
		model.addAttribute("success", true);
		model.addAttribute("msg", "删除成功");
		return model;
	}

	@RequestMapping(value = "/menu/load.html")
	@ResponseBody
	public ModelMap load(@RequestParam(value = "id") int id) {

		ModelMap model = new ModelMap();
		model.addAttribute("success", true);
		model.addAttribute("data", menuService.get(id));
		return model;
	}

	@RequestMapping(value = "/menu/drag.html")
	@ResponseBody
	public ModelMap drag(@RequestParam(value = "sourceId") int sourceId, @RequestParam(value = "targetId") int targetId) {

		menuService.drag(sourceId, targetId);
		ModelMap model = new ModelMap();
		model.addAttribute("success", true);
		model.addAttribute("msg", "成功");
		return model;
	}
}
