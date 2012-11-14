package com.pcitc.uk.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcitc.uk.common.PageDataBean;
import com.pcitc.uk.model.UkAppForm;
import com.pcitc.uk.service.UkAppFormService;
import com.pcitc.uk.util.JsonUtils;
import com.pcitc.uk.util.Utils;

@Controller
public class UkAppFormController {

	private final Logger logger = Logger.getLogger(getClass());

	@Resource(name = "ukAppFormService", type = UkAppFormService.class)
	private UkAppFormService ukAppFormService;

	@RequestMapping(value = "/ukAppForm/index.html")
	public String index() {

		return "uk/ukAppForm";
	}

	@RequestMapping(value = "/ukAppForm/list.html")
	@ResponseBody
	public PageDataBean<UkAppForm> list(HttpServletRequest request) {

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		int companyType = ServletRequestUtils.getIntParameter(request, "companyType", 0);
		String[] sort = JsonUtils.getSort(ServletRequestUtils.getStringParameter(request, "sort", ""), new String[] { "id", "asc" });
		logger.info("start:" + start);
		logger.info("limit:" + limit);
		logger.info("name:" + name);
		logger.info("companyType:" + companyType);
		Map<String, Object[]> params = new HashMap<String, Object[]>();
		if (StringUtils.hasText(name)) {
			params.put("and name like ?", new String[] { "%" + name + "%" });
		}
		if (companyType > 0) {
			params.put("and companyType=?", new Integer[] { companyType });
		}
		return ukAppFormService.findPageDataBean("UkAppForm", params, start, limit, sort[0], sort[1]);
	}

	@RequestMapping(value = "/ukAppForm/delete.html")
	@ResponseBody
	public ModelMap delete(@RequestParam(value = "id") Integer[] ids) {

		ModelMap model = new ModelMap();
		logger.info("id:" + ids);
		ukAppFormService.delete(ids);
		model.addAttribute("success", true);
		model.addAttribute("msg", "删除成功");
		return model;
	}

	@RequestMapping(value = "/ukAppForm/persist.html")
	@ResponseBody
	public ModelMap persist(HttpServletRequest request) throws ServletException {

		ModelMap model = new ModelMap();
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		String companyName = ServletRequestUtils.getRequiredStringParameter(request, "companyName");
		int companyType = ServletRequestUtils.getRequiredIntParameter(request, "companyType");
		String companyAddress = ServletRequestUtils.getRequiredStringParameter(request, "companyAddress");
		String postcode = ServletRequestUtils.getRequiredStringParameter(request, "postcode");
		String linkman = ServletRequestUtils.getRequiredStringParameter(request, "linkman");
		String phone = ServletRequestUtils.getRequiredStringParameter(request, "phone");
		String email = ServletRequestUtils.getRequiredStringParameter(request, "email");
		String createTime = ServletRequestUtils.getStringParameter(request, "createTime");
		logger.info("name:" + name);
		logger.info("companyName:" + companyName);
		logger.info("companyType:" + companyType);
		logger.info("companyAddress:" + companyAddress);
		logger.info("postcode:" + postcode);
		logger.info("linkman:" + linkman);
		logger.info("phone:" + phone);
		logger.info("email:" + email);
		logger.info("createTime:" + createTime);
		UkAppForm ukAppForm = new UkAppForm();
		if (id > 0) {
			ukAppForm.setId(id);
		}
		ukAppForm.setName(name);
		ukAppForm.setCompanyName(companyName);
		ukAppForm.setCompanyType(companyType);
		ukAppForm.setCompanyAddress(companyAddress);
		ukAppForm.setPostcode(postcode);
		ukAppForm.setLinkman(linkman);
		ukAppForm.setPhone(phone);
		ukAppForm.setEmail(email);
		if (StringUtils.hasText(createTime)) {
			try {
				ukAppForm.setCreateTime(Utils.format(createTime, "yyyy-MM-dd"));
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			ukAppForm.setCreateTime(new Date());
		}
		ukAppFormService.saveOrUpdate(ukAppForm);
		model.addAttribute("success", true);
		model.addAttribute("msg", "添加成功");
		return model;
	}

	@RequestMapping(value = "/ukAppForm/load.html")
	@ResponseBody
	public ModelMap load(@RequestParam(value = "id") Integer id) {

		ModelMap model = new ModelMap();
		model.addAttribute("success", true);
		model.addAttribute("data", ukAppFormService.get(id));
		return model;
	}

}
